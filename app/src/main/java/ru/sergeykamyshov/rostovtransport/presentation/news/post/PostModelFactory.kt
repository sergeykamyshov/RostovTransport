package ru.sergeykamyshov.rostovtransport.presentation.news.post

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class PostModelFactory(var id: String) : ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass == PostViewModel::class.java) {
            return PostViewModel(id) as T
        }
        return super.create(modelClass)
    }
}