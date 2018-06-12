package ru.sergeykamyshov.rostovtransport.data.network

import retrofit2.Call
import retrofit2.http.GET
import ru.sergeykamyshov.rostovtransport.data.network.schedule.Directions

interface RestService {

    @GET("/tmp/rostov-transport/test/directions.json")
    fun getDirections(): Call<Directions>

}