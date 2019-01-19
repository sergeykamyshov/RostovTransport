package ru.sergeykamyshov.rostovtransport.presentation.card.deposit.map

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.google.gson.Gson
import ru.sergeykamyshov.rostovtransport.BuildConfig
import ru.sergeykamyshov.rostovtransport.R
import ru.sergeykamyshov.rostovtransport.base.Const
import ru.sergeykamyshov.rostovtransport.data.models.card.CardDeposit

class CardDepositMapActivity : AppCompatActivity(), OnMapReadyCallback {

    private lateinit var addresses: List<CardDeposit.Address>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.fragment_card_map)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val json = intent.getStringExtra(ADDRESSES_EXTRA)
        addresses = Gson().fromJson(json, Array<CardDeposit.Address>::class.java).toList()

        val mapFragment = supportFragmentManager.findFragmentById(R.id.map_card) as SupportMapFragment
        mapFragment.getMapAsync(this)
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

    companion object {
        const val ADDRESSES_EXTRA = "${BuildConfig.APPLICATION_ID}.CardDepositMapActivity.ADDRESSES"

        fun getIntent(context: Context, addresses: String): Intent {
            val intent = Intent(context, CardDepositMapActivity::class.java)
            intent.putExtra(ADDRESSES_EXTRA, addresses)
            return intent
        }
    }

}