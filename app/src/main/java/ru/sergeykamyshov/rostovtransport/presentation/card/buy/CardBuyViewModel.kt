package ru.sergeykamyshov.rostovtransport.presentation.card.buy

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import ru.sergeykamyshov.rostovtransport.App
import ru.sergeykamyshov.rostovtransport.base.DisposableViewModel
import ru.sergeykamyshov.rostovtransport.base.states.*
import ru.sergeykamyshov.rostovtransport.domain.card.BuyAddress
import ru.sergeykamyshov.rostovtransport.domain.card.GetBuyAddresses

class CardBuyViewModel : DisposableViewModel() {

    private val getBuyAddresses: GetBuyAddresses = App.provider.useCase.getBuyAddresses
    private val data = MutableLiveData<List<BuyAddress>>()
    private var uiState = MutableLiveData<UIState>(Loading)

    fun getUiState(): LiveData<UIState> = uiState

    fun getData(): LiveData<List<BuyAddress>> {
        if (data.value == null) {
            loadData()
        }
        return data
    }

    fun loadData() {
        uiState.value = Loading
        val disposable = getBuyAddresses.execute()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ addresses ->
                    if (addresses.isNotEmpty()) {
                        uiState.value = HasData
                        data.postValue(addresses)
                    } else {
                        uiState.value = NoData
                    }
                }, {
                    uiState.value = Error
                })
        disposables.add(disposable)
    }

}