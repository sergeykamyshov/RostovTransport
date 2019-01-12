package ru.sergeykamyshov.rostovtransport.presentation.card.info

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.fragment_card_info.view.*
import ru.sergeykamyshov.rostovtransport.presentation.main.MainActivity
import ru.sergeykamyshov.rostovtransport.R

class CardInfoFragment : Fragment() {

    companion object {
        fun newInstance() = CardInfoFragment()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_card_info, container, false)

        val viewModel = ViewModelProviders.of(activity as MainActivity).get(CardInfoViewModel::class.java)
        viewModel.getData().observe(this, Observer {
            if (it != null) {
                view.img_card_info.visibility = View.VISIBLE
                view.v_card_info_gradient.visibility = View.VISIBLE

                view.tv_card_info_title.text = it.title
                view.tv_card_info_content.text = it.content

                view.card_info_progress.visibility = View.GONE
            }
        })
        viewModel.loadData()

        return view
    }
}