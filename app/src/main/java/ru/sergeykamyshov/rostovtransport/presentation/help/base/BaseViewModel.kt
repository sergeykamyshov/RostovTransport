package ru.sergeykamyshov.rostovtransport.presentation.help.base

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import io.reactivex.disposables.Disposable
import ru.sergeykamyshov.rostovtransport.domain.help.Contact

abstract class BaseViewModel : ViewModel() {

    protected var data = MutableLiveData<List<Contact>>()
    lateinit var disposable: Disposable

    fun getData(): LiveData<List<Contact>> {
        if (data.value == null) {
            loadData()
        }
        return data
    }

    abstract fun loadData()

    override fun onCleared() {
        disposable.dispose()
        super.onCleared()
    }
}