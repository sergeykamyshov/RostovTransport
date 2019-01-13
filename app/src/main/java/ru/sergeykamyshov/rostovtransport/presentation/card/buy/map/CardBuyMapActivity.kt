package ru.sergeykamyshov.rostovtransport.presentation.card.buy.map

import android.content.Context
import android.content.Intent
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
import ru.sergeykamyshov.rostovtransport.data.network.model.card.CardBuy

class CardBuyMapActivity : AppCompatActivity(), OnMapReadyCallback {

    private lateinit var addresses: List<CardBuy.Address>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.fragment_card_map)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val json = intent.getStringExtra(ADDRESSES_EXTRA)
        addresses = Gson().fromJson(json, Array<CardBuy.Address>::class.java).toList()

        val mapFragment = supportFragmentManager.findFragmentById(R.id.map_card) as SupportMapFragment
        mapFragment.getMapAsync(this)
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

    companion object {
        const val ADDRESSES_EXTRA = "${BuildConfig.APPLICATION_ID}.CardBuyMapActivity.ADDRESSES"

        fun getIntent(context: Context, addresses: String): Intent {
            val intent = Intent(context, CardBuyMapActivity::class.java)
            intent.putExtra(ADDRESSES_EXTRA, addresses)
            return intent
        }
    }

}