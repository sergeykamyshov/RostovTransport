package ru.sergeykamyshov.rostovtransport.ui.news.post

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import android.util.Log
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import ru.sergeykamyshov.rostovtransport.App
import ru.sergeykamyshov.rostovtransport.data.network.RestService

class PostViewModel(var id: String) : ViewModel() {

    private var mData = MutableLiveData<ru.sergeykamyshov.rostovtransport.data.network.model.news.Post>()
    lateinit var restService: RestService

    fun getData(): LiveData<ru.sergeykamyshov.rostovtransport.data.network.model.news.Post> {
        restService = App.retrofit.create(RestService::class.java)
        return mData
    }

    fun loadData() {
        val call = restService.getNewsById(id)
        call.enqueue(object : Callback<ru.sergeykamyshov.rostovtransport.data.network.model.news.post.Post> {
            override fun onResponse(call: Call<ru.sergeykamyshov.rostovtransport.data.network.model.news.post.Post>?, response: Response<ru.sergeykamyshov.rostovtransport.data.network.model.news.post.Post>?) {
                Log.i("PostViewModel", "success")
                val news = response?.body()
                val post = news?.post
                Log.i("PostViewModel", "id=${post?.id}, title=${post?.title}")
                mData.postValue(post)
            }

            override fun onFailure(call: Call<ru.sergeykamyshov.rostovtransport.data.network.model.news.post.Post>?, t: Throwable?) {
                Log.i("PostViewModel", "failed")
            }
        })
    }

}