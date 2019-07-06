package ru.sergeykamyshov.rostovtransport.presentation.card.deposit.map

import android.graphics.Color
import android.graphics.Typeface
import android.os.Bundle
import android.view.Gravity
import android.view.MenuItem
import android.view.View
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.Marker
import com.google.android.gms.maps.model.MarkerOptions
import com.google.gson.Gson
import ru.sergeykamyshov.rostovtransport.BuildConfig
import ru.sergeykamyshov.rostovtransport.R
import ru.sergeykamyshov.rostovtransport.base.Const
import ru.sergeykamyshov.rostovtransport.domain.card.DepositAddress

class CardDepositMapActivity : AppCompatActivity(), OnMapReadyCallback {

    private lateinit var addresses: List<DepositAddress>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.fragment_map)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val json = intent.getStringExtra(ADDRESSES_EXTRA)
        addresses = Gson().fromJson(json, Array<DepositAddress>::class.java).toList()

        val mapFragment = supportFragmentManager.findFragmentById(R.id.map_fragment) as SupportMapFragment
        mapFragment.getMapAsync(this)
    }

    override fun onMapReady(googleMap: GoogleMap?) {
        addresses.forEach addressLoop@{
            if (it.location.isEmpty()) {
                return@addressLoop
            }

            val coordinates = it.location
                    .trim()
                    .replace(" ", "")
                    .split(",")
            if (coordinates.size != 2) {
                return@addressLoop
            }

            googleMap?.addMarker(
                    MarkerOptions()
                            .position(LatLng(coordinates[0].toDouble(), coordinates[1].toDouble()))
                            .title(it.desc)
                            .snippet("${it.address}\n${it.schedule}")
                            .icon(
                                    BitmapDescriptorFactory.defaultMarker(
                                            if (it.type == 1) {
                                                BitmapDescriptorFactory.HUE_ORANGE
                                            } else {
                                                BitmapDescriptorFactory.HUE_RED
                                            }
                                    )
                            )
            )
            googleMap?.setInfoWindowAdapter(object : GoogleMap.InfoWindowAdapter {
                override fun getInfoContents(marker: Marker?): View {
                    return getInfoView(marker)
                }

                override fun getInfoWindow(marker: Marker?): View {
                    return getInfoView(marker)
                }

                private fun getInfoView(marker: Marker?): View {
                    val context = this@CardDepositMapActivity

                    val info = LinearLayout(context)
                    info.orientation = LinearLayout.VERTICAL
                    info.background = ContextCompat.getDrawable(context, R.drawable.bg_card_map_info)

                    val title = TextView(context)
                    title.setTextColor(Color.BLACK)
                    title.gravity = Gravity.CENTER
                    title.setTypeface(null, Typeface.BOLD)
                    title.text = marker?.title

                    val snippet = TextView(context)
                    snippet.setTextColor(Color.GRAY)
                    snippet.text = marker?.snippet

                    info.addView(title)
                    info.addView(snippet)

                    return info
                }
            })
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
        const val ADDRESSES_EXTRA = "${BuildConfig.APPLICATION_ID}.CardDepositMapActivity.ADDRESSES"
    }

}