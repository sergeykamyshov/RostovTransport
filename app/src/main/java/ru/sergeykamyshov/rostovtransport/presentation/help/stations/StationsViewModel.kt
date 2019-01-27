package ru.sergeykamyshov.rostovtransport.presentation.help.stations

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import ru.sergeykamyshov.rostovtransport.App
import ru.sergeykamyshov.rostovtransport.domain.help.Contact
import ru.sergeykamyshov.rostovtransport.presentation.help.base.BaseViewModel
import timber.log.Timber

class StationsViewModel : BaseViewModel() {

    private val getStations = App.provider.useCase.getStations
    private val data = MutableLiveData<List<Contact>>()

    override fun getData(): LiveData<List<Contact>> {
        return data
    }

    override fun loadData() {
        disposable = getStations.execute()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ contacts ->
                    if (contacts.isNotEmpty()) {
                        data.postValue(contacts)
                    }
                }, {
                    Timber.e(it)
                })
    }

}