package ru.sergeykamyshov.rostovtransport.presentation.card.buy

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.gson.Gson
import kotlinx.android.synthetic.main.fragment_card_buy.view.*
import ru.sergeykamyshov.rostovtransport.R
import ru.sergeykamyshov.rostovtransport.base.extentions.onClickDebounce
import ru.sergeykamyshov.rostovtransport.domain.card.BuyAddress
import ru.sergeykamyshov.rostovtransport.presentation.base.StateFragment
import ru.sergeykamyshov.rostovtransport.presentation.card.buy.map.CardBuyMapActivity
import ru.sergeykamyshov.rostovtransport.presentation.main.MainActivity

class CardBuyFragment : StateFragment() {

    private var addresses: List<BuyAddress> = emptyList()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_card_buy, container, false)

        val recycler = view.rv_card_buy
        recycler.layoutManager = LinearLayoutManager(activity)
        val adapter = CardBuyAdapter(activity!!)
        recycler.adapter = adapter
        recycler.addItemDecoration(DividerItemDecoration(activity, DividerItemDecoration.VERTICAL))
        recycler.setHasFixedSize(true)

        val viewModel = ViewModelProviders.of(this).get(CardBuyViewModel::class.java)
        initViewState(
                this,
                viewModel.getUiState(),
                view.buy_progress,
                view.rv_card_buy,
                view.tv_empty,
                view.tv_error
        )
        viewModel.getData().observe(this, Observer {
            addresses = it
            adapter.update(it)
        })

        view.layout_card_buy_button_map.onClickDebounce {
            startActivity(CardBuyMapActivity.getIntent(activity as MainActivity, Gson().toJson(addresses)))
        }

        return view
    }

    companion object {
        fun newInstance() = CardBuyFragment()
    }

}