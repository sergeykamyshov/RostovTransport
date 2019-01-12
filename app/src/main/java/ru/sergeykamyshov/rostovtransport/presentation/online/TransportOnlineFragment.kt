package ru.sergeykamyshov.rostovtransport.presentation.online

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import ru.sergeykamyshov.rostovtransport.R
import ru.sergeykamyshov.rostovtransport.data.network.model.online.TransportOnline
import ru.sergeykamyshov.rostovtransport.presentation.base.BaseFragment
import ru.sergeykamyshov.rostovtransport.base.Const

class TransportOnlineFragment : BaseFragment(), OnMapReadyCallback {

    private var mGoogleMap: GoogleMap? = null
    private lateinit var mViewModel: TransportOnlineViewModel

    companion object {
        fun newInstance() = TransportOnlineFragment()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_online, container, false)

        setActionBarTitle(R.string.title_transport_online)

        val mapFragment = childFragmentManager.findFragmentById(R.id.map_transport_online) as SupportMapFragment
        mapFragment.getMapAsync(this)

        mViewModel = ViewModelProviders.of(this).get(TransportOnlineViewModel::class.java)
        // Получаем список транспорта
        mViewModel.getTransportList().observe(this, Observer {
            // TODO: Сохранить список транспорта для запроса
        })
        mViewModel.loadTransportList()

        // Получаем список транспорта, который находится онлайн
        mViewModel.getTransportOnline().observe(this, Observer {
            if (it != null) {
                updateTransportOnlineOnMap(it)
            }
        })

        return view
    }

    override fun onStart() {
        super.onStart()
        mViewModel.setRunning(true)
        // TODO: Убрать заглушку
        mViewModel.loadTransportOnline("2_67")
    }

    override fun onStop() {
        super.onStop()
        mViewModel.setRunning(false)
    }

    override fun onMapReady(googleMap: GoogleMap?) {
        mGoogleMap = googleMap
        mGoogleMap?.uiSettings?.isZoomControlsEnabled = true
        mGoogleMap?.moveCamera(CameraUpdateFactory.newLatLngZoom(
                LatLng(Const.RND_LATITUDE, Const.RND_LONGITUDE), 12F))
    }

    /**
     * Обновляет список транспорта онлайн на карте
     * transportList - обновленный список транспорта онлайн
     */
    private fun updateTransportOnlineOnMap(transportList: List<TransportOnline>) {
        mGoogleMap?.clear()
        transportList.forEach {
            val lat = "${it.lat.substring(0, 2)}.${it.lat.substring(2)}"
            val lon = "${it.lon.substring(0, 2)}.${it.lon.substring(2)}"
            mGoogleMap?.addMarker(MarkerOptions()
                    .title(it.name)
                    .snippet("Номер: ${it.num}")
                    .position(LatLng(lat.toDouble(), lon.toDouble())))
        }
    }
}