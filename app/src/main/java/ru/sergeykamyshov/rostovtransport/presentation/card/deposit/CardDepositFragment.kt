package ru.sergeykamyshov.rostovtransport.presentation.card.deposit

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.gson.Gson
import kotlinx.android.synthetic.main.fragment_card_deposit.view.*
import ru.sergeykamyshov.rostovtransport.R
import ru.sergeykamyshov.rostovtransport.base.extentions.onClickDebounce
import ru.sergeykamyshov.rostovtransport.domain.card.DepositAddress
import ru.sergeykamyshov.rostovtransport.presentation.base.StateFragment
import ru.sergeykamyshov.rostovtransport.presentation.card.deposit.map.CardDepositMapActivity
import ru.sergeykamyshov.rostovtransport.presentation.main.MainActivity

class CardDepositFragment : StateFragment() {

    private var addresses: List<DepositAddress> = emptyList()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_card_deposit, container, false)

        val recycler = view.rv_card_deposit
        recycler.layoutManager = LinearLayoutManager(activity)
        val adapter = CardDepositAdapter(activity!!)
        recycler.adapter = adapter
        recycler.addItemDecoration(DividerItemDecoration(activity, DividerItemDecoration.VERTICAL))
        recycler.setHasFixedSize(true)

        val viewModel = ViewModelProviders.of(this).get(CardDepositViewModel::class.java)
        initViewState(
                this,
                viewModel.getUiState(),
                loadingView = view.deposit_progress,
                dataView = view.rv_card_deposit,
                emptyView = view.tv_empty,
                errorView = view.tv_error
        )
        viewModel.getData().observe(this, Observer {
            addresses = it
            adapter.update(it)
        })

        view.layout_card_deposit_button_map.onClickDebounce {
            startActivity(CardDepositMapActivity.getIntent(activity as MainActivity, Gson().toJson(addresses)))
        }

        return view
    }

    companion object {
        fun newInstance() = CardDepositFragment()
    }

}