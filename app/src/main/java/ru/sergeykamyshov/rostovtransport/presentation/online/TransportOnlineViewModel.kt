package ru.sergeykamyshov.rostovtransport.presentation.online

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import ru.sergeykamyshov.rostovtransport.App
import ru.sergeykamyshov.rostovtransport.data.json.JsonDataApi
import ru.sergeykamyshov.rostovtransport.data.online.OnlineTransportApi
import ru.sergeykamyshov.rostovtransport.data.models.online.Transport
import ru.sergeykamyshov.rostovtransport.data.models.online.TransportOnline
import java.util.concurrent.TimeUnit

class TransportOnlineViewModel : ViewModel() {

    private val mUpdateInterval: Long = 5
    val jsonDataApi: JsonDataApi = App.provider.api.jsonDataApi
    private var mTransportList = MutableLiveData<List<Transport.Item>>()
    val onlineTransportApi: OnlineTransportApi = App.provider.api.onlineTransportApi
    private var mTransportOnline = MutableLiveData<List<TransportOnline>>()
    private var isRunning = true

    fun getTransportList(): LiveData<List<Transport.Item>> {
        return mTransportList
    }

    fun loadTransportList() {
        val call = jsonDataApi.getTransportList()
        call.enqueue(object : Callback<Transport> {
            override fun onResponse(call: Call<Transport>?, response: Response<Transport>?) {
                val transportList = response?.body()
                Log.i("TransportOnlineVM", "Last update: ${transportList?.lastUpdate}")
                mTransportList.postValue(transportList?.transport)
            }

            override fun onFailure(call: Call<Transport>?, t: Throwable?) {
                Log.i("TransportOnlineVM", "Failed to get transport list: $t")
            }
        })
    }

    fun getTransportOnline(): LiveData<List<TransportOnline>> {
        return mTransportOnline
    }

    fun setRunning(isRunning: Boolean) {
        this.isRunning = isRunning
    }

    fun loadTransportOnline(transportName: String) {
        Thread(Runnable {
            while (isRunning) {
                val call = onlineTransportApi.getTransportByName(transportName)
                call.enqueue(OnlineRestServiceCallback())

                TimeUnit.SECONDS.sleep(mUpdateInterval)
            }
        }).start()
    }

    inner class OnlineRestServiceCallback : Callback<List<TransportOnline>> {
        override fun onResponse(call: Call<List<TransportOnline>>?, response: Response<List<TransportOnline>>?) {
            val transportOnline = response?.body()
            Log.i("TransportOnlineVM", "Number of transport online: ${transportOnline?.size}")
            mTransportOnline.postValue(transportOnline)
        }

        override fun onFailure(call: Call<List<TransportOnline>>?, t: Throwable?) {
            Log.i("TransportOnlineVM", "Failed to get transport online: $t")
        }
    }

}