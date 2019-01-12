package ru.sergeykamyshov.rostovtransport.presentation.help.business

import ru.sergeykamyshov.rostovtransport.presentation.help.base.BaseFragment
import ru.sergeykamyshov.rostovtransport.presentation.help.base.BaseViewModel

class BusinessListFragment : BaseFragment() {

    companion object {
        fun newInstance() = BusinessListFragment()
    }

    override fun getViewModel(): BaseViewModel {
        return BusinessViewModel()
    }

}