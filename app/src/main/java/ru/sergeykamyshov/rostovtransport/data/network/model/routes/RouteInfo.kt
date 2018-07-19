package ru.sergeykamyshov.rostovtransport.data.network.model.routes

import com.google.gson.annotations.SerializedName
import java.util.*

class RouteInfo {

    @SerializedName("lastUpdate")
    lateinit var lastUpdate: Date

    @SerializedName("name")
    lateinit var name: String

    @SerializedName("type")
    lateinit var type: String

    @SerializedName("from_name")
    lateinit var fromName: String

    @SerializedName("to_name")
    lateinit var toName: String

    @SerializedName("directions")
    lateinit var directions: List<Direction>

    class Direction {

        @SerializedName("type")
        lateinit var type: String

        @SerializedName("center_point")
        lateinit var centerPoint: String

        @SerializedName("points")
        lateinit var points: String
    }
}