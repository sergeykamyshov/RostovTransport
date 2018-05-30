package ru.sergeykamyshov.rostovtransport.ui.complain

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import ru.sergeykamyshov.rostovtransport.R

class ComplainFragment : Fragment() {

    companion object {
        fun newInstance() = ComplainFragment()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_complain, container, false)
    }
}