package ru.sergeykamyshov.rostovtransport.presentation.routes.base

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import ru.sergeykamyshov.rostovtransport.App
import ru.sergeykamyshov.rostovtransport.data.json.JsonDataApi
import ru.sergeykamyshov.rostovtransport.data.models.routes.RouteInfo

class RouteMapViewModel(var type: String, var id: String) : ViewModel() {

    val jsonDataApi: JsonDataApi = App.provider.api.jsonDataApi
    private var data = MutableLiveData<RouteInfo>()

    fun getData(): LiveData<RouteInfo> {
        return data
    }

    fun loadData() {
        val call = jsonDataApi.getRoute(type, id)
        call.enqueue(object : Callback<RouteInfo> {
            override fun onResponse(call: Call<RouteInfo>?, response: Response<RouteInfo>?) {
                val routeInfo = response?.body()
                data.postValue(routeInfo)
            }

            override fun onFailure(call: Call<RouteInfo>?, t: Throwable?) {
            }
        })
    }

}