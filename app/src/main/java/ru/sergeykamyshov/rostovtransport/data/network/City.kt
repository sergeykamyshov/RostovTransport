package ru.sergeykamyshov.rostovtransport.data.network

import com.google.gson.annotations.SerializedName

class City {

    @SerializedName("cityName")
    lateinit var name: String

    @SerializedName("cityId")
    lateinit var id: String

}