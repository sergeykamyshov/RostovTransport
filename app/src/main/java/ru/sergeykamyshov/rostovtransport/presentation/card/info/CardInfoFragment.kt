package ru.sergeykamyshov.rostovtransport.presentation.card.info

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import kotlinx.android.synthetic.main.fragment_card_info.view.*
import ru.sergeykamyshov.rostovtransport.R
import ru.sergeykamyshov.rostovtransport.base.extentions.hide
import ru.sergeykamyshov.rostovtransport.base.extentions.show
import ru.sergeykamyshov.rostovtransport.presentation.main.MainActivity

class CardInfoFragment : Fragment() {

    companion object {
        fun newInstance() = CardInfoFragment()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_card_info, container, false)

        val viewModel = ViewModelProviders.of(activity as MainActivity).get(CardInfoViewModel::class.java)
        viewModel.getData().observe(this, Observer {
            if (it != null) {
                view.img_card_info.show()
                view.v_card_info_gradient.show()

                view.tv_card_info_title.text = it.title
                view.tv_card_info_content.text = it.content

                view.card_info_progress.hide()
            }
        })
        viewModel.loadData()

        return view
    }
}