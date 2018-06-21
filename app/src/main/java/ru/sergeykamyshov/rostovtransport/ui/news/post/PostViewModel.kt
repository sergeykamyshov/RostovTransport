package ru.sergeykamyshov.rostovtransport.ui.news.post

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import android.util.Log
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import ru.sergeykamyshov.rostovtransport.App
import ru.sergeykamyshov.rostovtransport.data.network.NewsRestService
import ru.sergeykamyshov.rostovtransport.data.network.model.news.News
import ru.sergeykamyshov.rostovtransport.data.network.model.news.post.Post

class PostViewModel(var id: String) : ViewModel() {

    val restService: NewsRestService = App.createNewsRestService()
    private var mData = MutableLiveData<News.Post>()

    fun getData(): LiveData<News.Post> {
        return mData
    }

    fun loadData() {
        val call = restService.getNewsById(id)
        call.enqueue(object : Callback<Post> {
            override fun onResponse(call: Call<Post>?, response: Response<Post>?) {
                Log.i("PostViewModel", "success")
                val news = response?.body()
                val post = news?.post
                Log.i("PostViewModel", "id=${post?.id}, title=${post?.title}")
                mData.postValue(post)
            }

            override fun onFailure(call: Call<Post>?, t: Throwable?) {
                Log.i("PostViewModel", "failed")
            }
        })
    }

}