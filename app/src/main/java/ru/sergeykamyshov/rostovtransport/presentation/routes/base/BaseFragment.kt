package ru.sergeykamyshov.rostovtransport.presentation.routes.base

import android.arch.lifecycle.Observer
import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.fragment_list_routes.view.*
import ru.sergeykamyshov.rostovtransport.R

abstract class BaseFragment : Fragment(), OnItemClickListener {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_list_routes, container, false)

        val recycler = view.rv_routes
        recycler.layoutManager = LinearLayoutManager(activity)
        recycler.addItemDecoration(DividerItemDecoration(activity, DividerItemDecoration.VERTICAL))
        val adapter = RoutesAdapter(activity, ArrayList(), this)
        recycler.adapter = adapter

        val viewModel = getViewModel()
        val liveData = viewModel.getData()
        liveData.observe(this, Observer {
            if (it != null) {
                adapter.updateData(it)
                view.routes_progress.visibility = View.GONE
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