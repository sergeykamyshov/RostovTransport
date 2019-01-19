package ru.sergeykamyshov.rostovtransport.data.news

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query
import ru.sergeykamyshov.rostovtransport.data.models.news.News
import ru.sergeykamyshov.rostovtransport.data.models.news.post.Post

interface NewsApi {

    @GET("/?json=get_recent_posts")
    fun getRecentNews(): Call<News>

    @GET("/?json=get_post")
    fun getPostById(@Query("post_id") id: String): Call<Post>

}