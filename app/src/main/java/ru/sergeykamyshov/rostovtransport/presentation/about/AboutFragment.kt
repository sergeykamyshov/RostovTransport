package ru.sergeykamyshov.rostovtransport.presentation.about

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.fragment_about.*
import ru.sergeykamyshov.rostovtransport.R
import ru.sergeykamyshov.rostovtransport.presentation.base.BaseFragment

class AboutFragment : BaseFragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_about, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setActionBarTitle(R.string.title_about)

        rv_cards.layoutManager = LinearLayoutManager(requireContext())
        val adapter = CardsAdapter(requireContext())
        rv_cards.adapter = adapter
    }

    companion object {
        // TODO: SK 07-Apr-19 Check and remove unused method in every fragment
        fun newInstance() = AboutFragment()
    }

}