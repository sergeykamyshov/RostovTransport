package ru.sergeykamyshov.rostovtransport.data.network

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query
import ru.sergeykamyshov.rostovtransport.data.network.model.online.TransportOnline

interface OnlineRestService {

    @GET("/get_bus")
    fun getTransportByName(@Query("bus[]") name: String): Call<List<TransportOnline>>

}