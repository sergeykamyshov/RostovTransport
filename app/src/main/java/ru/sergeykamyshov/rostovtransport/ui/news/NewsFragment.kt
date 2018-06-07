package ru.sergeykamyshov.rostovtransport.ui.news

import android.content.Intent
import android.os.Bundle
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import ru.sergeykamyshov.rostovtransport.R
import ru.sergeykamyshov.rostovtransport.ui.base.BaseFragment
import ru.sergeykamyshov.rostovtransport.ui.base.OnItemClickListener
import ru.sergeykamyshov.rostovtransport.ui.news.NewsContract.MvpView
import ru.sergeykamyshov.rostovtransport.ui.news.news.SpecificNews

class NewsFragment : BaseFragment(), MvpView, OnItemClickListener {

    private lateinit var mPresenter: NewsContract.MvpPresenter

    companion object {
        fun newInstance() = NewsFragment()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_news, container, false)

        setActionBarTitle(R.string.title_news)

        mPresenter = NewsPresenter()
        mPresenter.onAttach(this)

        val recycler = view.findViewById<RecyclerView>(R.id.news_recycler)
        recycler.layoutManager = LinearLayoutManager(activity)
        recycler.adapter = NewsAdapter(activity, getTestNews(), this)
        recycler.addItemDecoration(DividerItemDecoration(activity, DividerItemDecoration.VERTICAL))
        recycler.setHasFixedSize(true)

        return view
    }

    override fun onDestroyView() {
        mPresenter.onDetach()
        super.onDestroyView()
    }

    // TODO: удалить после тестирования
    private fun getTestNews(): List<String> {
        val news = mutableListOf<String>()
        val testTitle = activity?.resources?.getString(R.string.test_news_title)
        for (i in 1..100) {
            news.add("Новость $i. $testTitle")
        }
        return news
    }

    override fun onItemClick(news: String) {
        val intent = Intent(activity, SpecificNews::class.java)
        intent.putExtra(SpecificNews.SPECIFIC_NEWS_EXTRA, news)
        startActivity(intent)
    }
}