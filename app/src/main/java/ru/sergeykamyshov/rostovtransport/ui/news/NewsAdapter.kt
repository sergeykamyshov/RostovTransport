package ru.sergeykamyshov.rostovtransport.ui.news

import android.support.v4.app.FragmentActivity
import android.support.v7.widget.RecyclerView
import android.text.Html
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.squareup.picasso.Picasso
import ru.sergeykamyshov.rostovtransport.R
import ru.sergeykamyshov.rostovtransport.data.network.model.news.News.Post
import ru.sergeykamyshov.rostovtransport.ui.base.OnItemClickListener
import java.text.SimpleDateFormat
import java.util.*

class NewsAdapter(var mContext: FragmentActivity?,
                  var mData: List<Post>,
                  var mListener: OnItemClickListener) : RecyclerView.Adapter<NewsAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = mContext?.layoutInflater?.inflate(R.layout.recycler_item_news, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return mData.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val post = mData.get(position)
        if (post.thumbnail?.isNotEmpty()!!) {
            Picasso.get()
                    .load(post.thumbnail)
                    .resize(150, 100)
                    .centerCrop()
                    .into(holder.newsThumbnail)
        } else {
            // Если картинку невозможно загрузить из интернета, то устанавливаем картинку по-умолчанию
            Picasso.get()
                    .load(getDefaultThumbnail(position))
                    .resize(150, 100)
                    .centerCrop()
                    .into(holder.newsThumbnail)
        }

        // Внимание! В заголовках статей могут быть указаны html теги
        holder.newsTitle?.text = Html.fromHtml(post.title)
        holder.newsAuthor?.text = post.author.name
        // Приводим дату к формат "dd.MM.yyyy"
        val date = SimpleDateFormat("yyyy-MM-dd hh:mm:ss", Locale.US).parse(post.date)
        holder.newsDate?.text = SimpleDateFormat("dd.MM.yyyy", Locale.US).format(date)
        holder.newsId?.text = post.id

        holder.bind(mListener)
    }

    fun updateData(data: List<Post>) {
        mData = data
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

    class ViewHolder(itemView: View?) : RecyclerView.ViewHolder(itemView) {
        val container: ViewGroup? = itemView?.findViewById(R.id.container_item_news)
        val newsThumbnail: ImageView? = itemView?.findViewById(R.id.img_news_thumbnail)
        val newsTitle: TextView? = itemView?.findViewById(R.id.tv_post_title)
        val newsAuthor: TextView? = itemView?.findViewById(R.id.tv_news_author)
        val newsDate: TextView? = itemView?.findViewById(R.id.tv_news_date)
        // Скрытое поле
        val newsId: TextView? = itemView?.findViewById(R.id.tv_news_id)

        fun bind(listener: OnItemClickListener) {
            container?.setOnClickListener({
                listener.onItemClick(newsId?.text as String)
            })
        }
    }

}