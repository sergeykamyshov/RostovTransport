package ru.sergeykamyshov.rostovtransport.ui.card.deposit

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.fragment_card_deposit.view.*
import ru.sergeykamyshov.rostovtransport.MainActivity
import ru.sergeykamyshov.rostovtransport.R
import ru.sergeykamyshov.rostovtransport.ui.card.deposit.map.CardDepositMapActivity

class CardDepositFragment : Fragment() {

    companion object {
        fun newInstance() = CardDepositFragment()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_card_deposit, container, false)

        view.layout_card_deposit_button_map.setOnClickListener {
            startActivity(Intent(activity, CardDepositMapActivity::class.java))
        }

        val recycler = view.rv_card_deposit
        recycler.layoutManager = LinearLayoutManager(activity)
        val adapter = CardDepositAdapter(activity, ArrayList())
        recycler.adapter = adapter
        recycler.addItemDecoration(DividerItemDecoration(activity, DividerItemDecoration.VERTICAL))
        recycler.setHasFixedSize(true)

        val viewModel = ViewModelProviders.of(activity as MainActivity).get(CardDepositViewModel::class.java)
        viewModel.getData().observe(this, Observer {
            if (it != null) {
                adapter.updateData(it)
            }
            view.card_deposit_progress.visibility = View.GONE
        })
        viewModel.loadData()

        return view
    }

}