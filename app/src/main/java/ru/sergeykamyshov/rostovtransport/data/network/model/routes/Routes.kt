package ru.sergeykamyshov.rostovtransport.data.network.model.routes

import com.google.gson.annotations.SerializedName
import java.util.*

class Routes {

    @SerializedName("lastUpdate")
    lateinit var lastUpdate: Date

    @SerializedName("routes")
    lateinit var routes: List<Route>

    class Route {

        @SerializedName("number")
        lateinit var number: String

        @SerializedName("name")
        lateinit var name: String

        @SerializedName("id")
        lateinit var id: String
    }
}