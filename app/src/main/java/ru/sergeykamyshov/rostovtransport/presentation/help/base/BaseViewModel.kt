package ru.sergeykamyshov.rostovtransport.presentation.help.base

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import ru.sergeykamyshov.rostovtransport.data.network.model.help.Help

abstract class BaseViewModel : ViewModel() {

    abstract fun getData(): LiveData<List<Help.Contact>>

    abstract fun loadData()

}