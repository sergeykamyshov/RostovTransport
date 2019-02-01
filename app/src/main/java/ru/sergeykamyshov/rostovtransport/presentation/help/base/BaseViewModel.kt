package ru.sergeykamyshov.rostovtransport.presentation.help.base

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import io.reactivex.disposables.CompositeDisposable
import ru.sergeykamyshov.rostovtransport.base.states.Loading
import ru.sergeykamyshov.rostovtransport.base.states.UIState
import ru.sergeykamyshov.rostovtransport.domain.help.Contact

abstract class BaseViewModel : ViewModel() {

    protected val data = MutableLiveData<List<Contact>>()
    protected val uiState = MutableLiveData<UIState>(Loading)
    protected var disposables = CompositeDisposable()

    fun getUiState(): LiveData<UIState> = uiState

    fun getData(): LiveData<List<Contact>> {
        if (data.value == null) {
            loadData()
        }
        return data
    }

    abstract fun loadData()

    override fun onCleared() {
        disposables.clear()
        super.onCleared()
    }
}