package ru.sergeykamyshov.rostovtransport.ui.news

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.content.Intent
import android.os.Bundle
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import ru.sergeykamyshov.rostovtransport.MainActivity
import ru.sergeykamyshov.rostovtransport.R
import ru.sergeykamyshov.rostovtransport.ui.base.BaseFragment
import ru.sergeykamyshov.rostovtransport.ui.base.OnItemClickListener
import ru.sergeykamyshov.rostovtransport.ui.news.news.SpecificNews

class NewsFragment : BaseFragment(), OnItemClickListener {

    private lateinit var mProgress: ProgressBar

    companion object {
        fun newInstance() = NewsFragment()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_news, container, false)

        setActionBarTitle(R.string.title_news)

        mProgress = view.findViewById(R.id.news_progress)

        val recycler = view.findViewById<RecyclerView>(R.id.news_recycler)
        recycler.layoutManager = LinearLayoutManager(activity)
        val adapter = NewsAdapter(activity, ArrayList(), this)
        recycler.adapter = adapter
        recycler.addItemDecoration(DividerItemDecoration(activity, DividerItemDecoration.VERTICAL))
        recycler.setHasFixedSize(true)

        val viewModel = ViewModelProviders.of(activity as MainActivity).get(NewsViewModel::class.java)
        viewModel.getData().observe(this, Observer {
            if (it != null) {
                adapter.updateData(it)
            }
            mProgress.visibility = View.GONE
        })
        viewModel.loadData()

        return view
    }

    override fun onItemClick(news: String) {
        val intent = Intent(activity, SpecificNews::class.java)
        intent.putExtra(SpecificNews.SPECIFIC_NEWS_EXTRA, news)
        startActivity(intent)
    }
}