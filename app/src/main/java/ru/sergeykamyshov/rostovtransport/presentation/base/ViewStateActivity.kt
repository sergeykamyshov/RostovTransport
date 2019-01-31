package ru.sergeykamyshov.rostovtransport.presentation.base

import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import ru.sergeykamyshov.rostovtransport.base.states.UIState

open class ViewStateActivity : AppCompatActivity() {

    private var viewState: ViewState = ViewState()

    fun initViewState(
            lifecycleOwner: LifecycleOwner,
            uiState: LiveData<UIState>,
            loadingView: View? = null,
            dataView: View? = null,
            emptyView: View? = null,
            errorView: View? = null
    ) {
        viewState.uiState = uiState

        viewState.loadingView = loadingView
        viewState.dataView = dataView
        viewState.emptyView = emptyView
        viewState.errorView = errorView

        viewState.init(lifecycleOwner)
    }

}