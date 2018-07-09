package ru.sergeykamyshov.rostovtransport.ui.routes.bus

import ru.sergeykamyshov.rostovtransport.ui.routes.base.BaseFragment
import ru.sergeykamyshov.rostovtransport.ui.routes.base.BaseViewModel

class BusListFragment : BaseFragment() {

    companion object {
        fun newInstance() = BusListFragment()
    }

    override fun getViewModel(): BaseViewModel {
        return BusListViewModel()
    }

    override fun getTransportType() = "bus"

}