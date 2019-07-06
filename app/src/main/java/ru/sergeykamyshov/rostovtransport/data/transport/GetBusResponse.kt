package ru.sergeykamyshov.rostovtransport.data.transport

import com.google.gson.annotations.SerializedName
import ru.sergeykamyshov.rostovtransport.domain.transport.Prev
import ru.sergeykamyshov.rostovtransport.domain.transport.Transport

class GetBusResponse {

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
    lateinit var prev: PrevResponse

    @SerializedName("gray")
    var gray: Boolean = false

    fun toTransport() = Transport(
            toDouble(lon),
            toDouble(lat),
            speed,
            time,
            angle,
            name,
            type,
            num,
            prev.toPrev()
    )


    class PrevResponse {
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

        fun toPrev() = Prev(
                toDouble(lon),
                toDouble(lat),
                speed,
                angle,
                time
        )
    }
}

fun toDouble(coordinate: String): Double {
    val lonOrLat = "${coordinate.substring(0, 2)}.${coordinate.substring(2)}"
    return lonOrLat.toDouble()
}