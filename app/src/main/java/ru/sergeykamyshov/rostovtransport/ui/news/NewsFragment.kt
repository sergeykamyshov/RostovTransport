package ru.sergeykamyshov.rostovtransport.ui.news

import android.content.Intent
import android.os.Bundle
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import ru.sergeykamyshov.rostovtransport.MainActivity
import ru.sergeykamyshov.rostovtransport.R
import ru.sergeykamyshov.rostovtransport.data.network.news.News
import ru.sergeykamyshov.rostovtransport.ui.base.BaseFragment
import ru.sergeykamyshov.rostovtransport.ui.base.OnItemClickListener
import ru.sergeykamyshov.rostovtransport.ui.news.NewsContract.MvpView
import ru.sergeykamyshov.rostovtransport.ui.news.news.SpecificNews

class NewsFragment : BaseFragment(), MvpView, OnItemClickListener {

    private lateinit var mPresenter: NewsContract.MvpPresenter
    private lateinit var mProgress: ProgressBar

    companion object {
        fun newInstance() = NewsFragment()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_news, container, false)

        setActionBarTitle(R.string.title_news)

        mProgress = view.findViewById(R.id.news_progress)

        mPresenter = NewsPresenter()
        mPresenter.onAttach(this)

        val recycler = view.findViewById<RecyclerView>(R.id.news_recycler)
        recycler.layoutManager = LinearLayoutManager(activity)
        val adapter = NewsAdapter(activity, ArrayList(), this)
        recycler.adapter = adapter
        recycler.addItemDecoration(DividerItemDecoration(activity, DividerItemDecoration.VERTICAL))
        recycler.setHasFixedSize(true)

        val call = (activity as MainActivity).restService.getRecentNews()
        call.enqueue(object : Callback<News> {
            override fun onResponse(call: Call<News>?, response: Response<News>?) {
                Log.i("NewsFragment", "Response successfull = ${response?.isSuccessful}")
                val news = response?.body()
                if (news?.posts != null) {
                    Log.i("NewsFragment", "Posts not null")
                    adapter.updateData(news.posts)
                    mProgress.visibility = View.GONE
                }
            }

            override fun onFailure(call: Call<News>?, t: Throwable?) {
                Log.i("NewsFragment", "Failed to get recent posts: $t")
            }
        })

        return view
    }

    override fun onDestroyView() {
        mPresenter.onDetach()
        super.onDestroyView()
    }

    override fun onItemClick(news: String) {
        val intent = Intent(activity, SpecificNews::class.java)
        intent.putExtra(SpecificNews.SPECIFIC_NEWS_EXTRA, news)
        startActivity(intent)
    }
}