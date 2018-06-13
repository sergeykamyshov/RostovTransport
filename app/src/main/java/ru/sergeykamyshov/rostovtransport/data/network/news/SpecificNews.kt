package ru.sergeykamyshov.rostovtransport.data.network.news

import com.google.gson.annotations.SerializedName

class SpecificNews {

    @SerializedName("status")
    lateinit var status: String

    @SerializedName("post")
    lateinit var post: Post
}