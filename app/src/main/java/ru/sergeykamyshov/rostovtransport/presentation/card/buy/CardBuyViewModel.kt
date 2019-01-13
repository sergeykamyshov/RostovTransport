package ru.sergeykamyshov.rostovtransport.presentation.card.buy

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import android.util.Log
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import ru.sergeykamyshov.rostovtransport.App
import ru.sergeykamyshov.rostovtransport.data.network.RestService
import ru.sergeykamyshov.rostovtransport.data.network.model.card.CardBuy

class CardBuyViewModel : ViewModel() {

    val restService: RestService = App.createRestService()
    private var data = MutableLiveData<List<CardBuy.Address>>()

    fun getData(): LiveData<List<CardBuy.Address>> {
        return data
    }

    fun loadData() {
        val call = restService.getCardBuy()
        call.enqueue(object : Callback<CardBuy> {
            override fun onResponse(call: Call<CardBuy>?, response: Response<CardBuy>?) {
                val cardBuy = response?.body()
                Log.i("CardInfoViewModel", "Last update: ${cardBuy?.lastUpdate}")
                data.postValue(cardBuy?.addresses)
            }

            override fun onFailure(call: Call<CardBuy>?, t: Throwable?) {
                Log.i("CardBuyViewModel", "Failed to get card buy address: $t")
            }
        })
    }

}