package ru.sergeykamyshov.rostovtransport.presentation.routes.tram

import ru.sergeykamyshov.rostovtransport.presentation.routes.base.BaseFragment
import ru.sergeykamyshov.rostovtransport.presentation.routes.base.BaseViewModel

class TramListFragment : BaseFragment() {

    companion object {
        fun newInstance() = TramListFragment()
    }

    override fun getViewModel(): BaseViewModel {
        return TramListViewModel()
    }

    override fun getTransportType() = "tram"
    
}