package ru.sergeykamyshov.rostovtransport.presentation.card.deposit

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import ru.sergeykamyshov.rostovtransport.App
import ru.sergeykamyshov.rostovtransport.base.DisposableViewModel
import ru.sergeykamyshov.rostovtransport.base.states.*
import ru.sergeykamyshov.rostovtransport.domain.card.DepositAddress
import ru.sergeykamyshov.rostovtransport.domain.card.GetDepositAddresses

class CardDepositViewModel : DisposableViewModel() {

    private val getDepositAddresses: GetDepositAddresses = App.provider.useCase.getDepositAddresses
    private var data = MutableLiveData<List<DepositAddress>>()
    private var uiState = MutableLiveData<UIState>(Loading)

    fun getUiState(): LiveData<UIState> = uiState

    fun getData(): LiveData<List<DepositAddress>> {
        if (data.value == null) {
            loadData()
        }
        return data
    }

    fun loadData() {
        uiState.value = Loading
        val disposable = getDepositAddresses.execute()
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
                })
        disposables.add(disposable)
    }

}