package ru.sergeykamyshov.rostovtransport.presentation.help.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.fragment_list_help.*
import ru.sergeykamyshov.rostovtransport.R
import ru.sergeykamyshov.rostovtransport.base.extentions.hide
import ru.sergeykamyshov.rostovtransport.presentation.base.ViewState

abstract class BaseFragment : Fragment() {

    private var viewState: ViewState = ViewState()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_list_help, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val recycler = rv_help
        recycler.layoutManager = LinearLayoutManager(requireContext())
        recycler.addItemDecoration(DividerItemDecoration(requireContext(), DividerItemDecoration.VERTICAL))
        val adapter = BaseAdapter(requireContext(), ArrayList())
        recycler.adapter = adapter

        val viewModel = getViewModel()

        viewState.uiState = viewModel.getUiState()

        viewState.loadingView = help_progress
        viewState.dataView = rv_help
        viewState.emptyView = tv_empty
        viewState.errorView = tv_error

        viewState.init(this)

        viewModel.getData().observe(this, Observer {
            adapter.updateData(it)
            help_progress.hide()
        })
    }

    abstract fun getViewModel(): BaseViewModel

}