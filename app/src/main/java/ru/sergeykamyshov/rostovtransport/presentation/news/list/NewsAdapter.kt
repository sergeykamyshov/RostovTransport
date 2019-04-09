package ru.sergeykamyshov.rostovtransport.presentation.news.list

import android.content.Context
import android.text.Html
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_news.view.*
import ru.sergeykamyshov.rostovtransport.R
import ru.sergeykamyshov.rostovtransport.base.extentions.onClickDebounce
import ru.sergeykamyshov.rostovtransport.domain.news.Post
import java.text.SimpleDateFormat
import java.util.*

class NewsAdapter(
        private var context: Context,
        private var callback: Callback
) : RecyclerView.Adapter<NewsAdapter.ViewHolder>() {

    private val TARGET_IMG_WIDTH = 300
    private val TARGET_IMG_HEIGHT = 150
    private val layoutInflater = LayoutInflater.from(context)
    private var items: List<Post> = emptyList()

    interface Callback {
        fun onPostClick(id: Long)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val holder = ViewHolder(layoutInflater.inflate(R.layout.item_news, parent, false))

        holder.itemView.onClickDebounce {
            val post = items[holder.adapterPosition]
            callback.onPostClick(post.id)
        }

        return holder
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val post = items[position]
        if (post.thumbnail != null) {
            Picasso.get()
                    .load(post.thumbnailMedium)
                    .resize(TARGET_IMG_WIDTH, TARGET_IMG_HEIGHT)
                    .centerCrop()
                    .into(holder.newsThumbnail)
        } else {
            // Если картинку невозможно загрузить из интернета, то устанавливаем картинку по-умолчанию
            Picasso.get()
                    .load(getDefaultThumbnail(position))
                    .resize(TARGET_IMG_WIDTH, TARGET_IMG_HEIGHT)
                    .centerCrop()
                    .into(holder.newsThumbnail)
        }

        // Внимание! В заголовках статей могут быть указаны html теги
        holder.newsTitle.text = Html.fromHtml(post.title)
        // TODO: SK 06-Apr-19 Format data when create object
        // Приводим дату к формат "dd.MM.yyyy"
        val date = SimpleDateFormat("yyyy-MM-dd hh:mm:ss", Locale.US).parse(post.date)
        holder.newsDate.text = SimpleDateFormat("dd.MM.yyyy", Locale.US).format(date)
    }

    fun updateData(data: List<Post>) {
        items = data
        notifyDataSetChanged()
    }

    private fun getDefaultThumbnail(position: Int): Int {
        return when {
            position % 5 == 0 -> R.drawable.img_thumbnail_news_item_5
            position % 4 == 0 -> R.drawable.img_thumbnail_news_item_4
            position % 3 == 0 -> R.drawable.img_thumbnail_news_item_3
            position % 2 == 0 -> R.drawable.img_thumbnail_news_item_2
            else -> R.drawable.img_thumbnail_news_item_1
        }
    }

    class ViewHolder(itemView: View) : androidx.recyclerview.widget.RecyclerView.ViewHolder(itemView) {
        val newsThumbnail: ImageView = itemView.img_news_thumbnail
        val newsTitle: TextView = itemView.tv_post_title
        val newsDate: TextView = itemView.tv_news_date
    }

}