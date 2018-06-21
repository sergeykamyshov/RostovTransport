package ru.sergeykamyshov.rostovtransport.ui.routes.base

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.ViewModel
import ru.sergeykamyshov.rostovtransport.data.network.model.routes.Routes

abstract class BaseViewModel : ViewModel() {

    abstract fun getData(): LiveData<List<Routes.Route>>

    abstract fun loadData()

}