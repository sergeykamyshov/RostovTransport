package ru.sergeykamyshov.rostovtransport.ui.news.news

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.TextView
import ru.sergeykamyshov.rostovtransport.R

class SpecificNews : AppCompatActivity() {

    companion object {
        const val SPECIFIC_NEWS_EXTRA = "specificNews"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_specific_news)

        val specificNews = findViewById<TextView>(R.id.tv_specific_news_test)

        specificNews.text = intent.getStringExtra(SPECIFIC_NEWS_EXTRA)
    }
}