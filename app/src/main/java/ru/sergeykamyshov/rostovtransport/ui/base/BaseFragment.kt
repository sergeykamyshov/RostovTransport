package ru.sergeykamyshov.rostovtransport.ui.base

import android.support.v4.app.Fragment
import ru.sergeykamyshov.rostovtransport.MainActivity

open class BaseFragment : Fragment() {

    fun setActionBarTitle(resId: Int) {
        (activity as MainActivity).supportActionBar?.title = activity?.resources?.getString(resId)
    }

}