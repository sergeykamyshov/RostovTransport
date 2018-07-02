package ru.sergeykamyshov.rostovtransport.ui.online

import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.ServiceConnection
import android.os.Bundle
import android.os.IBinder
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.Marker
import ru.sergeykamyshov.rostovtransport.R
import ru.sergeykamyshov.rostovtransport.data.network.model.online.Transport
import ru.sergeykamyshov.rostovtransport.service.TransportOnlineService
import ru.sergeykamyshov.rostovtransport.ui.base.BaseFragment
import ru.sergeykamyshov.rostovtransport.utils.Const

class TransportOnlineFragment : BaseFragment(), OnMapReadyCallback {

    private var mGoogleMap: GoogleMap? = null
    private var markers: MutableList<Marker?> = mutableListOf()
    private lateinit var allTransports: List<Transport.Item>

    // Сервис
    lateinit var mService: TransportOnlineService
    // Признак подключения сервиса
    private var mBound = false

    companion object {
        fun newInstance() = TransportOnlineFragment()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Запускаем Сервис для обновления данных о передвижении
//        activity?.startService(Intent(activity, TransportOnlineService::class.java))
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_online, container, false)

        setActionBarTitle(R.string.title_transport_online)

        val mapFragment = childFragmentManager.findFragmentById(R.id.map_transport_online) as SupportMapFragment
        mapFragment.getMapAsync(this)

//        val viewModel = ViewModelProviders.of(activity!!).get(TransportOnlineViewModel::class.java)
//        val liveData = viewModel.getData()
//        liveData.observe(this, Observer {
//            if (it != null) {
//                allTransports = it
//            }
//        })
//        viewModel.loadData()

        return view
    }

    override fun onStart() {
        super.onStart()
        val intent = Intent(activity, TransportOnlineService::class.java)
        activity?.bindService(intent, mConnection, Context.BIND_AUTO_CREATE)
    }

    override fun onStop() {
        super.onStop()
        if (mBound) {
            activity?.unbindService(mConnection)
            mBound = false
        }
    }

    private var mConnection = object : ServiceConnection {
        override fun onServiceDisconnected(name: ComponentName?) {
            mBound = false
            Log.i("TransportOnlineFragment", "Service disconnected")
        }

        override fun onServiceConnected(name: ComponentName?, service: IBinder?) {
            val binder = service as TransportOnlineService.LocalBinder
            mService = binder.getService()
            mBound = true
            Log.i("TransportOnlineFragment", "Service connected")
        }
    }

    override fun onMapReady(googleMap: GoogleMap?) {
        mGoogleMap = googleMap
        mGoogleMap?.uiSettings?.isZoomControlsEnabled = true
        mGoogleMap?.moveCamera(CameraUpdateFactory.newLatLngZoom(
                LatLng(Const.RND_LATITUDE, Const.RND_LONGITUDE), 12F))

//        Thread(Runnable {
//            val restService = App.createOnlineRestService()
//
//            var x = 0
//            while (x < 30) {
//                val call = restService.getTransportByName("2_67")
//                call.enqueue(object : Callback<List<TransportOnline>> {
//                    override fun onFailure(call: Call<List<TransportOnline>>?, t: Throwable?) {
//                        Log.i("TransportOnlineFragment", "Error when try to get transport online: $t")
//                    }
//
//                    override fun onResponse(call: Call<List<TransportOnline>>?, response: Response<List<TransportOnline>>?) {
//                        if (response?.code() == 200) {
//                            val list = response.body()
//
//                            activity?.runOnUiThread {
//                                mGoogleMap?.clear()
//                                list?.forEach {
//                                    val lat = "${it.lat.substring(0, 2)}.${it.lat.substring(2)}"
//                                    val lon = "${it.lon.substring(0, 2)}.${it.lon.substring(2)}"
//                                    mGoogleMap?.addMarker(MarkerOptions().position(LatLng(lat.toDouble(), lon.toDouble())))
//                                }
//                            }
//                        }
//                    }
//
//                })
//                x++
//                TimeUnit.SECONDS.sleep(5)
//            }
//        }).start()
    }
}