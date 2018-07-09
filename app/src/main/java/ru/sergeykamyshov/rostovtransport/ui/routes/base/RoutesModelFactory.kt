package ru.sergeykamyshov.rostovtransport.ui.routes.base

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider

class RoutesModelFactory(var type: String, var id: String) : ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass == RouteMapViewModel::class.java) {
            return RouteMapViewModel(type, id) as T
        }
        return super.create(modelClass)
    }
}