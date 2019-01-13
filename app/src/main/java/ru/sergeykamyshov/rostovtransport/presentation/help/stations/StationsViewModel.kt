package ru.sergeykamyshov.rostovtransport.presentation.help.stations

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import ru.sergeykamyshov.rostovtransport.App
import ru.sergeykamyshov.rostovtransport.data.network.RestService
import ru.sergeykamyshov.rostovtransport.data.network.model.help.Help
import ru.sergeykamyshov.rostovtransport.presentation.help.base.BaseViewModel

class StationsViewModel : BaseViewModel() {

    val restService: RestService = App.createRestService()
    private var data = MutableLiveData<List<Help.Contact>>()

    override fun getData(): LiveData<List<Help.Contact>> {
        return data
    }

    override fun loadData() {
        val call = restService.getHelpFor("stations")
        call.enqueue(object : Callback<Help> {
            override fun onResponse(call: Call<Help>?, response: Response<Help>?) {
                val help = response?.body()
                Log.i("StationsViewModel", "Last update: ${help?.lastUpdate}")
                data.postValue(help?.contacts)
            }

            override fun onFailure(call: Call<Help>?, t: Throwable?) {
                Log.i("StationsViewModel", "Failed to get routes: $t")
            }
        })
    }

}