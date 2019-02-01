package ru.sergeykamyshov.rostovtransport.presentation.help.stations

import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import ru.sergeykamyshov.rostovtransport.App
import ru.sergeykamyshov.rostovtransport.base.states.Error
import ru.sergeykamyshov.rostovtransport.base.states.HasData
import ru.sergeykamyshov.rostovtransport.base.states.Loading
import ru.sergeykamyshov.rostovtransport.base.states.NoData
import ru.sergeykamyshov.rostovtransport.presentation.help.base.BaseViewModel
import timber.log.Timber

class StationsViewModel : BaseViewModel() {

    private val getStations = App.provider.useCase.getStations

    override fun loadData() {
        uiState.value = Loading
        disposables.add(getStations.execute()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ contacts ->
                    if (contacts.isNotEmpty()) {
                        uiState.value = HasData
                        data.postValue(contacts)
                    } else {
                        uiState.value = NoData
                    }
                }, {
                    Timber.e(it)
                    uiState.value = Error
                })
        )
    }

}