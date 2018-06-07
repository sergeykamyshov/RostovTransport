package ru.sergeykamyshov.rostovtransport.ui.online

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import ru.sergeykamyshov.rostovtransport.R
import ru.sergeykamyshov.rostovtransport.ui.base.BaseFragment

class TransportOnlineFragment : BaseFragment() {

    companion object {
        fun newInstance() = TransportOnlineFragment()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_online, container, false)

        setActionBarTitle(R.string.title_transport_online)

        return view
    }
}