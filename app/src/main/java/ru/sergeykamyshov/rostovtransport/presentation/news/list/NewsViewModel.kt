package ru.sergeykamyshov.rostovtransport.presentation.news.list

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import ru.sergeykamyshov.rostovtransport.App
import ru.sergeykamyshov.rostovtransport.data.network.NewsRestService
import ru.sergeykamyshov.rostovtransport.data.network.model.news.News
import ru.sergeykamyshov.rostovtransport.data.network.model.news.News.Post

class NewsViewModel : ViewModel() {

    val restService: NewsRestService = App.createNewsRestService()
    private var data = MutableLiveData<List<Post>>()

    fun getData(): LiveData<List<Post>> {
        return data
    }

    fun loadData() {
        val call = restService.getRecentNews()
        call.enqueue(object : Callback<News> {
            override fun onResponse(call: Call<News>?, response: Response<News>?) {
                val news = response?.body()
                data.postValue(news?.posts)
            }

            override fun onFailure(call: Call<News>?, t: Throwable?) {
                Log.i("NewsFragment", "Failed to get recent posts: $t")
            }
        })
    }

}