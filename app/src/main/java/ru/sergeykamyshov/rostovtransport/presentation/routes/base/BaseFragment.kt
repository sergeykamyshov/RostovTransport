package ru.sergeykamyshov.rostovtransport.presentation.routes.base

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.fragment_list_routes.view.*
import ru.sergeykamyshov.rostovtransport.R
import ru.sergeykamyshov.rostovtransport.base.extentions.hide

abstract class BaseFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_list_routes, container, false)

        val recycler = view.rv_routes
        recycler.layoutManager = LinearLayoutManager(requireContext())
        recycler.addItemDecoration(DividerItemDecoration(requireContext(), DividerItemDecoration.VERTICAL))
        val adapter = RoutesAdapter(requireContext(), ArrayList())
        recycler.adapter = adapter

        val viewModel = getViewModel()
        val liveData = viewModel.getData()
        liveData.observe(this, Observer {
            if (it != null) {
                adapter.updateData(it)
                view.routes_progress.hide()
            }
        })
        viewModel.loadData()

        return view
    }

//    override fun onItemClick(id: String) {
//        val type = getTransportType()
//        startMapActivity(id, type)
//    }

    private fun startMapActivity(id: String, type: String) {
        val intent = Intent(activity, RouteMapActivity::class.java)
        intent.putExtra(RouteMapActivity.ROUTE_TYPE, type)
        intent.putExtra(RouteMapActivity.ROUTE_ID, id)
        activity?.startActivity(intent)
    }

    abstract fun getViewModel(): BaseViewModel

    abstract fun getTransportType(): String
}