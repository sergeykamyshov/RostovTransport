package ru.sergeykamyshov.rostovtransport.presentation.card.info

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import ru.sergeykamyshov.rostovtransport.R

class CardInfoFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_card_info, container, false)
    }

    companion object {
        fun newInstance() = CardInfoFragment()
    }

}