package ru.sergeykamyshov.rostovtransport.data.transport

import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface TransportApi {

    @GET("/get_bus")
    fun getTransport(@Query("bus[]") name: String): Single<List<GetBusResponse>>

}