package ru.sergeykamyshov.rostovtransport.presentation.menu

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.hannesdorfmann.adapterdelegates4.AdapterDelegate
import ru.sergeykamyshov.rostovtransport.R

class SimpleOptionDelegate(
        private val context: Context
) : AdapterDelegate<List<MenuOption>>() {

    private val layoutInflater = LayoutInflater.from(context)

    override fun onCreateViewHolder(parent: ViewGroup): RecyclerView.ViewHolder {
        return SimpleOptionViewHolder(layoutInflater.inflate(R.layout.item_menu_option, parent, false))
    }

    override fun isForViewType(items: List<MenuOption>, position: Int): Boolean {
        return items[position] is SimpleOption
    }

    override fun onBindViewHolder(items: List<MenuOption>, position: Int, holder: RecyclerView.ViewHolder, payloads: MutableList<Any>) {
        val menuOption = items[position] as SimpleOption
        val viewHolder = holder as SimpleOptionViewHolder
        viewHolder.ivIcon.setImageDrawable(ContextCompat.getDrawable(context, menuOption.iconId))
        viewHolder.tvTitle.text = context.getString(menuOption.textId)
    }

    class SimpleOptionViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val ivIcon: ImageView = itemView.findViewById(R.id.iv_menu_icon)
        val tvTitle: TextView = itemView.findViewById(R.id.tv_menu_title)
    }

}