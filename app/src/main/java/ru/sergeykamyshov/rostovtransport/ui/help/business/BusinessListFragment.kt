package ru.sergeykamyshov.rostovtransport.ui.help.business

import ru.sergeykamyshov.rostovtransport.ui.help.base.BaseFragment
import ru.sergeykamyshov.rostovtransport.ui.help.base.BaseViewModel

class BusinessListFragment : BaseFragment() {

    companion object {
        fun newInstance() = BusinessListFragment()
    }

    override fun getViewModel(): BaseViewModel {
        return BusinessViewModel()
    }

}