package ru.sergeykamyshov.rostovtransport.data.models.card

import com.google.gson.annotations.SerializedName
import java.util.*

class CardBuy {

    @SerializedName("lastUpdate")
    lateinit var lastUpdate: Date

    @SerializedName("addresses")
    lateinit var addresses: List<Address>

    class Address {

        @SerializedName("desc")
        lateinit var desc: String

        @SerializedName("type")
        lateinit var type: String

        @SerializedName("note")
        lateinit var note: String

        @SerializedName("locations")
        lateinit var locations: List<String>

    }

}