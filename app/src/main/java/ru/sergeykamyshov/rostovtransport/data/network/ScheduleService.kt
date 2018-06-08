package ru.sergeykamyshov.rostovtransport.data.network

import com.google.gson.JsonObject
import retrofit2.Call
import retrofit2.http.GET

interface ScheduleService {

    @GET("/cities.json")
//    fun getCities(): Call<JsonObject>
    fun getCities(): Call<List<City>>

}