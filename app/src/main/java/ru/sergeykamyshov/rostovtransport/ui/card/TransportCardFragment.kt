package ru.sergeykamyshov.rostovtransport.ui.card

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import ru.sergeykamyshov.rostovtransport.R
import ru.sergeykamyshov.rostovtransport.ui.base.BaseFragment

class TransportCardFragment : BaseFragment() {

    companion object {
        fun newInstance() = TransportCardFragment()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_card, container, false)

        setActionBarTitle(R.string.title_transport_card)

        return view
    }
}