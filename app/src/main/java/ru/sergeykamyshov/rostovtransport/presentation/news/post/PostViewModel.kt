package ru.sergeykamyshov.rostovtransport.presentation.news.post

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import ru.sergeykamyshov.rostovtransport.App
import ru.sergeykamyshov.rostovtransport.domain.news.Post
import timber.log.Timber

class PostViewModel(private var id: String) : ViewModel() {

    private val getPost = App.provider.useCase.getPost
    private var data = MutableLiveData<Post>()
    private var loading = MutableLiveData<Boolean>()
    private var error = MutableLiveData<Boolean>()

    private lateinit var disposable: Disposable

    fun getData(): LiveData<Post> = data
    fun isLoading(): LiveData<Boolean> = loading
    fun isError(): LiveData<Boolean> = error

    fun loadData() {
        disposable = getPost.execute(id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    loading.value = false
                    data.postValue(it)
                }, {
                    Timber.e(it)
                    loading.value = false
                    error.value = true
                })
    }

    override fun onCleared() {
        disposable.dispose()
        super.onCleared()
    }

    fun initError(throwable: Throwable) {
        Timber.e(throwable)
        error.value = true
    }
}