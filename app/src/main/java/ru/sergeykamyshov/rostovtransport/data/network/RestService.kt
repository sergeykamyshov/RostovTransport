package ru.sergeykamyshov.rostovtransport.data.network

import retrofit2.Call
import retrofit2.http.GET
import ru.sergeykamyshov.rostovtransport.data.network.news.News
import ru.sergeykamyshov.rostovtransport.data.network.schedule.Directions

interface RestService {

    @GET("/?json=get_recent_posts")
    fun getRecentNews(): Call<News>

    @GET("/tmp/rostov-transport/test/directions.json")
    fun getDirections(): Call<Directions>

}