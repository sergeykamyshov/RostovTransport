package ru.sergeykamyshov.rostovtransport.presentation.base

import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import ru.sergeykamyshov.rostovtransport.base.states.UIState

open class StateFragment : Fragment() {

    private lateinit var viewState: ViewState

    fun initViewState(
            lifecycleOwner: LifecycleOwner,
            uiState: LiveData<UIState>,
            loadingView: View? = null,
            dataView: View? = null,
            emptyView: View? = null,
            errorView: View? = null
    ) {
        viewState = ViewState()

        viewState.uiState = uiState

        viewState.loadingView = loadingView
        viewState.dataView = dataView
        viewState.emptyView = emptyView
        viewState.errorView = errorView

        viewState.init(lifecycleOwner)
    }
}