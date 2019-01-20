package ru.sergeykamyshov.rostovtransport.presentation.news.post

import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.text.Html
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.google.android.material.snackbar.Snackbar
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_post.*
import kotlinx.android.synthetic.main.toolbar_layout.*
import ru.sergeykamyshov.rostovtransport.BuildConfig
import ru.sergeykamyshov.rostovtransport.R
import ru.sergeykamyshov.rostovtransport.base.EmptyImageGetter
import ru.sergeykamyshov.rostovtransport.base.extentions.hide
import ru.sergeykamyshov.rostovtransport.base.extentions.openInBrowser
import ru.sergeykamyshov.rostovtransport.base.extentions.show
import ru.sergeykamyshov.rostovtransport.databinding.ActivityPostBinding

class PostActivity : AppCompatActivity() {

    private lateinit var url: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val contentView = DataBindingUtil.setContentView<ActivityPostBinding>(
                this,
                R.layout.activity_post
        )
        contentView.setLifecycleOwner(this)

        setSupportActionBar(main_toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val id = intent.getStringExtra(POST_ID_EXTRA)
        val viewModel = ViewModelProviders.of(this, PostModelFactory(id))
                .get(PostViewModel::class.java)
        contentView.viewModel = viewModel

        observeLoading(viewModel)
        observeError(viewModel)
        observeData(viewModel)
    }

    private fun observeLoading(viewModel: PostViewModel) {
        viewModel.isLoading().observe(this, Observer { loading ->
            if (loading) post_progress.show() else post_progress.hide()
        })
    }

    private fun observeError(viewModel: PostViewModel) {
        viewModel.isError().observe(this, Observer { error ->
            if (error) {
                img_placeholder.show()
                Snackbar.make(
                        vContainer,
                        getString(R.string.error_news_post_loading),
                        Snackbar.LENGTH_LONG
                ).setAction(
                        getString(R.string.common_action_repeat)
                ) { viewModel.loadData() }
                        .show()
            }
        })
    }

    private fun observeData(viewModel: PostViewModel) {
        viewModel.getData().observe(this, Observer {
            url = it?.url.toString()

            Picasso.get().load(it?.thumbnailMedium)
                    .resize(300, 150)
                    .centerCrop()
                    .into(img_post_title)
            img_post_title.show()
            img_gradient_post_title.show()

            // Убираем html теги из заголовка
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                tv_post_title.text = Html.fromHtml(it?.title, Html.FROM_HTML_MODE_LEGACY)
            } else {
                tv_post_title.text = Html.fromHtml(it?.title)
            }
            tv_post_title.show()

            var content: String?
            try {
                // Убираем подписи к фотографиям
                content = it?.content?.replace("<figcaption.+/(figcaption)*>".toRegex(), "")
                // Убираем текст из скрипта рекламы
                content = content?.replace("\\(adsbygoogle.+\\);".toRegex(), "")
                // Убираем лишние переносы строк
                content = content?.replace("<br />", "")
                content = content?.replace("<p></p>", "")
            } catch (throwable: Throwable) {
                viewModel.initError(throwable)
                return@Observer
            }

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                tv_post_content.text = Html.fromHtml(content, Html.FROM_HTML_MODE_LEGACY, EmptyImageGetter(), null)
            } else {
                tv_post_content.text = Html.fromHtml(content, EmptyImageGetter(), null)
            }
            tv_post_content.show()
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