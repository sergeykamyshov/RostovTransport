package ru.sergeykamyshov.rostovtransport.presentation.transport.map

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import ru.sergeykamyshov.rostovtransport.App
import ru.sergeykamyshov.rostovtransport.base.DisposableViewModel
import ru.sergeykamyshov.rostovtransport.domain.transport.Transport
import timber.log.Timber

class TransportViewModel : DisposableViewModel() {

    private val getTransport = App.provider.useCase.getTransport
    private val data = MutableLiveData<List<Transport>>()

    fun getData(): LiveData<List<Transport>> {
        return data
    }

    fun loadTransport(route: String) {
        val disposable = getTransport.execute(route)
                .subscribeOn(Schedulers.io())
                .subscribeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    data.postValue(it)
                }, {
                    // TODO: add error dialog
                    Timber.e("Error while loading transport route: $route")
                })
        disposables.add(disposable)
    }

}