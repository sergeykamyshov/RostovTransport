package ru.sergeykamyshov.rostovtransport.data.transport

import com.google.gson.annotations.SerializedName
import ru.sergeykamyshov.rostovtransport.domain.transport.Prev
import ru.sergeykamyshov.rostovtransport.domain.transport.Transport

class GetBusResponse(
        @SerializedName("lon") val lon: String,
        @SerializedName("lat") val lat: String,
        @SerializedName("speed") val speed: String,
        @SerializedName("time") val time: String,
        @SerializedName("angle") val angle: String,
        @SerializedName("name") val name: String,
        @SerializedName("type") val type: String,
        @SerializedName("num") val num: String,
        @SerializedName("prev") val prev: PrevResponse?,
        @SerializedName("gray") val gray: Boolean
) {
    fun toTransport() = Transport(
            toDouble(lon),
            toDouble(lat),
            speed,
            time,
            angle,
            name,
            type,
            num,
            prev?.toPrev(),
            gray
    )
}

class PrevResponse(
        @SerializedName("lon") val lon: String,
        @SerializedName("lat") val lat: String,
        @SerializedName("speed") val speed: String,
        @SerializedName("angle") val angle: String,
        @SerializedName("time") val time: String
) {
    fun toPrev() = Prev(
            toDouble(lon),
            toDouble(lat),
            speed,
            angle,
            time
    )
}

fun toDouble(coordinate: String): Double {
    val lonOrLat = "${coordinate.substring(0, 2)}.${coordinate.substring(2)}"
    return lonOrLat.toDouble()
}