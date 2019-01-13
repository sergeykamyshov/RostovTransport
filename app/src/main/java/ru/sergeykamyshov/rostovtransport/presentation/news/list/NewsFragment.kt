package ru.sergeykamyshov.rostovtransport.presentation.news.list

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.fragment_news.view.*
import ru.sergeykamyshov.rostovtransport.R
import ru.sergeykamyshov.rostovtransport.presentation.base.BaseFragment
import ru.sergeykamyshov.rostovtransport.presentation.base.OnItemClickListener
import ru.sergeykamyshov.rostovtransport.presentation.main.MainActivity
import ru.sergeykamyshov.rostovtransport.presentation.news.post.PostActivity

class NewsFragment : BaseFragment(), OnItemClickListener {

    companion object {
        fun newInstance() = NewsFragment()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_news, container, false)

        setActionBarTitle(R.string.title_news)

        val recycler = view.news_recycler
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
            view.news_progress.visibility = View.GONE
        })
        viewModel.loadData()

        return view
    }

    override fun onItemClick(id: String) {
        val intent = Intent(activity, PostActivity::class.java)
        intent.putExtra(PostActivity.POST_ID_EXTRA, id)
        startActivity(intent)
    }
}