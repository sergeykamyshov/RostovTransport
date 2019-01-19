package ru.sergeykamyshov.rostovtransport.presentation.card.buy

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import ru.sergeykamyshov.rostovtransport.App
import ru.sergeykamyshov.rostovtransport.data.json.JsonDataApi
import ru.sergeykamyshov.rostovtransport.data.models.card.CardBuy

class CardBuyViewModel : ViewModel() {

    val jsonDataApi: JsonDataApi = App.provider.api.jsonDataApi
    private var data = MutableLiveData<List<CardBuy.Address>>()

    fun getData(): LiveData<List<CardBuy.Address>> {
        return data
    }

    fun loadData() {
        val call = jsonDataApi.getCardBuy()
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