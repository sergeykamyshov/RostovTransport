package ru.sergeykamyshov.rostovtransport.presentation.news.post

import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.text.Html
import android.view.Menu
import android.view.MenuItem
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_post.*
import kotlinx.android.synthetic.main.toolbar_layout.*
import ru.sergeykamyshov.rostovtransport.BuildConfig
import ru.sergeykamyshov.rostovtransport.R
import ru.sergeykamyshov.rostovtransport.base.EmptyImageGetter
import ru.sergeykamyshov.rostovtransport.base.extentions.openInBrowser
import ru.sergeykamyshov.rostovtransport.databinding.ActivityPostBinding
import ru.sergeykamyshov.rostovtransport.presentation.base.BaseActivity

class PostActivity : BaseActivity() {

    private lateinit var url: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = DataBindingUtil.setContentView<ActivityPostBinding>(
                this,
                R.layout.activity_post
        )
        binding.setLifecycleOwner(this)

        setSupportActionBar(main_toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val id = intent.getStringExtra(POST_ID_EXTRA)
        val viewModel = ViewModelProviders.of(this, PostModelFactory(id))
                .get(PostViewModel::class.java)
        binding.viewModel = viewModel

        initViewState(
                this,
                viewModel.getUiState(),
                post_progress,
                v_post,
                errorView = tv_error
        )

        observeData(viewModel)
    }

    private fun observeData(viewModel: PostViewModel) {
        viewModel.getData().observe(this, Observer {
            url = it.url

            if (it.thumbnailMedium != null) {
                Picasso.get().load(it.thumbnailMedium)
                        .resize(300, 150)
                        .centerCrop()
                        .into(img_post_title)
            } else {
                Picasso.get().load(R.drawable.img_thumbnail_news_item_4)
                        .resize(300, 150)
                        .centerCrop()
                        .into(img_post_title)
            }

            val content = try {
                // Убираем подписи к фотографиям
                var content = it.content.replace("<figcaption.+/(figcaption)*>".toRegex(), "")
                // Убираем текст из скрипта рекламы
                content = content.replace("\\(adsbygoogle.+\\);".toRegex(), "")
                // Убираем лишние переносы строк
                content = content.replace("<br />", "")
                content.replace("<p></p>", "")
            } catch (throwable: Throwable) {
                viewModel.initError(throwable)
                return@Observer
            }

            // Убираем html теги
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                tv_post_title.text = Html.fromHtml(it.title, Html.FROM_HTML_MODE_LEGACY)
                tv_post_content.text = Html.fromHtml(content, Html.FROM_HTML_MODE_LEGACY, EmptyImageGetter(), null)
            } else {
                tv_post_title.text = Html.fromHtml(it.title)
                tv_post_content.text = Html.fromHtml(content, EmptyImageGetter(), null)
            }
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