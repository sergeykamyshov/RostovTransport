package ru.sergeykamyshov.rostovtransport.data.models.online

import com.google.gson.annotations.SerializedName

class TransportOnline {

    @SerializedName("lon")
    lateinit var lon: String

    @SerializedName("lat")
    lateinit var lat: String

    @SerializedName("speed")
    lateinit var speed: String

    @SerializedName("time")
    lateinit var time: String

    @SerializedName("angle")
    lateinit var angle: String

    @SerializedName("name")
    lateinit var name: String

    @SerializedName("type")
    lateinit var type: String

    @SerializedName("num")
    lateinit var num: String

    @SerializedName("prev")
    lateinit var prev: Prev

    class Prev {
        @SerializedName("lon")
        lateinit var lon: String

        @SerializedName("lat")
        lateinit var lat: String

        @SerializedName("speed")
        lateinit var speed: String

        @SerializedName("angle")
        lateinit var angle: String

        @SerializedName("time")
        lateinit var time: String
    }
}