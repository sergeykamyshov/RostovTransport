package ru.sergeykamyshov.rostovtransport.ui.card.buy.map

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

class CardBuyMapActivity : AppCompatActivity(), OnMapReadyCallback {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_card_buy_map)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val mapFragment = supportFragmentManager.findFragmentById(R.id.map_card_buy) as SupportMapFragment
        mapFragment.getMapAsync(this)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item?.itemId) {
            android.R.id.home -> finish()
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onMapReady(googleMap: GoogleMap?) {

        val addresses = generateTestData()

        addresses.forEach {
            googleMap?.addMarker(MarkerOptions()
                    .position(LatLng(it.latitude.toDouble(), it.longitude.toDouble()))
                    .title(it.desc)
                    .snippet(it.address))
        }
        googleMap?.moveCamera(CameraUpdateFactory.newLatLngZoom(
                LatLng(addresses[0].latitude.toDouble(), addresses[0].longitude.toDouble()), 13F))
    }

    fun generateTestData(): List<CardBuy.Address> {
        val list = mutableListOf<CardBuy.Address>()
        for (i in 0..10) {
            val element = CardBuy.Address()
            element.desc = "Test desc $i"
            element.address = "Test address $i"
            element.latitude = 47.22.toString() + i
            element.longitude = 39.63.toString() + i
            list.add(element)
        }
        return list
    }

}