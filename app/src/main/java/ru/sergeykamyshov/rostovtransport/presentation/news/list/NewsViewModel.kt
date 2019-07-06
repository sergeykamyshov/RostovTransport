package ru.sergeykamyshov.rostovtransport.presentation.news.list

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import ru.sergeykamyshov.rostovtransport.App
import ru.sergeykamyshov.rostovtransport.base.DisposableViewModel
import ru.sergeykamyshov.rostovtransport.base.states.*
import ru.sergeykamyshov.rostovtransport.domain.news.GetRecentNews
import ru.sergeykamyshov.rostovtransport.domain.news.Post

class NewsViewModel : DisposableViewModel() {

    private val getRecentNews: GetRecentNews = App.provider.useCase.getRecentNews
    private val uiState = MutableLiveData<UIState>(Loading)
    private val data = MutableLiveData<List<Post>>()

    fun getUiState(): LiveData<UIState> = uiState

    fun getData(): LiveData<List<Post>> {
        if (data.value == null) {
            loadData()
        }
        return data
    }

    fun loadData() {
        uiState.value = Loading
        val disposable = getRecentNews.execute()
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
        disposables.add(disposable)
    }

}