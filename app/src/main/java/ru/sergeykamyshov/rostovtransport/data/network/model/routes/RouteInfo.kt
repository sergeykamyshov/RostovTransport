package ru.sergeykamyshov.rostovtransport.data.network.model.routes

import com.google.gson.annotations.SerializedName
import java.util.*

class RouteInfo {

    @SerializedName("lastUpdate")
    lateinit var lastUpdate: Date

    @SerializedName("to")
    lateinit var to: List<Point>

    @SerializedName("from")
    lateinit var from: List<Point>

    class Point {

        @SerializedName("lat")
        var lat: Double = 0.0

        @SerializedName("lon")
        var lon: Double = 0.0
    }
}