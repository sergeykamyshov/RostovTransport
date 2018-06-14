package ru.sergeykamyshov.rostovtransport.ui.news.post

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider

class PostModelFactory(var id: String) : ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass == PostViewModel::class.java) {
            return PostViewModel(id) as T
        }
        return super.create(modelClass)
    }
}