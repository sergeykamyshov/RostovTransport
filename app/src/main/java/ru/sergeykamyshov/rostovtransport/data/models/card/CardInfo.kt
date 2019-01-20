package ru.sergeykamyshov.rostovtransport.data.models.card

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