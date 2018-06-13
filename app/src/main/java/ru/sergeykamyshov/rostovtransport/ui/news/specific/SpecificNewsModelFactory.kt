package ru.sergeykamyshov.rostovtransport.ui.news.specific

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider

class SpecificNewsModelFactory(var id: String) : ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass == SpecificNewsViewModel::class.java) {
            return SpecificNewsViewModel(id) as T
        }
        return super.create(modelClass)
    }
}