package ru.sergeykamyshov.rostovtransport.presentation.card.deposit.map

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.MenuItem
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import ru.sergeykamyshov.rostovtransport.R
import ru.sergeykamyshov.rostovtransport.data.network.model.card.CardDeposit
import ru.sergeykamyshov.rostovtransport.presentation.card.deposit.CardDepositViewModel
import ru.sergeykamyshov.rostovtransport.base.Const

class CardDepositMapActivity : AppCompatActivity(), OnMapReadyCallback {

    private lateinit var addresses: List<CardDeposit.Address>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.fragment_card_map)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val mapFragment = supportFragmentManager.findFragmentById(R.id.map_card) as SupportMapFragment

        val viewModel = ViewModelProviders.of(this).get(CardDepositViewModel::class.java)
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
        addresses.forEach addressLoop@{
            if (it.location.isEmpty()) return@addressLoop

            val coordinates = it.location.trim().replace(" ", "").split(",")
            if (coordinates.size != 2) return@addressLoop

            googleMap?.addMarker(MarkerOptions()
                    .position(LatLng(coordinates[0].toDouble(), coordinates[1].toDouble()))
                    .title(it.desc)
                    .snippet(it.address)
                    .icon(BitmapDescriptorFactory.defaultMarker(
                            if (Const.CARD_TYPE_YELLOW == it.type) BitmapDescriptorFactory.HUE_ORANGE
                            else BitmapDescriptorFactory.HUE_RED)))
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