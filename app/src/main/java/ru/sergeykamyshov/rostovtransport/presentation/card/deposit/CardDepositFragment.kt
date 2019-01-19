package ru.sergeykamyshov.rostovtransport.presentation.card.deposit

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.crashlytics.android.answers.Answers
import com.crashlytics.android.answers.CustomEvent
import com.google.gson.Gson
import kotlinx.android.synthetic.main.fragment_card_deposit.view.*
import ru.sergeykamyshov.rostovtransport.App
import ru.sergeykamyshov.rostovtransport.R
import ru.sergeykamyshov.rostovtransport.base.extentions.sendEvent
import ru.sergeykamyshov.rostovtransport.data.models.card.CardDeposit
import ru.sergeykamyshov.rostovtransport.presentation.card.deposit.map.CardDepositMapActivity
import ru.sergeykamyshov.rostovtransport.presentation.main.MainActivity

class CardDepositFragment : Fragment() {

    private val CARD_DEPOSIT_MAP_EVENT = "card_deposit_map"

    private lateinit var addresses: List<CardDeposit.Address>

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_card_deposit, container, false)

        val recycler = view.rv_card_deposit
        recycler.layoutManager = LinearLayoutManager(activity)
        val adapter = CardDepositAdapter(activity, ArrayList())
        recycler.adapter = adapter
        recycler.addItemDecoration(DividerItemDecoration(activity, DividerItemDecoration.VERTICAL))
        recycler.setHasFixedSize(true)

        val viewModel = ViewModelProviders.of(activity as MainActivity).get(CardDepositViewModel::class.java)
        viewModel.getData().observe(this, Observer {
            if (it != null) {
                addresses = it
                adapter.updateData(it)
            }
            view.card_deposit_progress.visibility = View.GONE
        })
        viewModel.loadData()

        view.layout_card_deposit_button_map.setOnClickListener {
            App.firebaseAnalytics.sendEvent(CARD_DEPOSIT_MAP_EVENT)
            Answers.getInstance().logCustom(CustomEvent(CARD_DEPOSIT_MAP_EVENT))
            startActivity(CardDepositMapActivity.getIntent(activity as MainActivity, Gson().toJson(addresses)))
        }

        return view
    }

    companion object {
        fun newInstance() = CardDepositFragment()
    }

}