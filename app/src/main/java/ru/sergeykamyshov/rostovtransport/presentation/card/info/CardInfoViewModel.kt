package ru.sergeykamyshov.rostovtransport.presentation.card.info

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import android.util.Log
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import ru.sergeykamyshov.rostovtransport.App
import ru.sergeykamyshov.rostovtransport.data.network.RestService
import ru.sergeykamyshov.rostovtransport.data.network.model.card.CardInfo

class CardInfoViewModel : ViewModel() {

    val restService: RestService = App.createRestService()
    private var mData = MutableLiveData<CardInfo>()

    fun getData(): LiveData<CardInfo> {
        return mData
    }

    fun loadData() {
        val call = restService.getCardInfo()
        call.enqueue(object : Callback<CardInfo> {
            override fun onResponse(call: Call<CardInfo>?, response: Response<CardInfo>?) {
                val info = response?.body()
                Log.i("CardInfoViewModel", "Last update: ${info?.lastUpdate}")
                mData.postValue(info)
            }

            override fun onFailure(call: Call<CardInfo>?, t: Throwable?) {
                Log.i("CardInfoViewModel", "Failed to get card info: $t")
            }
        })
    }

}