package ru.sergeykamyshov.rostovtransport.ui.help.base

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.ViewModel
import ru.sergeykamyshov.rostovtransport.data.network.help.Help

abstract class BaseViewModel : ViewModel() {

    abstract fun getData(): LiveData<List<Help.Contact>>

    abstract fun loadData()

}