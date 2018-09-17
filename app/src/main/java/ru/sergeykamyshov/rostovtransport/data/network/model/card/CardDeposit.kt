package ru.sergeykamyshov.rostovtransport.data.network.model.card

import com.google.gson.annotations.SerializedName
import java.util.*

class CardDeposit {

    @SerializedName("lastUpdate")
    lateinit var lastUpdate: Date

    @SerializedName("addresses")
    lateinit var addresses: List<Address>

    class Address {

        @SerializedName("type")
        lateinit var type: String

        @SerializedName("desc")
        lateinit var desc: String

        @SerializedName("address")
        lateinit var address: String

        @SerializedName("schedule")
        lateinit var schedule: String

        @SerializedName("location")
        lateinit var location: String

    }
}