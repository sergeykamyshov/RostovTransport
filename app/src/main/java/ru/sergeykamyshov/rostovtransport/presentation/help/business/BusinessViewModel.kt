package ru.sergeykamyshov.rostovtransport.presentation.help.business

import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import ru.sergeykamyshov.rostovtransport.App
import ru.sergeykamyshov.rostovtransport.presentation.help.base.BaseViewModel
import timber.log.Timber

class BusinessViewModel : BaseViewModel() {

    private val getBusiness = App.provider.useCase.getBusiness

    override fun loadData() {
        Timber.d("HP. BUS. Load data")
        disposable = getBusiness.execute()
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