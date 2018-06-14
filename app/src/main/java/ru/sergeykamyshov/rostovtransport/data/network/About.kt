package ru.sergeykamyshov.rostovtransport.data.network

import com.google.gson.annotations.SerializedName
import java.util.*

class About {

    @SerializedName("lastUpdate")
    lateinit var lastUpdate: Date

    @SerializedName("photoUrl")
    lateinit var photoUrl: String

    @SerializedName("shortDescription")
    lateinit var shortDescription: String

    @SerializedName("importantInfo")
    lateinit var importantInfo: String

    @SerializedName("fullDescription")
    lateinit var fullDescription: String
}