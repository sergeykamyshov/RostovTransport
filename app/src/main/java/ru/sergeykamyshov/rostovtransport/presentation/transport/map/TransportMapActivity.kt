package ru.sergeykamyshov.rostovtransport.presentation.transport.map

import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import ru.sergeykamyshov.rostovtransport.R
import ru.sergeykamyshov.rostovtransport.base.Const

class TransportMapActivity : AppCompatActivity(), OnMapReadyCallback {

    private var googleMap: GoogleMap? = null
    private lateinit var viewModel: TransportViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.fragment_map)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val mapFragment = supportFragmentManager.findFragmentById(R.id.map_fragment) as SupportMapFragment
        mapFragment.getMapAsync(this)

        viewModel = ViewModelProviders.of(this).get(TransportViewModel::class.java)

        viewModel.getData().observe(this, Observer {
            if (googleMap == null) {
                return@Observer
            }
            googleMap?.clear()
            it.forEach { transport ->
                googleMap?.addMarker(MarkerOptions()
                        .title(transport.name)
                        .snippet("Номер: ${transport.num}")
                        .position(LatLng(transport.lat, transport.lon))
                )
            }
        })

        viewModel.loadTransport("2_71")
    }

    override fun onMapReady(googleMap: GoogleMap?) {
        this.googleMap = googleMap

        googleMap?.uiSettings?.isZoomControlsEnabled = true
        googleMap?.moveCamera(
                CameraUpdateFactory.newLatLngZoom(
                        LatLng(Const.RND_LATITUDE, Const.RND_LONGITUDE), 12F)
        )
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item?.itemId) {
            android.R.id.home -> finish()
        }
        return super.onOptionsItemSelected(item)
    }

}