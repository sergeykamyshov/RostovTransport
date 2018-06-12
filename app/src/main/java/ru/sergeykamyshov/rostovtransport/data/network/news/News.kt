package ru.sergeykamyshov.rostovtransport.data.network.news

import com.google.gson.annotations.SerializedName

class News {

    @SerializedName("status")
    lateinit var status: String

    @SerializedName("posts")
    lateinit var posts: List<Post>

}