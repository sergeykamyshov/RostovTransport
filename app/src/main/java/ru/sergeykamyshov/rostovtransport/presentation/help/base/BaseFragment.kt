package ru.sergeykamyshov.rostovtransport.presentation.help.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.fragment_list_help.view.*
import ru.sergeykamyshov.rostovtransport.R
import ru.sergeykamyshov.rostovtransport.base.extentions.hide
import ru.sergeykamyshov.rostovtransport.base.utils.AnalyticsUtils
import ru.sergeykamyshov.rostovtransport.presentation.base.ViewState

abstract class BaseFragment : Fragment() {

    private var viewState: ViewState = ViewState()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_list_help, container, false)

        AnalyticsUtils.logContentViewEvent(getContentType())

        val recycler = view.rv_help
        recycler.layoutManager = LinearLayoutManager(activity)
        recycler.addItemDecoration(DividerItemDecoration(activity, DividerItemDecoration.VERTICAL))
        val adapter = BaseAdapter(activity, ArrayList())
        recycler.adapter = adapter

        val viewModel = getViewModel()

        viewState.uiState = viewModel.getUiState()

        viewState.loadingView = view.help_progress
        viewState.dataView = view.rv_help
        viewState.emptyView = view.tv_empty
        viewState.errorView = view.tv_error

        viewState.init(this)

        viewModel.getData().observe(this, Observer {
            adapter.updateData(it)
            view.help_progress.hide()
        })

        return view
    }

    abstract fun getViewModel(): BaseViewModel

    abstract fun getContentType(): String

}