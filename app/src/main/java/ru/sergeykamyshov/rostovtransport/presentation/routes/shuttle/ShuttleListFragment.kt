package ru.sergeykamyshov.rostovtransport.presentation.routes.shuttle

import ru.sergeykamyshov.rostovtransport.presentation.routes.base.BaseFragment
import ru.sergeykamyshov.rostovtransport.presentation.routes.base.BaseViewModel

class ShuttleListFragment : BaseFragment() {

    companion object {
        fun newInstance() = ShuttleListFragment()
    }

    override fun getViewModel(): BaseViewModel {
        return ShuttleListViewModel()
    }

    override fun getTransportType() = "shuttle"

}