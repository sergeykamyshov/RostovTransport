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
import ru.sergeykamyshov.rostovtransport.utils.PicassoImageGetter

class PostActivity : AppCompatActivity() {

    companion object {
        const val POST_ID_EXTRA = "postId"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_post)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val progressBar = findViewById<ProgressBar>(R.id.post_progress)
        val postTitle = findViewById<TextView>(R.id.tv_post_title)
        val postContent = findViewById<TextView>(R.id.tv_post_content)

        val id = intent.getStringExtra(POST_ID_EXTRA)

        val viewModel = ViewModelProviders.of(this, PostModelFactory(id)).get(PostViewModel::class.java)
        viewModel.getData().observe(this, Observer {
            // Убираем html теги из заголовка
            postTitle.text = Html.fromHtml(it?.title)
            // Убираем подписи к фотографиям
            val htmlContent = it?.content?.replace("<figcaption.+/(figcaption)*>".toRegex(), "")
            // Убираем html теги из контента и подгружаем фотографии с помощью Picasso
            postContent.text = Html.fromHtml(htmlContent, PicassoImageGetter(postContent), null)

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