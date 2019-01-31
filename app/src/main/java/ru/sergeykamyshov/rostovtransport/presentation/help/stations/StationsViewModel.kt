package ru.sergeykamyshov.rostovtransport.presentation.help.stations

import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import ru.sergeykamyshov.rostovtransport.App
import ru.sergeykamyshov.rostovtransport.presentation.help.base.BaseViewModel
import timber.log.Timber

class StationsViewModel : BaseViewModel() {

    private val getStations = App.provider.useCase.getStations

    override fun loadData() {
        Timber.d("HP. STA. Load data")
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