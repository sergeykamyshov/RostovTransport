package ru.sergeykamyshov.rostovtransport.data.json

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import ru.sergeykamyshov.rostovtransport.data.models.routes.RouteInfo
import ru.sergeykamyshov.rostovtransport.data.models.routes.Routes
import ru.sergeykamyshov.rostovtransport.data.models.schedule.Directions

interface JsonDataApi {

    // Маршруты
    @GET("routes/{transport}/{transport}.json")
    fun getRoutesFor(@Path("transport") transport: String): Call<Routes>

    @GET("routes/{transport}/{route}.json")
    fun getRoute(@Path("transport") transport: String, @Path("route") route: String): Call<RouteInfo>

    // Расписание
    @GET("schedule/directions.json")
    fun getDirections(): Call<Directions>

}