package ru.sergeykamyshov.rostovtransport.presentation.card.deposit

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import ru.sergeykamyshov.rostovtransport.App
import ru.sergeykamyshov.rostovtransport.base.states.*
import ru.sergeykamyshov.rostovtransport.domain.card.DepositAddress
import ru.sergeykamyshov.rostovtransport.domain.card.GetDepositAddresses

class CardDepositViewModel : ViewModel() {

    private val getDepositAddresses: GetDepositAddresses = App.provider.useCase.getDepositAddresses
    private var data = MutableLiveData<List<DepositAddress>>()
    private var uiState = MutableLiveData<UIState>(Loading)
    private var disposables = CompositeDisposable()

    fun getUiState(): LiveData<UIState> = uiState

    fun getData(): LiveData<List<DepositAddress>> {
        if (data.value == null) {
            loadData()
        }
        return data
    }

    fun loadData() {
        uiState.value = Loading
        disposables.add(getDepositAddresses.execute()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    if (it.isNotEmpty()) {
                        uiState.value = HasData
                        data.postValue(it)
                    } else {
                        uiState.value = NoData
                    }
                }, {
                    uiState.value = Error
                }))
    }

    override fun onCleared() {
        disposables.clear()
        super.onCleared()
    }

}