package ru.sergeykamyshov.rostovtransport.presentation.transport.list

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.hannesdorfmann.adapterdelegates4.AbsListItemAdapterDelegate
import kotlinx.android.synthetic.main.item_route.view.*
import ru.sergeykamyshov.rostovtransport.R
import ru.sergeykamyshov.rostovtransport.base.extentions.onClickDebounce

class RouteDelegate(
        private val context: Context,
        private val callback: RoutesListAdapter.Callback,
        private val adapter: RoutesListAdapter
) : AbsListItemAdapterDelegate<Route, Any, RouteDelegate.RouteViewHolder>() {

    private val layoutInflater = LayoutInflater.from(context)

    override fun onCreateViewHolder(parent: ViewGroup): RouteViewHolder {
        val view = layoutInflater.inflate(R.layout.item_route, parent, false)
        return RouteViewHolder(view).apply {
            itemView.onClickDebounce {
                callback.onRouteClick(adapter.items[adapterPosition] as Route)
            }
            itemView.iv_favorite.onClickDebounce {
                callback.onFavoriteClick(adapter.items[adapterPosition] as Route)
            }
            itemView.iv_not_favorite.onClickDebounce {
                callback.onNotFavoriteClick(adapter.items[adapterPosition] as Route)
            }
        }
    }

    override fun isForViewType(item: Any, items: MutableList<Any>, position: Int): Boolean {
        return items[position] is Route
    }

    override fun onBindViewHolder(item: Route, holder: RouteViewHolder, payloads: MutableList<Any>) {
        holder.tvRoute.text = item.number
        // TODO: Route needs flag isFavorite
    }

    class RouteViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvRoute: TextView = itemView.tv_route
        val ivNotFavorite: ImageView = itemView.iv_not_favorite
        val ivFavorite: ImageView = itemView.iv_favorite
    }

}