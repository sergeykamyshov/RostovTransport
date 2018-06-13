package ru.sergeykamyshov.rostovtransport.ui.news.specific

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.text.Html
import android.view.View
import android.widget.ProgressBar
import android.widget.TextView
import ru.sergeykamyshov.rostovtransport.R

class SpecificNews : AppCompatActivity() {

    companion object {
        const val SPECIFIC_NEWS_ID_EXTRA = "specificNewsId"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_specific_news)

        val progressBar = findViewById<ProgressBar>(R.id.specific_news_progress)
        val newsTitle = findViewById<TextView>(R.id.tv_news_title)
        val newsContent = findViewById<TextView>(R.id.tv_news_content)

        val id = intent.getStringExtra(SPECIFIC_NEWS_ID_EXTRA)

        val viewModel = ViewModelProviders.of(this, SpecificNewsModelFactory(id)).get(SpecificNewsViewModel::class.java)
        viewModel.getData().observe(this, Observer {
            newsTitle.text = it?.title
            newsContent.text = Html.fromHtml(it?.content)
            progressBar.visibility = View.GONE

        })
        viewModel.loadData()
    }
}