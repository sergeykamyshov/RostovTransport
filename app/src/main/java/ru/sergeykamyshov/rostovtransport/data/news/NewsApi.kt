package ru.sergeykamyshov.rostovtransport.data.news

import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsApi {

    @GET("/?json=get_recent_posts")
    fun getRecentNews(): Single<NewsResponse>

    @GET("/?json=get_post")
    fun getPost(@Query("post_id") id: Long): Single<PostResponse>

}