package ru.sergeykamyshov.rostovtransport.data.models.news.post

import com.google.gson.annotations.SerializedName
import ru.sergeykamyshov.rostovtransport.data.models.news.News

class Post {

    @SerializedName("status")
    lateinit var status: String

    @SerializedName("post")
    lateinit var post: News.Post
}