package ru.sergeykamyshov.rostovtransport.ui.routes.trolleybus

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import ru.sergeykamyshov.rostovtransport.R

class TrolleybusListFragment : Fragment() {

    companion object {
        fun newInstance() = TrolleybusListFragment()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_list_trolleybus, container, false)
    }

}