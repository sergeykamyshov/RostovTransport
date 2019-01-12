package ru.sergeykamyshov.rostovtransport.presentation.help.stations

import ru.sergeykamyshov.rostovtransport.presentation.help.base.BaseFragment
import ru.sergeykamyshov.rostovtransport.presentation.help.base.BaseViewModel

class StationsListFragment : BaseFragment() {

    companion object {
        fun newInstance() = StationsListFragment()
    }

    override fun getViewModel(): BaseViewModel {
        return StationsViewModel()
    }

}