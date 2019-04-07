package ru.sergeykamyshov.rostovtransport.presentation.card.buy

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.gson.Gson
import kotlinx.android.synthetic.main.fragment_card_buy.*
import ru.sergeykamyshov.rostovtransport.R
import ru.sergeykamyshov.rostovtransport.base.extentions.onClickDebounce
import ru.sergeykamyshov.rostovtransport.domain.card.BuyAddress
import ru.sergeykamyshov.rostovtransport.presentation.base.StateFragment
import ru.sergeykamyshov.rostovtransport.presentation.card.buy.map.CardBuyMapActivity

class CardBuyFragment : StateFragment() {

    private var addresses: List<BuyAddress> = emptyList()
    private lateinit var adapter: CardBuyAdapter
    private lateinit var navController: NavController

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_card_buy, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        navController = NavHostFragment.findNavController(this)

        setupRecycler()

        val viewModel = ViewModelProviders.of(this).get(CardBuyViewModel::class.java)
        initViewState(
                this,
                viewModel.getUiState(),
                buy_progress,
                rv_card_buy,
                tv_empty,
                tv_error
        )
        viewModel.getData().observe(this, Observer {
            addresses = it
            adapter.update(it)
        })

        layout_card_buy_button_map.onClickDebounce {
            navController.navigate(
                    R.id.action_transportCardFragment_to_cardBuyMapActivity,
                    Bundle().also {
                        it.putString(CardBuyMapActivity.ADDRESSES_EXTRA, Gson().toJson(addresses))
                    })
        }
    }

    private fun setupRecycler() {
        rv_card_buy.layoutManager = LinearLayoutManager(requireContext())
        rv_card_buy.addItemDecoration(DividerItemDecoration(requireContext(), DividerItemDecoration.VERTICAL))
        rv_card_buy.setHasFixedSize(true)
        adapter = CardBuyAdapter(requireContext())
        rv_card_buy.adapter = adapter
    }

    companion object {
        fun newInstance() = CardBuyFragment()
    }

}