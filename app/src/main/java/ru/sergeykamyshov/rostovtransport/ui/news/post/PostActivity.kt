package ru.sergeykamyshov.rostovtransport.ui.news.post

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.text.Html
import android.view.MenuItem
import android.view.View
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_post.*
import ru.sergeykamyshov.rostovtransport.R
import ru.sergeykamyshov.rostovtransport.utils.EmptyImageGetter

class PostActivity : AppCompatActivity() {

    companion object {
        const val POST_ID_EXTRA = "postId"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_post)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val id = intent.getStringExtra(POST_ID_EXTRA)

        val viewModel = ViewModelProviders.of(this, PostModelFactory(id)).get(PostViewModel::class.java)
        viewModel.getData().observe(this, Observer {
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

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item?.itemId) {
            android.R.id.home -> finish()
        }
        return super.onOptionsItemSelected(item)
    }
}