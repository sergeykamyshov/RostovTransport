package ru.sergeykamyshov.rostovtransport.ui.card.deposit

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
import ru.sergeykamyshov.rostovtransport.ui.card.deposit.map.CardDepositMapActivity

class CardDepositFragment : Fragment() {

    companion object {
        fun newInstance() = CardDepositFragment()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_card_deposit, container, false)

        val progressBar = view.findViewById<ProgressBar>(R.id.card_deposit_progress)

        val mapButton = view.findViewById<ViewGroup>(R.id.layout_card_deposit_button_map)
        mapButton.setOnClickListener {
            startActivity(Intent(activity, CardDepositMapActivity::class.java))
        }

        val recycler = view.findViewById<RecyclerView>(R.id.rv_card_deposit)
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
            progressBar.visibility = View.GONE
        })
        viewModel.loadData()

        return view
    }

}