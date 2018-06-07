package ru.sergeykamyshov.rostovtransport.ui.complain

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import ru.sergeykamyshov.rostovtransport.R
import ru.sergeykamyshov.rostovtransport.ui.base.BaseFragment

class ComplainFragment : BaseFragment() {

    companion object {
        fun newInstance() = ComplainFragment()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_complain, container, false)

        setActionBarTitle(R.string.title_complain)

        return view
    }
}