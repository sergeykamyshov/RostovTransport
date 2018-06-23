package ru.sergeykamyshov.rostovtransport.data.network.model.card

import com.google.gson.annotations.SerializedName
import java.util.*

class CardInfo {

    @SerializedName("lastUpdate")
    lateinit var lastUpdate: Date

    @SerializedName("title")
    lateinit var title: String

    @SerializedName("content")
    lateinit var content: String

}