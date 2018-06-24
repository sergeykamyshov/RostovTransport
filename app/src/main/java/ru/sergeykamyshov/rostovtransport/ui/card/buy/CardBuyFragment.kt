package ru.sergeykamyshov.rostovtransport.ui.card.buy

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import ru.sergeykamyshov.rostovtransport.MainActivity
import ru.sergeykamyshov.rostovtransport.R
import ru.sergeykamyshov.rostovtransport.ui.card.buy.map.CardBuyMapActivity

class CardBuyFragment : Fragment() {

    companion object {
        fun newInstance() = CardBuyFragment()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_card_buy, container, false)

        val progressBar = view.findViewById<ProgressBar>(R.id.card_buy_progress)

        val mapButton = view.findViewById<ViewGroup>(R.id.layout_card_buy_button_map)
        mapButton.setOnClickListener {
            startActivity(Intent(activity, CardBuyMapActivity::class.java))
        }

        val recycler = view.findViewById<RecyclerView>(R.id.rv_card_buy)
        recycler.layoutManager = LinearLayoutManager(activity)
        val adapter = CardBuyAdapter(activity, ArrayList())
        recycler.adapter = adapter
        recycler.addItemDecoration(DividerItemDecoration(activity, DividerItemDecoration.VERTICAL))

        val viewModel = ViewModelProviders.of(activity as MainActivity).get(CardBuyViewModel::class.java)
        viewModel.getData().observe(this, Observer {
            if (it != null) {
                adapter.updateData(it)
            }
            progressBar.visibility = View.GONE
        })
        viewModel.loadData()

        return view
    }

}