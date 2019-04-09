package ru.sergeykamyshov.rostovtransport.presentation.transport

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.hannesdorfmann.adapterdelegates4.AbsListItemAdapterDelegate
import kotlinx.android.synthetic.main.recycler_item_route.view.*
import ru.sergeykamyshov.rostovtransport.R

class RouteDelegate(
        private val context: Context,
        private val callback: RoutesListAdapter.Callback,
        private val adapter: RoutesListAdapter
) : AbsListItemAdapterDelegate<Route, Any, RouteDelegate.RouteViewHolder>() {

    private val layoutInflater = LayoutInflater.from(context)

    override fun onCreateViewHolder(parent: ViewGroup): RouteViewHolder {
        return RouteViewHolder(layoutInflater.inflate(R.layout.recycler_item_route, parent, false))
    }

    override fun isForViewType(item: Any, items: MutableList<Any>, position: Int): Boolean {
        return items[position] is Route
    }

    override fun onBindViewHolder(item: Route, holder: RouteViewHolder, payloads: MutableList<Any>) {
        holder.tvRoute.text = item.number
    }

    class RouteViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvRoute: TextView = itemView.tv_route
    }

}