package ru.sergeykamyshov.rostovtransport.ui.routes.tram

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import ru.sergeykamyshov.rostovtransport.R

class TramListFragment : Fragment() {

    companion object {
        fun newInstance() = TramListFragment()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_list_tram, container, false)
    }

}