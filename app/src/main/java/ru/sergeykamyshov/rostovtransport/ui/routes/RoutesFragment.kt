package ru.sergeykamyshov.rostovtransport.ui.routes

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import ru.sergeykamyshov.rostovtransport.R
import ru.sergeykamyshov.rostovtransport.ui.base.BaseFragment

class RoutesFragment : BaseFragment() {

    companion object {
        fun newInstance() = RoutesFragment()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_routes, container, false)

        setActionBarTitle(R.string.title_routes)

        return view
    }
}