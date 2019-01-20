package ru.sergeykamyshov.rostovtransport.presentation.routes.trolleybus

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import ru.sergeykamyshov.rostovtransport.App
import ru.sergeykamyshov.rostovtransport.data.json.JsonDataApi
import ru.sergeykamyshov.rostovtransport.data.models.routes.Routes
import ru.sergeykamyshov.rostovtransport.presentation.routes.base.BaseViewModel

class TrolleybusListViewModel : BaseViewModel() {

    val jsonDataApi: JsonDataApi = App.provider.api.jsonDataApi
    private var data = MutableLiveData<List<Routes.Route>>()

    override fun getData(): LiveData<List<Routes.Route>> {
        return data
    }

    override fun loadData() {
        val call = jsonDataApi.getRoutesFor("trolleybus")
        call.enqueue(object : Callback<Routes> {
            override fun onResponse(call: Call<Routes>?, response: Response<Routes>?) {
                val routes = response?.body()
                data.postValue(routes?.routes)
            }

            override fun onFailure(call: Call<Routes>?, t: Throwable?) {
            }
        })
    }

}