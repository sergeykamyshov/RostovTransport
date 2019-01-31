package ru.sergeykamyshov.rostovtransport.presentation.news.post

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import ru.sergeykamyshov.rostovtransport.App
import ru.sergeykamyshov.rostovtransport.base.states.Error
import ru.sergeykamyshov.rostovtransport.base.states.HasData
import ru.sergeykamyshov.rostovtransport.base.states.Loading
import ru.sergeykamyshov.rostovtransport.base.states.UIState
import ru.sergeykamyshov.rostovtransport.domain.news.Post
import timber.log.Timber

class PostViewModel(private val id: String) : ViewModel() {

    private val getPost = App.provider.useCase.getPost
    private var uiState = MutableLiveData<UIState>(Loading)
    private var data = MutableLiveData<Post>()
    private lateinit var disposable: Disposable

    fun getUiState(): LiveData<UIState> = uiState

    fun getData(): LiveData<Post> {
        if (data.value == null) {
            loadData()
        }
        return data
    }

    fun loadData() {
        uiState.value = Loading
        disposable = getPost.execute(id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ post ->
                    uiState.value = HasData
                    data.postValue(post)
                }, {
                    (::processError)(it)
                })
    }

    override fun onCleared() {
        disposable.dispose()
        super.onCleared()
    }

    fun initError(throwable: Throwable) {
        processError(throwable)
    }

    private fun processError(throwable: Throwable) {
        Timber.e(throwable)
        uiState.value = Error
    }
}