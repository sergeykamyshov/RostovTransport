package ru.sergeykamyshov.rostovtransport.presentation.help.business

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.util.Log
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import ru.sergeykamyshov.rostovtransport.App
import ru.sergeykamyshov.rostovtransport.data.network.RestService
import ru.sergeykamyshov.rostovtransport.data.network.model.help.Help
import ru.sergeykamyshov.rostovtransport.presentation.help.base.BaseViewModel

class BusinessViewModel : BaseViewModel() {

    val restService: RestService = App.createRestService()
    private var mData = MutableLiveData<List<Help.Contact>>()

    override fun getData(): LiveData<List<Help.Contact>> {
        return mData
    }

    override fun loadData() {
        val call = restService.getHelpFor("business")
        call.enqueue(object : Callback<Help> {
            override fun onResponse(call: Call<Help>?, response: Response<Help>?) {
                val help = response?.body()
                Log.i("BusinessViewModel", "Last update: ${help?.lastUpdate}")
                mData.postValue(help?.contacts)
            }

            override fun onFailure(call: Call<Help>?, t: Throwable?) {
                Log.i("BusinessViewModel", "Failed to get routes: $t")
            }
        })
    }

}