package ru.sergeykamyshov.rostovtransport.presentation.help.departments

import androidx.lifecycle.ViewModelProviders
import ru.sergeykamyshov.rostovtransport.presentation.help.base.BaseFragment
import ru.sergeykamyshov.rostovtransport.presentation.help.base.BaseViewModel

class DepartmentsListFragment : BaseFragment() {

    companion object {
        fun newInstance() = DepartmentsListFragment()
    }

    override fun getViewModel(): BaseViewModel {
        return ViewModelProviders.of(this).get(DepartmentsViewModel::class.java)
    }

    override fun getContentType() = "help_departments"

}