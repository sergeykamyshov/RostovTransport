package ru.sergeykamyshov.rostovtransport.presentation.news.list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.fragment_news.*
import ru.sergeykamyshov.rostovtransport.R
import ru.sergeykamyshov.rostovtransport.presentation.base.BaseFragment
import ru.sergeykamyshov.rostovtransport.presentation.base.OnItemClickListener
import ru.sergeykamyshov.rostovtransport.presentation.main.MainActivity
import ru.sergeykamyshov.rostovtransport.presentation.news.post.PostActivity

class NewsFragment : BaseFragment() {

    private lateinit var navController: NavController
    private lateinit var adapter: NewsAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_news, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setActionBarTitle(R.string.title_news)

        navController = NavHostFragment.findNavController(this)

        setupRecycler()

        val viewModel = ViewModelProviders.of(activity as MainActivity).get(NewsViewModel::class.java)
        initViewState(
                this,
                viewModel.getUiState(),
                news_progress,
                news_recycler,
                errorView = tv_error
        )
        viewModel.getData().observe(this, Observer {
            adapter.updateData(it)
        })

        swipeRefresh.setOnRefreshListener {
            swipeRefresh.isRefreshing = false
            viewModel.loadData()
        }
    }

    private fun setupRecycler() {
        news_recycler.layoutManager = LinearLayoutManager(activity)
        adapter = NewsAdapter(activity, object : OnItemClickListener {
            override fun onItemClick(value: String) {
                navController.navigate(R.id.postActivity, Bundle().also {
                    it.putString(PostActivity.POST_ID_EXTRA, value)
                })
            }
        })
        news_recycler.adapter = adapter
        news_recycler.addItemDecoration(DividerItemDecoration(activity, DividerItemDecoration.VERTICAL))
        news_recycler.setHasFixedSize(true)
    }

    companion object {
        fun newInstance() = NewsFragment()
    }

}