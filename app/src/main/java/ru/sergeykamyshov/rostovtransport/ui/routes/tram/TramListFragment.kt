package ru.sergeykamyshov.rostovtransport.ui.routes.tram

import ru.sergeykamyshov.rostovtransport.ui.routes.base.BaseFragment
import ru.sergeykamyshov.rostovtransport.ui.routes.base.BaseViewModel

class TramListFragment : BaseFragment() {

    companion object {
        fun newInstance() = TramListFragment()
    }

    override fun getViewModel(): BaseViewModel {
        return TramListViewModel()
    }

    override fun getTransportType() = "tram"
    
}