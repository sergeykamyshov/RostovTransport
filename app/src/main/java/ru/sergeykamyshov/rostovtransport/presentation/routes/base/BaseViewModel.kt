package ru.sergeykamyshov.rostovtransport.presentation.routes.base

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import ru.sergeykamyshov.rostovtransport.data.models.routes.Routes

abstract class BaseViewModel : ViewModel() {

    abstract fun getData(): LiveData<List<Routes.Route>>

    abstract fun loadData()

}