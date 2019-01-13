package ru.sergeykamyshov.rostovtransport.presentation.card.deposit

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import ru.sergeykamyshov.rostovtransport.App
import ru.sergeykamyshov.rostovtransport.data.network.RestService
import ru.sergeykamyshov.rostovtransport.data.network.model.card.CardDeposit

class CardDepositViewModel : ViewModel() {

    val restService: RestService = App.createRestService()
    private var data = MutableLiveData<List<CardDeposit.Address>>()

    fun getData(): LiveData<List<CardDeposit.Address>> {
        return data
    }

    fun loadData() {
        val call = restService.getCardDeposit()
        call.enqueue(object : Callback<CardDeposit> {
            override fun onResponse(call: Call<CardDeposit>?, response: Response<CardDeposit>?) {
                val cardDeposit = response?.body()
                Log.i("CardDepositViewModel", "Last update: ${cardDeposit?.lastUpdate}")
                data.postValue(cardDeposit?.addresses)
            }

            override fun onFailure(call: Call<CardDeposit>?, t: Throwable?) {
                Log.i("CardDepositViewModel", "Failed to get card deposit address: $t")
            }
        })
    }

}