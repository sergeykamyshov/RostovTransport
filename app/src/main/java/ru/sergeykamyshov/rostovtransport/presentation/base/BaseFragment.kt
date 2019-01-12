package ru.sergeykamyshov.rostovtransport.presentation.base

import android.os.Bundle
import android.support.v4.app.Fragment
import ru.sergeykamyshov.rostovtransport.presentation.main.MainActivity

open class BaseFragment : Fragment() {

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        // Показываем AppBarLayout при создании нового фрагмента (даже если на предыдущем он был скрыт)
        (activity as MainActivity).showAppBarLayout()
    }

    fun setActionBarTitle(resId: Int) {
        (activity as MainActivity).supportActionBar?.title = activity?.resources?.getString(resId)
    }
}