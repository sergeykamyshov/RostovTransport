package ru.sergeykamyshov.rostovtransport.data.network

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import ru.sergeykamyshov.rostovtransport.data.network.about.About
import ru.sergeykamyshov.rostovtransport.data.network.news.News
import ru.sergeykamyshov.rostovtransport.data.network.news.post.Post
import ru.sergeykamyshov.rostovtransport.data.network.routes.Routes
import ru.sergeykamyshov.rostovtransport.data.network.schedule.Directions

interface RestService {

    // Новости
    @GET("/?json=get_recent_posts")
    fun getRecentNews(): Call<News>

    @GET("/?json=get_post")
    fun getNewsById(@Query("post_id") id: String): Call<Post>

    // Маршруты
    @GET("/tmp/rostov-transport/test/routes/{transport}/{transport}.json")
    fun getRoutesFor(@Path("transport") transport: String): Call<Routes>

    // Расписание
    @GET("/tmp/rostov-transport/test/directions.json")
    fun getDirections(): Call<Directions>

    // О проекте
    @GET("/tmp/rostov-transport/test/about.json")
    fun getAbout(): Call<About>

}