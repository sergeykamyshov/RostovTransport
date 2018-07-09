package ru.sergeykamyshov.rostovtransport.ui.routes.base

import android.arch.lifecycle.Observer
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
import ru.sergeykamyshov.rostovtransport.R

abstract class BaseFragment : Fragment(), OnItemClickListener {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_list_routes, container, false)

        val progressBar = view.findViewById<ProgressBar>(R.id.routes_progress)
        val recycler = view.findViewById<RecyclerView>(R.id.rv_routes)
        recycler.layoutManager = LinearLayoutManager(activity)
        recycler.addItemDecoration(DividerItemDecoration(activity, DividerItemDecoration.VERTICAL))
        val adapter = RoutesAdapter(activity, ArrayList(), this)
        recycler.adapter = adapter

        val viewModel = getViewModel()
        val liveData = viewModel.getData()
        liveData.observe(this, Observer {
            if (it != null) {
                adapter.updateData(it)
                progressBar.visibility = View.GONE
            }
        })
        viewModel.loadData()

        return view
    }

    override fun onItemClick(id: String) {
        val type = getTransportType()
        startMapActivity(id, type)
    }

    private fun startMapActivity(id: String, type: String) {
        val intent = Intent(activity, RouteMapActivity::class.java)
        intent.putExtra(RouteMapActivity.ROUTE_TYPE, type)
        intent.putExtra(RouteMapActivity.ROUTE_ID, id)
        activity?.startActivity(intent)
    }

    abstract fun getViewModel(): BaseViewModel

    abstract fun getTransportType(): String
}