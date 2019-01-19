package ru.sergeykamyshov.rostovtransport.data.models.online

import com.google.gson.annotations.SerializedName
import java.util.*

class Transport {

    @SerializedName("lastUpdate")
    lateinit var lastUpdate: Date

    @SerializedName("transport")
    lateinit var transport: List<Item>

    class Item {

        @SerializedName("type")
        lateinit var type: String

        @SerializedName("name")
        lateinit var name: String

    }

}