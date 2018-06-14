package ru.sergeykamyshov.rostovtransport.ui.news.post

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.text.Html
import android.view.MenuItem
import android.view.View
import android.widget.ProgressBar
import android.widget.TextView
import ru.sergeykamyshov.rostovtransport.R

class PostActivity : AppCompatActivity() {

    companion object {
        const val POST_ID_EXTRA = "postId"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_post)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val progressBar = findViewById<ProgressBar>(R.id.post_progress)
        val newsTitle = findViewById<TextView>(R.id.tv_news_title)
        val newsContent = findViewById<TextView>(R.id.tv_news_content)

        val id = intent.getStringExtra(POST_ID_EXTRA)

        val viewModel = ViewModelProviders.of(this, PostModelFactory(id)).get(PostViewModel::class.java)
        viewModel.getData().observe(this, Observer {
            newsTitle.text = it?.title
            newsContent.text = Html.fromHtml(it?.content)
            progressBar.visibility = View.GONE

        })
        viewModel.loadData()
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item?.itemId) {
            android.R.id.home -> finish()
        }
        return super.onOptionsItemSelected(item)
    }
}