package ru.sergeykamyshov.rostovtransport.presentation.card.deposit

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import ru.sergeykamyshov.rostovtransport.App
import ru.sergeykamyshov.rostovtransport.data.json.JsonDataApi
import ru.sergeykamyshov.rostovtransport.domain.card.DepositAddress

class CardDepositViewModel : ViewModel() {

    val jsonDataApi: JsonDataApi = App.provider.api.jsonDataApi
    private var data = MutableLiveData<List<DepositAddress>>()

    fun getData(): LiveData<List<DepositAddress>> {
        if (data.value == null) {
            loadData()
        }
        return data
    }

    // TODO: impl load data from room
    fun loadData() {
        val list = ArrayList<DepositAddress>()
        for (i in 0..5) {
            list.add(DepositAddress(1, "test desc", "test address", "test schdule", "47.226766, 39.726799"))
        }
        data.postValue(list)
    }

}