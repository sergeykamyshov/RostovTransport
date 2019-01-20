package ru.sergeykamyshov.rostovtransport.presentation.card.deposit

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import ru.sergeykamyshov.rostovtransport.App
import ru.sergeykamyshov.rostovtransport.data.json.JsonDataApi
import ru.sergeykamyshov.rostovtransport.data.models.card.CardDeposit

class CardDepositViewModel : ViewModel() {

    val jsonDataApi: JsonDataApi = App.provider.api.jsonDataApi
    private var data = MutableLiveData<List<CardDeposit.Address>>()

    fun getData(): LiveData<List<CardDeposit.Address>> {
        return data
    }

    fun loadData() {
        val call = jsonDataApi.getCardDeposit()
        call.enqueue(object : Callback<CardDeposit> {
            override fun onResponse(call: Call<CardDeposit>?, response: Response<CardDeposit>?) {
                val cardDeposit = response?.body()
                data.postValue(cardDeposit?.addresses)
            }

            override fun onFailure(call: Call<CardDeposit>?, t: Throwable?) {
            }
        })
    }

}