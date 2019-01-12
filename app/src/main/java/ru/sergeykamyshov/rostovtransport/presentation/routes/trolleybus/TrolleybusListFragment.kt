package ru.sergeykamyshov.rostovtransport.presentation.routes.trolleybus

import ru.sergeykamyshov.rostovtransport.presentation.routes.base.BaseFragment
import ru.sergeykamyshov.rostovtransport.presentation.routes.base.BaseViewModel

class TrolleybusListFragment : BaseFragment() {

    companion object {
        fun newInstance() = TrolleybusListFragment()
    }

    override fun getViewModel(): BaseViewModel {
        return TrolleybusListViewModel()
    }

    override fun getTransportType() = "trolleybus"

}