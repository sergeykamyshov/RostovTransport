package ru.sergeykamyshov.rostovtransport.ui.news

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
import ru.sergeykamyshov.rostovtransport.data.network.model.news.News.Post

class NewsViewModel : ViewModel() {

    val restService: NewsRestService = App.createNewsRestService()
    private var mData = MutableLiveData<List<Post>>()

    fun getData(): LiveData<List<Post>> {
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