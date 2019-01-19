package ru.sergeykamyshov.rostovtransport.presentation.news.list

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import ru.sergeykamyshov.rostovtransport.App
import ru.sergeykamyshov.rostovtransport.domain.news.GetRecentNews
import ru.sergeykamyshov.rostovtransport.domain.news.Post
import timber.log.Timber

class NewsViewModel : ViewModel() {

    private val getRecentNews: GetRecentNews = App.provider.useCase.getRecentNews
    private var data = MutableLiveData<List<Post>>()
    private lateinit var disposable: Disposable

    fun getData(): LiveData<List<Post>> {
        return data
    }

    fun loadData() {
        disposable = getRecentNews.execute()
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