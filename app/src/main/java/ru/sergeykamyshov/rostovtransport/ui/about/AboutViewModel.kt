package ru.sergeykamyshov.rostovtransport.ui.about

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import android.util.Log
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import ru.sergeykamyshov.rostovtransport.App
import ru.sergeykamyshov.rostovtransport.data.network.RestService
import ru.sergeykamyshov.rostovtransport.data.network.model.about.About

class AboutViewModel : ViewModel() {

    val restService: RestService = App.createRestService()
    private var mData = MutableLiveData<About>()

    init {
        loadData()
    }

    fun getData(): LiveData<About> {
        return mData
    }

    fun loadData() {
        val call = restService.getAbout()
        call.enqueue(object : Callback<About> {
            override fun onResponse(call: Call<About>?, response: Response<About>?) {
                mData.postValue(response?.body())
            }

            override fun onFailure(call: Call<About>?, t: Throwable?) {
                Log.i("AboutViewModel", "Failed to get about.json")
            }
        })
    }
}