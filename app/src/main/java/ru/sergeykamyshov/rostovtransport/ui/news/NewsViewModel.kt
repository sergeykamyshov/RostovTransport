package ru.sergeykamyshov.rostovtransport.ui.news

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import android.util.Log
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import ru.sergeykamyshov.rostovtransport.App
import ru.sergeykamyshov.rostovtransport.data.network.RestService
import ru.sergeykamyshov.rostovtransport.data.network.news.News
import ru.sergeykamyshov.rostovtransport.data.network.news.Post

class NewsViewModel : ViewModel() {

    private var mData = MutableLiveData<List<Post>>()
    lateinit var restService: RestService

    fun getData(): LiveData<List<Post>> {
        restService = App.retrofit.create(RestService::class.java)
        return mData
    }

    fun loadData() {
        val call = restService.getRecentNews()
        call.enqueue(object : Callback<News> {
            override fun onResponse(call: Call<News>?, response: Response<News>?) {
                val news = response?.body()
                mData.postValue(news?.posts)
            }

            override fun onFailure(call: Call<News>?, t: Throwable?) {
                Log.i("NewsFragment", "Failed to get recent posts: $t")
            }
        })
    }

}