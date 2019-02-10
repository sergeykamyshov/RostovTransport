package ru.sergeykamyshov.rostovtransport.presentation.about

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.hannesdorfmann.adapterdelegates4.AdapterDelegate
import ru.sergeykamyshov.rostovtransport.R

class TextCardDelegate(private val context: Context) : AdapterDelegate<List<Any>>() {

    private val inflater: LayoutInflater = LayoutInflater.from(context)

    override fun isForViewType(items: List<Any>, position: Int): Boolean {
        return items[position] is CardItem.TextCard
    }

    override fun onCreateViewHolder(parent: ViewGroup): RecyclerView.ViewHolder {
        return ViewHolder(inflater.inflate(R.layout.about_card_text, parent, false))
    }

    override fun onBindViewHolder(items: List<Any>, position: Int, holder: RecyclerView.ViewHolder, payloads: List<Any>) {

    }

    internal class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

}