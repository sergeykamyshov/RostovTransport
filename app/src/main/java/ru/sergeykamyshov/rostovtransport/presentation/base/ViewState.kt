package ru.sergeykamyshov.rostovtransport.presentation.base

import android.view.View
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import ru.sergeykamyshov.rostovtransport.base.extentions.hide
import ru.sergeykamyshov.rostovtransport.base.extentions.show
import ru.sergeykamyshov.rostovtransport.base.states.*

class ViewState {

    var uiState: LiveData<UIState>? = null
    var loadingView: View? = null
    var dataView: View? = null
    var emptyView: View? = null
    var errorView: View? = null

    fun init(lifecycleOwner: LifecycleOwner) {
        uiState?.observe(lifecycleOwner, Observer { uiState ->
            when (uiState) {
                Loading -> {
                    loadingView?.show()

                    dataView?.hide()
                    emptyView?.hide()
                    errorView?.hide()
                }
                HasData -> {
                    dataView?.show()

                    loadingView?.hide()
                    emptyView?.hide()
                    errorView?.hide()
                }
                NoData -> {
                    emptyView?.show()

                    loadingView?.hide()
                    dataView?.hide()
                    errorView?.hide()
                }
                Error -> {
                    errorView?.show()

                    loadingView?.hide()
                    dataView?.hide()
                    emptyView?.hide()
                }
            }
        })
    }
}