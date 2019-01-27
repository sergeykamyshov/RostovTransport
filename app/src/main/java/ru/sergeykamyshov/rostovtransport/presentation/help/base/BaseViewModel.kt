package ru.sergeykamyshov.rostovtransport.presentation.help.base

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import io.reactivex.disposables.Disposable
import ru.sergeykamyshov.rostovtransport.domain.help.Contact

abstract class BaseViewModel : ViewModel() {

    lateinit var disposable: Disposable

    abstract fun getData(): LiveData<List<Contact>>

    abstract fun loadData()

    override fun onCleared() {
        disposable.dispose()
        super.onCleared()
    }
}