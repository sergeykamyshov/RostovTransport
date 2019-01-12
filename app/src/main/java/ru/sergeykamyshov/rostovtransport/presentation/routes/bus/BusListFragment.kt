package ru.sergeykamyshov.rostovtransport.presentation.routes.bus

import ru.sergeykamyshov.rostovtransport.presentation.routes.base.BaseFragment
import ru.sergeykamyshov.rostovtransport.presentation.routes.base.BaseViewModel

class BusListFragment : BaseFragment() {

    companion object {
        fun newInstance() = BusListFragment()
    }

    override fun getViewModel(): BaseViewModel {
        return BusListViewModel()
    }

    override fun getTransportType() = "bus"

}