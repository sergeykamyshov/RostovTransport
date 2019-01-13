package ru.sergeykamyshov.rostovtransport.presentation.news.post

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.text.Html
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_post.*
import kotlinx.android.synthetic.main.toolbar_layout.*
import ru.sergeykamyshov.rostovtransport.BuildConfig
import ru.sergeykamyshov.rostovtransport.R
import ru.sergeykamyshov.rostovtransport.base.EmptyImageGetter
import ru.sergeykamyshov.rostovtransport.base.extentions.openInBrowser

class PostActivity : AppCompatActivity() {

    private lateinit var url: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_post)

        setSupportActionBar(main_toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val id = intent.getStringExtra(POST_ID_EXTRA)

        val viewModel = ViewModelProviders.of(this, PostModelFactory(id)).get(PostViewModel::class.java)
        viewModel.getData().observe(this, Observer {
            url = it?.url.toString()

            Picasso.get().load(it?.thumbnailImages?.medium?.url)
                    .resize(300, 150)
                    .centerCrop()
                    .into(img_post_title)
            img_post_title.visibility = View.VISIBLE
            img_gradient_post_title.visibility = View.VISIBLE

            // Убираем html теги из заголовка
            tv_post_title.text = Html.fromHtml(it?.title)
            tv_post_title.visibility = View.VISIBLE

            // Убираем подписи к фотографиям
            var content = it?.content?.replace("<figcaption.+/(figcaption)*>".toRegex(), "")
            // Убираем текст из скрипта рекламы
            content = content?.replace("\\(adsbygoogle.+\\);".toRegex(), "");
            // Убираем лишние переносы строк
            content = content?.replace("<br />", "");
            content = content?.replace("<p></p>", "");

            tv_post_content.text = Html.fromHtml(content, EmptyImageGetter(), null)
            tv_post_content.visibility = View.VISIBLE

            post_progress.visibility = View.GONE
        })
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_news_post, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item?.itemId) {
            android.R.id.home -> finish()
            R.id.menu_item_open_in_browser -> openInBrowser(url)
        }
        return super.onOptionsItemSelected(item)
    }

    companion object {
        const val POST_ID_EXTRA = "${BuildConfig.APPLICATION_ID}.PostActivity.POST_ID"

        fun getIntent(context: Context, postId: String): Intent {
            val intent = Intent(context, PostActivity::class.java)
            intent.putExtra(PostActivity.POST_ID_EXTRA, postId)
            return intent
        }
    }

}