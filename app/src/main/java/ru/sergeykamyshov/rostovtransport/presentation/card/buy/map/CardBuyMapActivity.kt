package ru.sergeykamyshov.rostovtransport.presentation.card.buy.map

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.MenuItem
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import ru.sergeykamyshov.rostovtransport.R
import ru.sergeykamyshov.rostovtransport.data.network.model.card.CardBuy
import ru.sergeykamyshov.rostovtransport.presentation.card.buy.CardBuyViewModel
import ru.sergeykamyshov.rostovtransport.base.Const

class CardBuyMapActivity : AppCompatActivity(), OnMapReadyCallback {

    private lateinit var addresses: List<CardBuy.Address>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.fragment_card_map)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val mapFragment = supportFragmentManager.findFragmentById(R.id.map_card) as SupportMapFragment

        // TODO: зачем нам делать еще один запрос, когда у нас уже есть список с координатами?
        val viewModel = ViewModelProviders.of(this).get(CardBuyViewModel::class.java)
        val liveData = viewModel.getData()
        liveData.observe(this, Observer {
            if (it != null) {
                addresses = it
                mapFragment.getMapAsync(this)
            }
        })
        viewModel.loadData()
    }

    override fun onMapReady(googleMap: GoogleMap?) {
        addresses.forEach addressLoop@{ address ->
            val locations = address.locations
            if (locations.isEmpty()) return@addressLoop

            locations.forEach locationLoop@{ location ->
                val coordinates = location.trim().replace(" ", "").split(",")
                if (coordinates.size != 2) return@locationLoop
                googleMap?.addMarker(MarkerOptions()
                        .position(LatLng(coordinates[0].toDouble(), coordinates[1].toDouble()))
                        .title(address.desc))
            }
        }

        googleMap?.uiSettings?.isZoomControlsEnabled = true
        googleMap?.moveCamera(CameraUpdateFactory.newLatLngZoom(
                LatLng(Const.RND_LATITUDE, Const.RND_LONGITUDE), 12F))
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item?.itemId) {
            android.R.id.home -> finish()
        }
        return super.onOptionsItemSelected(item)
    }

}