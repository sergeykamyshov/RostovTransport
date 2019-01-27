package ru.sergeykamyshov.rostovtransport.presentation.help.business

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import ru.sergeykamyshov.rostovtransport.App
import ru.sergeykamyshov.rostovtransport.domain.help.Contact
import ru.sergeykamyshov.rostovtransport.presentation.help.base.BaseViewModel
import timber.log.Timber

class BusinessViewModel : BaseViewModel() {

    private val getBusiness = App.provider.useCase.getBusiness
    private var data = MutableLiveData<List<Contact>>()

    override fun getData(): LiveData<List<Contact>> {
        return data
    }

    override fun loadData() {
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