package ru.sergeykamyshov.rostovtransport.ui.routes.base

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.graphics.Color
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.MenuItem
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.PolylineOptions
import ru.sergeykamyshov.rostovtransport.R
import ru.sergeykamyshov.rostovtransport.data.network.model.routes.RouteInfo
import ru.sergeykamyshov.rostovtransport.utils.Const

class RouteMapActivity : AppCompatActivity(), OnMapReadyCallback {

    private var mGoogleMap: GoogleMap? = null

    companion object {
        const val ROUTE_ID = "routeId"
        const val ROUTE_TYPE = "routeType"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.fragment_online)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val mapFragment = supportFragmentManager.findFragmentById(R.id.map_transport_online) as SupportMapFragment
        mapFragment.getMapAsync(this)

        val type = intent.getStringExtra(ROUTE_TYPE)
        val id = intent.getStringExtra(ROUTE_ID)

        val viewModel = ViewModelProviders
                .of(this, RoutesModelFactory(type, id))
                .get(RouteMapViewModel::class.java)
        viewModel.getData().observe(this, Observer {
            it?.directions?.forEach {
                prepareAndDrawRoute(it, if (it.type == "forward") Color.RED else Color.GRAY)
            }
        })
        viewModel.loadData()
    }

    override fun onMapReady(googleMap: GoogleMap?) {
        mGoogleMap = googleMap
        mGoogleMap?.uiSettings?.isZoomControlsEnabled = true
        mGoogleMap?.moveCamera(CameraUpdateFactory.newLatLngZoom(
                LatLng(Const.RND_LATITUDE, Const.RND_LONGITUDE), 12F))
    }

    private fun prepareAndDrawRoute(direction: RouteInfo.Direction, color: Int) {
        val centerPoint = getPoint(direction.centerPoint)
        mGoogleMap?.moveCamera(CameraUpdateFactory.newLatLngZoom(centerPoint, 12F))

        val points = getPoints(direction.points)
        drawRoute(points, Color.RED)
    }

    private fun drawRoute(points: List<LatLng>, color: Int) {
        val options = PolylineOptions()
        points.forEach {
            options.add(it)
        }
        options.width(10F)
        options.color(color)
        mGoogleMap?.addPolyline(options)
    }

    private fun getPoint(str: String): LatLng {
        val coordinates = str.split(" ")
        return LatLng(coordinates[1].toDouble(), coordinates[0].toDouble())
    }

    private fun getPoints(str: String): List<LatLng> {
        val coordinates = str.split(",")
        var points = mutableListOf<LatLng>()
        coordinates.forEach {
            points.add(getPoint(it))
        }
        return points
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item?.itemId) {
            android.R.id.home -> finish()
        }
        return super.onOptionsItemSelected(item)
    }
}