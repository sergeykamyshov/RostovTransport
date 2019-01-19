package ru.sergeykamyshov.rostovtransport.data.online

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query
import ru.sergeykamyshov.rostovtransport.data.models.online.TransportOnline

interface OnlineTransportApi {

    @GET("/get_bus")
    fun getTransportByName(@Query("bus[]") name: String): Call<List<TransportOnline>>

}