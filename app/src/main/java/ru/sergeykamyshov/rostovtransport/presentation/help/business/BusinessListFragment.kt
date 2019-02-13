package ru.sergeykamyshov.rostovtransport.presentation.help.business

import androidx.lifecycle.ViewModelProviders
import ru.sergeykamyshov.rostovtransport.presentation.help.base.BaseFragment
import ru.sergeykamyshov.rostovtransport.presentation.help.base.BaseViewModel

class BusinessListFragment : BaseFragment() {

    companion object {
        fun newInstance() = BusinessListFragment()
    }

    override fun getViewModel(): BaseViewModel {
        return ViewModelProviders.of(this).get(BusinessViewModel::class.java)
    }

    override fun getContentType() = "help_business"

}