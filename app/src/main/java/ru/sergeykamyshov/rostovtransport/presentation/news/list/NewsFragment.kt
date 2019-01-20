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
import ru.sergeykamyshov.rostovtransport.base.extentions.hide
import ru.sergeykamyshov.rostovtransport.base.extentions.show
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
        viewModel.getData().observe(this, Observer {
            view.img_placeholder.hide()
            adapter.updateData(it)
        })
        observeLoading(view, viewModel)
        observeError(view, viewModel)

        viewModel.loadData()
        return view
    }

    private fun observeLoading(view: View, viewModel: NewsViewModel) {
        viewModel.isLoading().observe(this, Observer { loading ->
            if (loading) view.news_progress.show() else view.news_progress.hide()
        })
    }

    private fun observeError(view: View, viewModel: NewsViewModel) {
        viewModel.isError().observe(this, Observer { error ->
            if (error) {
                view.img_placeholder.show()
            }
        })
    }

    companion object {
        fun newInstance() = NewsFragment()
    }

}