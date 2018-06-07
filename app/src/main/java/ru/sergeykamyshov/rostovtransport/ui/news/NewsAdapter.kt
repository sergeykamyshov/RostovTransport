package ru.sergeykamyshov.rostovtransport.ui.news

import android.support.v4.app.FragmentActivity
import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import ru.sergeykamyshov.rostovtransport.R
import ru.sergeykamyshov.rostovtransport.ui.base.OnItemClickListener

class NewsAdapter(var mContext: FragmentActivity?,
                  var mData: List<String>,
                  var mListener: OnItemClickListener) : RecyclerView.Adapter<NewsAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = mContext?.layoutInflater?.inflate(R.layout.recycler_item_news, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return mData.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.newsThumbnail?.setImageResource(R.drawable.img_test_thumbnail)
        holder.newsTitle?.text = mData.get(position)
        holder.bind(mListener)
    }

    class ViewHolder(itemView: View?) : RecyclerView.ViewHolder(itemView) {
        val container: ViewGroup? = itemView?.findViewById(R.id.container_item_news)
        val newsThumbnail: ImageView? = itemView?.findViewById(R.id.img_news_thumbnail)
        val newsTitle: TextView? = itemView?.findViewById(R.id.tv_news_title)

        fun bind(listener: OnItemClickListener) {
            container?.setOnClickListener({
                listener.onItemClick(newsTitle?.text as String)
            })
        }
    }

}