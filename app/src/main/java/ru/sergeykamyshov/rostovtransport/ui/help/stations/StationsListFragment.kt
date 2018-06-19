package ru.sergeykamyshov.rostovtransport.ui.help.stations

import ru.sergeykamyshov.rostovtransport.ui.help.base.BaseFragment
import ru.sergeykamyshov.rostovtransport.ui.help.base.BaseViewModel

class StationsListFragment : BaseFragment() {

    companion object {
        fun newInstance() = StationsListFragment()
    }

    override fun getViewModel(): BaseViewModel {
        return StationsViewModel()
    }

}