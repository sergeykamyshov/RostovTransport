package ru.sergeykamyshov.rostovtransport.presentation.base

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import ru.sergeykamyshov.rostovtransport.presentation.main.MainActivity

open class BaseFragment : Fragment() {

    protected var viewState: ViewState = ViewState()

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        // Показываем AppBarLayout при создании нового фрагмента (даже если на предыдущем он был скрыт)
        (activity as MainActivity).showAppBarLayout()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewState.init(this)
    }

    fun setActionBarTitle(resId: Int) {
        (activity as MainActivity).supportActionBar?.title = activity?.resources?.getString(resId)
    }
}