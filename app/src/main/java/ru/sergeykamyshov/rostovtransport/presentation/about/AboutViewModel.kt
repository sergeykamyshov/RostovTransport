package ru.sergeykamyshov.rostovtransport.presentation.about

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import ru.sergeykamyshov.rostovtransport.App
import ru.sergeykamyshov.rostovtransport.data.json.JsonDataApi
import ru.sergeykamyshov.rostovtransport.data.models.about.About

class AboutViewModel : ViewModel() {

    val jsonDataApi: JsonDataApi = App.provider.apiProvider.jsonDataApi
    private var data = MutableLiveData<About>()

    init {
        loadData()
    }

    fun getData(): LiveData<About> {
        return data
    }

    fun loadData() {
        val call = jsonDataApi.getAbout()
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