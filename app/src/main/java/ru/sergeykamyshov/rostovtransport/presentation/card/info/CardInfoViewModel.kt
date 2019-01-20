package ru.sergeykamyshov.rostovtransport.presentation.card.info

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import ru.sergeykamyshov.rostovtransport.App
import ru.sergeykamyshov.rostovtransport.data.json.JsonDataApi
import ru.sergeykamyshov.rostovtransport.data.models.card.CardInfo

class CardInfoViewModel : ViewModel() {

    val jsonDataApi: JsonDataApi = App.provider.api.jsonDataApi
    private var data = MutableLiveData<CardInfo>()

    fun getData(): LiveData<CardInfo> {
        return data
    }

    fun loadData() {
        val call = jsonDataApi.getCardInfo()
        call.enqueue(object : Callback<CardInfo> {
            override fun onResponse(call: Call<CardInfo>?, response: Response<CardInfo>?) {
                val info = response?.body()
                data.postValue(info)
            }

            override fun onFailure(call: Call<CardInfo>?, t: Throwable?) {
            }
        })
    }

}