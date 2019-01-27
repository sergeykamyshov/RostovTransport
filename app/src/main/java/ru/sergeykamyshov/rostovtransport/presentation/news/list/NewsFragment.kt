package ru.sergeykamyshov.rostovtransport.presentation.news.list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.fragment_news.view.*
import ru.sergeykamyshov.rostovtransport.R
import ru.sergeykamyshov.rostovtransport.databinding.FragmentNewsBinding
import ru.sergeykamyshov.rostovtransport.presentation.base.BaseFragment
import ru.sergeykamyshov.rostovtransport.presentation.base.OnItemClickListener
import ru.sergeykamyshov.rostovtransport.presentation.main.MainActivity
import ru.sergeykamyshov.rostovtransport.presentation.news.post.PostActivity

class NewsFragment : BaseFragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val binding = DataBindingUtil.inflate<FragmentNewsBinding>(
                inflater,
                R.layout.fragment_news,
                container,
                false
        )
        val view = binding.root

        viewState.loadingView = view.news_progress
        viewState.dataView = view.news_recycler
        viewState.errorView = view.tv_error

        setActionBarTitle(R.string.title_news)

        val recycler = view.news_recycler
        recycler.layoutManager = LinearLayoutManager(activity)
        val adapter = NewsAdapter(activity, object : OnItemClickListener {
            override fun onItemClick(value: String) {
                startActivity(PostActivity.getIntent(activity as MainActivity, value))
            }
        })
        recycler.adapter = adapter
        recycler.addItemDecoration(DividerItemDecoration(activity, DividerItemDecoration.VERTICAL))
        recycler.setHasFixedSize(true)

        val viewModel = ViewModelProviders.of(activity as MainActivity).get(NewsViewModel::class.java)
        viewState.uiState = viewModel.getUiState()
        viewModel.getData().observe(this, Observer {
            adapter.updateData(it)
        })
        viewModel.loadData()
        return view
    }

    companion object {
        fun newInstance() = NewsFragment()
    }

}