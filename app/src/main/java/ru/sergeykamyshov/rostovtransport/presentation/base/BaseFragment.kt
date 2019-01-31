package ru.sergeykamyshov.rostovtransport.presentation.base

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import ru.sergeykamyshov.rostovtransport.base.states.UIState
import ru.sergeykamyshov.rostovtransport.presentation.main.MainActivity

open class BaseFragment : Fragment() {

    private var viewState: ViewState = ViewState()

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        // Показываем AppBarLayout при создании нового фрагмента (даже если на предыдущем он был скрыт)
        (activity as MainActivity).showAppBarLayout()
    }

    fun setActionBarTitle(resId: Int) {
        (activity as MainActivity).supportActionBar?.title = activity?.resources?.getString(resId)
    }

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