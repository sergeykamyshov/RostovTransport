package ru.sergeykamyshov.rostovtransport.presentation.card.deposit

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import ru.sergeykamyshov.rostovtransport.App
import ru.sergeykamyshov.rostovtransport.domain.card.DepositAddress
import ru.sergeykamyshov.rostovtransport.domain.card.GetDepositAddresses
import timber.log.Timber

class CardDepositViewModel : ViewModel() {

    private val getDepositAddresses: GetDepositAddresses = App.provider.useCase.getDepositAddresses
    private var data = MutableLiveData<List<DepositAddress>>()
    private var disposables = CompositeDisposable()

    fun getData(): LiveData<List<DepositAddress>> {
        if (data.value == null) {
            loadData()
        }
        return data
    }

    fun loadData() {
        disposables.add(getDepositAddresses.execute()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    Timber.d("Received ${it.size} items")
                    data.postValue(it)
                }, {
                    Timber.d("Some error. $it")
                }))
    }

    override fun onCleared() {
        disposables.clear()
        super.onCleared()
    }

}