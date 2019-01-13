package ru.sergeykamyshov.rostovtransport.presentation.card.info

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import ru.sergeykamyshov.rostovtransport.App
import ru.sergeykamyshov.rostovtransport.data.network.RestService
import ru.sergeykamyshov.rostovtransport.data.network.model.card.CardInfo

class CardInfoViewModel : ViewModel() {

    val restService: RestService = App.createRestService()
    private var data = MutableLiveData<CardInfo>()

    fun getData(): LiveData<CardInfo> {
        return data
    }

    fun loadData() {
        val call = restService.getCardInfo()
        call.enqueue(object : Callback<CardInfo> {
            override fun onResponse(call: Call<CardInfo>?, response: Response<CardInfo>?) {
                val info = response?.body()
                Log.i("CardInfoViewModel", "Last update: ${info?.lastUpdate}")
                data.postValue(info)
            }

            override fun onFailure(call: Call<CardInfo>?, t: Throwable?) {
                Log.i("CardInfoViewModel", "Failed to get card info: $t")
            }
        })
    }

}