package ru.sergeykamyshov.rostovtransport.presentation.card.info

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import ru.sergeykamyshov.rostovtransport.R
import ru.sergeykamyshov.rostovtransport.base.utils.AnalyticsUtils

class CardInfoFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_card_info, container, false)

        AnalyticsUtils.logContentViewEvent(CONTENT_VIEW_TYPE)

        return view
    }

    companion object {
        private const val CONTENT_VIEW_TYPE = "card_info"

        fun newInstance() = CardInfoFragment()
    }

}