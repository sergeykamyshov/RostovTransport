package ru.sergeykamyshov.rostovtransport.presentation.about

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import android.util.Log
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import ru.sergeykamyshov.rostovtransport.App
import ru.sergeykamyshov.rostovtransport.data.network.RestService
import ru.sergeykamyshov.rostovtransport.data.network.model.about.About

class AboutViewModel : ViewModel() {

    val restService: RestService = App.restService
    private var data = MutableLiveData<About>()

    init {
        loadData()
    }

    fun getData(): LiveData<About> {
        return data
    }

    fun loadData() {
        val call = restService.getAbout()
        call.enqueue(object : Callback<About> {
            override fun onResponse(call: Call<About>?, response: Response<About>?) {
                data.postValue(response?.body())
            }

            override fun onFailure(call: Call<About>?, t: Throwable?) {
                Log.i("AboutViewModel", "Failed to get about.json")
            }
        })
    }
}