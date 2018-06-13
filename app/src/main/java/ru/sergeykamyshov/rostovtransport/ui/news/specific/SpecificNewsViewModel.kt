package ru.sergeykamyshov.rostovtransport.ui.news.specific

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import android.util.Log
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import ru.sergeykamyshov.rostovtransport.App
import ru.sergeykamyshov.rostovtransport.data.network.RestService
import ru.sergeykamyshov.rostovtransport.data.network.news.Post
import ru.sergeykamyshov.rostovtransport.data.network.news.SpecificNews

class SpecificNewsViewModel(var id: String) : ViewModel() {

    private var mData = MutableLiveData<Post>()
    lateinit var restService: RestService

    fun getData(): LiveData<Post> {
        restService = App.retrofit.create(RestService::class.java)
        return mData
    }

    fun loadData() {
        val call = restService.getNewsById(id)
        call.enqueue(object : Callback<SpecificNews> {
            override fun onResponse(call: Call<SpecificNews>?, response: Response<SpecificNews>?) {
                Log.i("SpecificNewsViewModel", "success")
                val news = response?.body()
                val post = news?.post
                Log.i("SpecificNewsViewModel", "id=${post?.id}, title=${post?.title}")
                mData.postValue(post)
            }

            override fun onFailure(call: Call<SpecificNews>?, t: Throwable?) {
                Log.i("SpecificNewsViewModel", "failed")
            }
        })
    }

}