package ru.sergeykamyshov.rostovtransport.ui.about

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v7.widget.CardView
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.TextView
import ru.sergeykamyshov.rostovtransport.MainActivity
import ru.sergeykamyshov.rostovtransport.R
import ru.sergeykamyshov.rostovtransport.ui.base.BaseFragment

class AboutFragment : BaseFragment() {

    companion object {
        fun newInstance() = AboutFragment()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_about, container, false)

        setActionBarTitle(R.string.title_about)

        val progressBar = view.findViewById<ProgressBar>(R.id.about_progress)
        val shortDescCard = view.findViewById<CardView>(R.id.cv_short_desc)
        val shortDesc = view.findViewById<TextView>(R.id.tv_short_desc)
        val importantInfoCard = view.findViewById<CardView>(R.id.cv_important)
        val importantInfo = view.findViewById<TextView>(R.id.tv_important)
        val contactsCard = view.findViewById<CardView>(R.id.cv_contacts)
        val fullDescCard = view.findViewById<CardView>(R.id.cv_full_desc)
        val fullDesc = view.findViewById<TextView>(R.id.tv_full_desc)

        val recycler = view.findViewById<RecyclerView>(R.id.contacts_recycler)
        recycler.layoutManager = LinearLayoutManager(activity)
        val adapter = ContactsAdapter(activity, ArrayList())
        recycler.adapter = adapter
        recycler.addItemDecoration(DividerItemDecoration(activity, DividerItemDecoration.VERTICAL))
        recycler.setHasFixedSize(true)

        val viewModel = ViewModelProviders.of(activity as MainActivity).get(AboutViewModel::class.java)
        val liveData = viewModel.getData()
        liveData.observe(this, Observer {
            shortDesc.text = it?.shortDescription
            shortDescCard.visibility = View.VISIBLE

            importantInfo.text = it?.importantInfo
            importantInfoCard.visibility = View.VISIBLE

            if (it?.contacts != null && it.contacts.isNotEmpty()) {
                adapter.updateData(it.contacts)
                contactsCard.visibility = View.VISIBLE
            }

            fullDesc.text = it?.fullDescription
            fullDescCard.visibility = View.VISIBLE

            progressBar.visibility = View.GONE
        })
        viewModel.loadData()

        return view
    }
}