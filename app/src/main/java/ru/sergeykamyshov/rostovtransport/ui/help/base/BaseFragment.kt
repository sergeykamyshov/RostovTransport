package ru.sergeykamyshov.rostovtransport.ui.help.base

import android.arch.lifecycle.Observer
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import ru.sergeykamyshov.rostovtransport.R

abstract class BaseFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_list_help, container, false)

        val recycler = view.findViewById<RecyclerView>(R.id.rv_help)
        recycler.layoutManager = LinearLayoutManager(activity)
        recycler.addItemDecoration(DividerItemDecoration(activity, DividerItemDecoration.VERTICAL))
        val adapter = BaseAdapter(activity, ArrayList())
        recycler.adapter = adapter

        val viewModel = getViewModel()
        val liveData = viewModel.getData()
        liveData.observe(this, Observer {
            if (it != null) {
                adapter.updateData(it)
            }
        })
        viewModel.loadData()

        return view
    }

    abstract fun getViewModel(): BaseViewModel

}