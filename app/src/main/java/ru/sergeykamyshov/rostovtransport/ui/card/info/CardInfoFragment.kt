package ru.sergeykamyshov.rostovtransport.ui.card.info

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import ru.sergeykamyshov.rostovtransport.R

class CardInfoFragment : Fragment() {

    companion object {
        fun newInstance() = CardInfoFragment()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_card_info, container, false)

        val progressBar = view.findViewById<ProgressBar>(R.id.card_info_progress)
        val image = view.findViewById<ImageView>(R.id.img_card_info)
        val gradient = view.findViewById<View>(R.id.v_card_info_gradient)
        val title = view.findViewById<TextView>(R.id.tv_card_info_title)
        val content = view.findViewById<TextView>(R.id.tv_card_info_content)

        val viewModel = ViewModelProviders.of(activity!!).get(CardInfoViewModel::class.java)
        viewModel.getData().observe(this, Observer {
            if (it != null) {
                image.visibility = View.VISIBLE
                gradient.visibility = View.VISIBLE

                title.text = it.title
                content.text = it.content

                progressBar.visibility = View.GONE
            }
        })
        viewModel.loadData()

        return view
    }
}