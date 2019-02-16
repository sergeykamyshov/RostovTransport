package ru.sergeykamyshov.rostovtransport.presentation.help.stations

import androidx.lifecycle.ViewModelProviders
import ru.sergeykamyshov.rostovtransport.presentation.help.base.BaseFragment
import ru.sergeykamyshov.rostovtransport.presentation.help.base.BaseViewModel

class StationsListFragment : BaseFragment() {

    companion object {
        fun newInstance() = StationsListFragment()
    }

    override fun getViewModel(): BaseViewModel {
        return ViewModelProviders.of(this).get(StationsViewModel::class.java)
    }

    override fun getContentType() = "help_stations"

}