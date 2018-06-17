package ru.sergeykamyshov.rostovtransport.ui.routes.trolleybus

import ru.sergeykamyshov.rostovtransport.ui.routes.base.BaseFragment
import ru.sergeykamyshov.rostovtransport.ui.routes.base.BaseViewModel

class TrolleybusListFragment : BaseFragment() {

    companion object {
        fun newInstance() = TrolleybusListFragment()
    }

    override fun getViewModel(): BaseViewModel {
        return TrolleybusListViewModel()
    }

}