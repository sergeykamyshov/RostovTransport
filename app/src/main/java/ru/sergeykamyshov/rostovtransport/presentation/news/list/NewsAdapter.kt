package ru.sergeykamyshov.rostovtransport.presentation.news.list

import android.text.Html
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.FragmentActivity
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.recycler_item_news.view.*
import ru.sergeykamyshov.rostovtransport.R
import ru.sergeykamyshov.rostovtransport.base.extentions.onClickDebounce
import ru.sergeykamyshov.rostovtransport.domain.news.Post
import ru.sergeykamyshov.rostovtransport.presentation.base.OnItemClickListener
import java.text.SimpleDateFormat
import java.util.*

class NewsAdapter(
        var context: FragmentActivity?,
        var listener: OnItemClickListener
) : RecyclerView.Adapter<NewsAdapter.ViewHolder>() {

    private val TARGET_IMG_WIDTH = 300
    private val TARGET_IMG_HEIGHT = 150
    private val layoutInflater = context?.layoutInflater
    private var items: List<Post> = emptyList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = layoutInflater?.inflate(R.layout.recycler_item_news, parent, false)
        return ViewHolder(view!!)
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
        holder.newsTitle?.text = Html.fromHtml(post.title)
        // Приводим дату к формат "dd.MM.yyyy"
        val date = SimpleDateFormat("yyyy-MM-dd hh:mm:ss", Locale.US).parse(post.date)
        holder.newsDate?.text = SimpleDateFormat("dd.MM.yyyy", Locale.US).format(date)
        holder.newsId?.text = post.id

        holder.bind(listener)
    }

    fun updateData(data: List<Post>) {
        items = data
        notifyDataSetChanged()
    }

    private fun getDefaultThumbnail(position: Int): Int {
        if (position % 5 == 0) {
            return R.drawable.img_thumbnail_news_item_5
        } else if (position % 4 == 0) {
            return R.drawable.img_thumbnail_news_item_4
        } else if (position % 3 == 0) {
            return R.drawable.img_thumbnail_news_item_3
        } else if (position % 2 == 0) {
            return R.drawable.img_thumbnail_news_item_2
        }
        return R.drawable.img_thumbnail_news_item_1
    }

    class ViewHolder(itemView: View) : androidx.recyclerview.widget.RecyclerView.ViewHolder(itemView) {
        val newsThumbnail: ImageView? = itemView.img_news_thumbnail
        val newsTitle: TextView? = itemView.tv_post_title
        val newsDate: TextView? = itemView.tv_news_date
        // Скрытое поле
        val newsId: TextView? = itemView.tv_news_id

        fun bind(listener: OnItemClickListener) {
            itemView.onClickDebounce {
                listener.onItemClick(newsId?.text as String)
            }
        }
    }

}