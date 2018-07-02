package ru.sergeykamyshov.rostovtransport.ui.online

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import android.util.Log
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import ru.sergeykamyshov.rostovtransport.App
import ru.sergeykamyshov.rostovtransport.data.network.RestService
import ru.sergeykamyshov.rostovtransport.data.network.model.online.Transport

class TransportOnlineViewModel : ViewModel() {

    val restService: RestService = App.createRestService()
    private var mData = MutableLiveData<List<Transport.Item>>()

    fun getData(): LiveData<List<Transport.Item>> {
        return mData
    }

    fun loadData() {
        val call = restService.getTransportList()
        call.enqueue(object : Callback<Transport> {
            override fun onResponse(call: Call<Transport>?, response: Response<Transport>?) {
                val transportList = response?.body()
                Log.i("TOViewModel", "Last update: ${transportList?.lastUpdate}")
                mData.postValue(transportList?.transport)
            }

            override fun onFailure(call: Call<Transport>?, t: Throwable?) {
                Log.i("TOViewModel", "Failed to get transport list: $t")
            }
        })
    }

}