package ru.sergeykamyshov.rostovtransport.presentation.help.business

import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import ru.sergeykamyshov.rostovtransport.App
import ru.sergeykamyshov.rostovtransport.base.states.Error
import ru.sergeykamyshov.rostovtransport.base.states.HasData
import ru.sergeykamyshov.rostovtransport.base.states.Loading
import ru.sergeykamyshov.rostovtransport.base.states.NoData
import ru.sergeykamyshov.rostovtransport.presentation.help.base.BaseViewModel
import timber.log.Timber

class BusinessViewModel : BaseViewModel() {

    private val getBusiness = App.provider.useCase.getBusiness

    override fun loadData() {
        uiState.value = Loading
        disposable = getBusiness.execute()
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
    }

}