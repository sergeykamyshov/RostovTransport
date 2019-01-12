package ru.sergeykamyshov.rostovtransport.presentation.help.base

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.ViewModel
import ru.sergeykamyshov.rostovtransport.data.network.model.help.Help

abstract class BaseViewModel : ViewModel() {

    abstract fun getData(): LiveData<List<Help.Contact>>

    abstract fun loadData()

}