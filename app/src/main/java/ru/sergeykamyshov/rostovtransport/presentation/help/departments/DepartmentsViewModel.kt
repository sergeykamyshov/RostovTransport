package ru.sergeykamyshov.rostovtransport.presentation.help.departments

import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import ru.sergeykamyshov.rostovtransport.App
import ru.sergeykamyshov.rostovtransport.presentation.help.base.BaseViewModel
import timber.log.Timber

class DepartmentsViewModel : BaseViewModel() {

    private val getDepartments = App.provider.useCase.getDepartments

    override fun loadData() {
        Timber.d("HP. DEP. Load data")
        disposable = getDepartments.execute()
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