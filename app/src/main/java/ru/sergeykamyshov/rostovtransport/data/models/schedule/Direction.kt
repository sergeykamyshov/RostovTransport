package ru.sergeykamyshov.rostovtransport.data.models.schedule

import com.google.gson.annotations.SerializedName

class Direction {

    @SerializedName("city")
    lateinit var city: String

    @SerializedName("region")
    lateinit var region: String

    @SerializedName("country")
    lateinit var country: String

    @SerializedName("id")
    lateinit var id: String

}
