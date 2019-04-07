package ru.sergeykamyshov.rostovtransport.presentation.card.deposit

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
import kotlinx.android.synthetic.main.fragment_card_deposit.*
import ru.sergeykamyshov.rostovtransport.R
import ru.sergeykamyshov.rostovtransport.base.extentions.onClickDebounce
import ru.sergeykamyshov.rostovtransport.domain.card.DepositAddress
import ru.sergeykamyshov.rostovtransport.presentation.base.StateFragment
import ru.sergeykamyshov.rostovtransport.presentation.card.deposit.map.CardDepositMapActivity

class CardDepositFragment : StateFragment() {

    private var addresses: List<DepositAddress> = emptyList()
    private lateinit var adapter: CardDepositAdapter
    private lateinit var navController: NavController

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_card_deposit, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        navController = NavHostFragment.findNavController(this)

        setupRecycler()

        val viewModel = ViewModelProviders.of(this).get(CardDepositViewModel::class.java)
        initViewState(
                this,
                viewModel.getUiState(),
                loadingView = deposit_progress,
                dataView = rv_card_deposit,
                emptyView = tv_empty,
                errorView = tv_error
        )
        viewModel.getData().observe(this, Observer {
            addresses = it
            adapter.update(it)
        })

        layout_card_deposit_button_map.onClickDebounce {
            navController.navigate(
                    R.id.action_transportCardFragment_to_cardDepositMapActivity,
                    Bundle().also {
                        it.putString(CardDepositMapActivity.ADDRESSES_EXTRA, Gson().toJson(addresses))
                    })
        }
    }

    private fun setupRecycler() {
        rv_card_deposit.layoutManager = LinearLayoutManager(requireContext())
        rv_card_deposit.addItemDecoration(DividerItemDecoration(requireContext(), DividerItemDecoration.VERTICAL))
        rv_card_deposit.setHasFixedSize(true)
        adapter = CardDepositAdapter(requireContext())
        rv_card_deposit.adapter = adapter
    }

    companion object {
        fun newInstance() = CardDepositFragment()
    }

}