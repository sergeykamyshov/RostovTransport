package ru.sergeykamyshov.rostovtransport.presentation.news.list

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import ru.sergeykamyshov.rostovtransport.App
import ru.sergeykamyshov.rostovtransport.base.states.*
import ru.sergeykamyshov.rostovtransport.domain.news.GetRecentNews
import ru.sergeykamyshov.rostovtransport.domain.news.Post

class NewsViewModel : ViewModel() {

    private val getRecentNews: GetRecentNews = App.provider.useCase.getRecentNews
    private var uiState = MutableLiveData<UIState>(Loading)
    private var data = MutableLiveData<List<Post>>()
    private lateinit var disposable: Disposable

    fun getData(): LiveData<List<Post>> = data

    fun getUiState(): LiveData<UIState> = uiState

    fun loadData() {
        uiState.value = Loading
        disposable = getRecentNews.execute()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ posts ->
                    when (posts.isNotEmpty()) {
                        true -> {
                            data.postValue(posts)
                            uiState.value = HasData
                        }
                        false -> uiState.value = NoData
                    }
                }, {
                    uiState.value = Error
                })
    }

    override fun onCleared() {
        disposable.dispose()
        super.onCleared()
    }
}