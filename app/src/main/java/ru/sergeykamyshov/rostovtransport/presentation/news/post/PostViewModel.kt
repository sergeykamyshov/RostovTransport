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
    private lateinit var disposable: Disposable

    init {
        loadData()
    }

    fun getData(): LiveData<Post> {
        return data
    }

    fun loadData() {
        disposable = getPost.execute(id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    data.postValue(it)
                }, {
                    Timber.d(it)
                })
    }

    override fun onCleared() {
        disposable.dispose()
        super.onCleared()
    }
}