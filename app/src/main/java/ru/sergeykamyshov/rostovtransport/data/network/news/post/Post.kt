package ru.sergeykamyshov.rostovtransport.data.network.news.post

import com.google.gson.annotations.SerializedName
import ru.sergeykamyshov.rostovtransport.data.network.news.Post

class Post {

    @SerializedName("status")
    lateinit var status: String

    @SerializedName("post")
    lateinit var post: Post
}