package ru.sergeykamyshov.rostovtransport.presentation.routes.trolleybus

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.util.Log
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import ru.sergeykamyshov.rostovtransport.App
import ru.sergeykamyshov.rostovtransport.data.network.RestService
import ru.sergeykamyshov.rostovtransport.data.network.model.routes.Routes
import ru.sergeykamyshov.rostovtransport.presentation.routes.base.BaseViewModel

class TrolleybusListViewModel : BaseViewModel() {

    val restService: RestService = App.createRestService()
    private var data = MutableLiveData<List<Routes.Route>>()

    override fun getData(): LiveData<List<Routes.Route>> {
        return data
    }

    override fun loadData() {
        val call = restService.getRoutesFor("trolleybus")
        call.enqueue(object : Callback<Routes> {
            override fun onResponse(call: Call<Routes>?, response: Response<Routes>?) {
                val routes = response?.body()
                Log.i("TrolleybusListViewModel", "Last update: ${routes?.lastUpdate}")
                data.postValue(routes?.routes)
            }

            override fun onFailure(call: Call<Routes>?, t: Throwable?) {
                Log.i("TrolleybusListViewModel", "Failed to get routes: $t")
            }
        })
    }

}