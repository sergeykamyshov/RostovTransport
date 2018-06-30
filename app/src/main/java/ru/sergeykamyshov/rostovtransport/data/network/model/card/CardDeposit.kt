package ru.sergeykamyshov.rostovtransport.data.network.model.card

import com.google.gson.annotations.SerializedName
import java.util.*

class CardDeposit {

    @SerializedName("lastUpdate")
    lateinit var lastUpdate: Date

    @SerializedName("address")
    lateinit var address: List<Address>

    class Address {

        @SerializedName("desc")
        lateinit var desc: String

        @SerializedName("address")
        lateinit var address: String

        @SerializedName("schedule")
        lateinit var schedule: String

        @SerializedName("latitude")
        var latitude = ""

        @SerializedName("longitude")
        var longitude = ""

    }
}