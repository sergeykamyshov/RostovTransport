package ru.sergeykamyshov.rostovtransport.ui.help.departments

import ru.sergeykamyshov.rostovtransport.ui.help.base.BaseFragment
import ru.sergeykamyshov.rostovtransport.ui.help.base.BaseViewModel

class DepartmentsListFragment : BaseFragment() {

    companion object {
        fun newInstance() = DepartmentsListFragment()
    }

    override fun getViewModel(): BaseViewModel {
        return DepartmentsViewModel()
    }

}