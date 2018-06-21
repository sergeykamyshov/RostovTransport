package ru.sergeykamyshov.rostovtransport.data.network

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query
import ru.sergeykamyshov.rostovtransport.data.network.model.news.News
import ru.sergeykamyshov.rostovtransport.data.network.model.news.post.Post

interface NewsRestService {

    @GET("/?json=get_recent_posts")
    fun getRecentNews(): Call<News>

    @GET("/?json=get_post")
    fun getNewsById(@Query("post_id") id: String): Call<Post>

}