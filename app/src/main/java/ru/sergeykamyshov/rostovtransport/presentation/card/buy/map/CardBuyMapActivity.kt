package ru.sergeykamyshov.rostovtransport.presentation.card.buy.map

import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.google.gson.Gson
import ru.sergeykamyshov.rostovtransport.BuildConfig
import ru.sergeykamyshov.rostovtransport.R
import ru.sergeykamyshov.rostovtransport.base.Const
import ru.sergeykamyshov.rostovtransport.domain.card.BuyAddress

class CardBuyMapActivity : AppCompatActivity(), OnMapReadyCallback {

    private lateinit var addresses: List<BuyAddress>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.fragment_map)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val json = intent.getStringExtra(ADDRESSES_EXTRA)
        addresses = Gson().fromJson(json, Array<BuyAddress>::class.java).toList()

        val mapFragment = supportFragmentManager.findFragmentById(R.id.map_fragment) as SupportMapFragment
        mapFragment.getMapAsync(this)
    }

    override fun onMapReady(googleMap: GoogleMap?) {
        addresses.forEach addressLoop@{ address ->
            val locations = address.locations
            if (locations.isEmpty()) {
                return@addressLoop
            }

            locations.forEach locationLoop@{ location ->
                val coordinates = location
                        .trim()
                        .replace(" ", "")
                        .split(",")
                if (coordinates.size != 2) {
                    return@locationLoop
                }
                googleMap?.addMarker(
                        MarkerOptions()
                                .position(LatLng(coordinates[0].toDouble(), coordinates[1].toDouble()))
                                .title(address.desc)
                )
            }
        }

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

    companion object {
        const val ADDRESSES_EXTRA = "${BuildConfig.APPLICATION_ID}.CardBuyMapActivity.ADDRESSES"
    }

}