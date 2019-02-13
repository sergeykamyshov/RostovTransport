package ru.sergeykamyshov.rostovtransport.presentation.about

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.fragment_about.view.*
import ru.sergeykamyshov.rostovtransport.R
import ru.sergeykamyshov.rostovtransport.base.utils.AnalyticsUtils
import ru.sergeykamyshov.rostovtransport.presentation.base.BaseFragment

class AboutFragment : BaseFragment() {

    private val CONTENT_VIEW_TYPE = "about"

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_about, container, false)

        setActionBarTitle(R.string.title_about)

        AnalyticsUtils.logContentViewEvent(CONTENT_VIEW_TYPE)

        view.rv_cards.layoutManager = LinearLayoutManager(context)
        val adapter = CardsAdapter(context!!)
        view.rv_cards.adapter = adapter

        return view
    }

    companion object {
        const val TAG = "AboutFragment"

        fun newInstance() = AboutFragment()
    }

}