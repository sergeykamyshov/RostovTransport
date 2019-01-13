package ru.sergeykamyshov.rostovtransport.presentation.card.buy

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.gson.Gson
import kotlinx.android.synthetic.main.fragment_card_buy.view.*
import ru.sergeykamyshov.rostovtransport.R
import ru.sergeykamyshov.rostovtransport.data.network.model.card.CardBuy
import ru.sergeykamyshov.rostovtransport.presentation.card.buy.map.CardBuyMapActivity
import ru.sergeykamyshov.rostovtransport.presentation.main.MainActivity

class CardBuyFragment : Fragment() {

    private lateinit var addresses: List<CardBuy.Address>

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_card_buy, container, false)

        val recycler = view.rv_card_buy
        recycler.layoutManager = LinearLayoutManager(activity)
        val adapter = CardBuyAdapter(activity, ArrayList())
        recycler.adapter = adapter
        recycler.addItemDecoration(DividerItemDecoration(activity, DividerItemDecoration.VERTICAL))
        recycler.setHasFixedSize(true)

        val viewModel = ViewModelProviders.of(activity as MainActivity).get(CardBuyViewModel::class.java)
        viewModel.getData().observe(this, Observer {
            if (it != null) {
                addresses = it
                adapter.updateData(it)
            }
            view.card_buy_progress.visibility = View.GONE
        })
        viewModel.loadData()

        view.layout_card_buy_button_map.setOnClickListener {
            startActivity(CardBuyMapActivity.getIntent(activity as MainActivity, Gson().toJson(addresses)))
        }

        return view
    }

    companion object {
        fun newInstance() = CardBuyFragment()
    }

}