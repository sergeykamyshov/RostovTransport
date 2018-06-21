package ru.sergeykamyshov.rostovtransport.data.network

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import ru.sergeykamyshov.rostovtransport.data.network.model.about.About
import ru.sergeykamyshov.rostovtransport.data.network.model.help.Help
import ru.sergeykamyshov.rostovtransport.data.network.model.news.post.Post
import ru.sergeykamyshov.rostovtransport.data.network.model.routes.Routes
import ru.sergeykamyshov.rostovtransport.data.network.model.schedule.Directions

interface RestService {

    // Маршруты
    @GET("/tmp/rostov-transport/test/routes/{transport}/{transport}.json")
    fun getRoutesFor(@Path("transport") transport: String): Call<Routes>

    // Справка
    @GET("/tmp/rostov-transport/test/help/{help}.json")
    fun getHelpFor(@Path("help") help: String): Call<Help>

    // Расписание
    @GET("/tmp/rostov-transport/test/directions.json")
    fun getDirections(): Call<Directions>

    // О проекте
    @GET("/tmp/rostov-transport/test/about.json")
    fun getAbout(): Call<About>

}