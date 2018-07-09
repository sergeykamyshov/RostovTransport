package ru.sergeykamyshov.rostovtransport.ui.routes.shuttle

import ru.sergeykamyshov.rostovtransport.ui.routes.base.BaseFragment
import ru.sergeykamyshov.rostovtransport.ui.routes.base.BaseViewModel

class ShuttleListFragment : BaseFragment() {

    companion object {
        fun newInstance() = ShuttleListFragment()
    }

    override fun getViewModel(): BaseViewModel {
        return ShuttleListViewModel()
    }

    override fun getTransportType() = "shuttle"

}