package ru.sergeykamyshov.rostovtransport.data.network.model.card

import com.google.gson.annotations.SerializedName
import java.util.*

class CardBuy {

    @SerializedName("lastUpdate")
    lateinit var lastUpdate: Date

    @SerializedName("address")
    lateinit var address: List<Address>

    class Address {

        @SerializedName("desc")
        lateinit var desc: String

        @SerializedName("district")
        lateinit var district: String

        @SerializedName("address")
        lateinit var address: String

        @SerializedName("latitude")
        lateinit var latitude: String

        @SerializedName("longitude")
        lateinit var longitude: String

    }

}