package ru.sergeykamyshov.rostovtransport.ui.news

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import ru.sergeykamyshov.rostovtransport.R
import ru.sergeykamyshov.rostovtransport.ui.news.NewsContract.MvpView

class NewsFragment : Fragment(), MvpView {

    private lateinit var mPresenter: NewsPresenter

    companion object {
        fun newInstance() = NewsFragment()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_news, container, false)

        mPresenter = NewsPresenter()
        mPresenter.onAttach(this)

        val textView = view.findViewById<TextView>(R.id.tv_news_test)
        textView.text = "News"

        return view
    }

    override fun onDestroyView() {
        mPresenter.onDetach()
        super.onDestroyView()
    }
}