package ru.sergeykamyshov.rostovtransport.data.network.schedule

import com.google.gson.annotations.SerializedName
import java.util.*

class Directions {

    @SerializedName("lastUpdate")
    lateinit var lastUpdate: Date

    @SerializedName("directions")
    lateinit var directions: List<Direction>

}