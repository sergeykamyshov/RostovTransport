package ru.sergeykamyshov.rostovtransport.ui.routes.tram

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.util.Log
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import ru.sergeykamyshov.rostovtransport.App
import ru.sergeykamyshov.rostovtransport.data.network.RestService
import ru.sergeykamyshov.rostovtransport.data.network.model.routes.Routes
import ru.sergeykamyshov.rostovtransport.ui.routes.base.BaseViewModel

class TramListViewModel : BaseViewModel() {

    private var mData = MutableLiveData<List<Routes.Route>>()
    lateinit var restService: RestService

    override fun getData(): LiveData<List<Routes.Route>> {
        restService = App.retrofit.create(RestService::class.java)
        return mData
    }

    override fun loadData() {
        val call = restService.getRoutesFor("tram")
        call.enqueue(object : Callback<Routes> {
            override fun onResponse(call: Call<Routes>?, response: Response<Routes>?) {
                val routes = response?.body()
                Log.i("TramListViewModel", "Last update: ${routes?.lastUpdate}")
                mData.postValue(routes?.routes)
            }

            override fun onFailure(call: Call<Routes>?, t: Throwable?) {
                Log.i("TramListViewModel", "Failed to get routes: $t")
            }
        })
    }

}