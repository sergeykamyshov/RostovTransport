package ru.sergeykamyshov.rostovtransport.presentation.routes.base

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import android.util.Log
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import ru.sergeykamyshov.rostovtransport.App
import ru.sergeykamyshov.rostovtransport.data.network.RestService
import ru.sergeykamyshov.rostovtransport.data.network.model.routes.RouteInfo

class RouteMapViewModel(var type: String, var id: String) : ViewModel() {

    val restService: RestService = App.createRestService()
    private var mData = MutableLiveData<RouteInfo>()

    fun getData(): LiveData<RouteInfo> {
        return mData
    }

    fun loadData() {
        val call = restService.getRoute(type, id)
        call.enqueue(object : Callback<RouteInfo> {
            override fun onResponse(call: Call<RouteInfo>?, response: Response<RouteInfo>?) {
                val routeInfo = response?.body()
                Log.i("RouteMapViewModel", "Last update ${routeInfo?.lastUpdate}")
                mData.postValue(routeInfo)
            }

            override fun onFailure(call: Call<RouteInfo>?, t: Throwable?) {
                Log.i("RouteMapViewModel", "Failed")
            }
        })
    }

}