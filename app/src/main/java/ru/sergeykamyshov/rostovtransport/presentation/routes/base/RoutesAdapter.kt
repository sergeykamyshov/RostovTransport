package ru.sergeykamyshov.rostovtransport.presentation.routes.base

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.recycler_item_routes.view.*
import ru.sergeykamyshov.rostovtransport.R
import ru.sergeykamyshov.rostovtransport.data.models.routes.Routes

class RoutesAdapter(
        private val context: Context,
        private var items: List<Routes.Route>
) : RecyclerView.Adapter<RoutesAdapter.ViewHolder>() {

    private val layoutInflater = LayoutInflater.from(context)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = layoutInflater.inflate(R.layout.recycler_item_routes, parent, false)
        return ViewHolder(view!!)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val route = items[position]
        holder.routeNumber.text = route.number
        holder.routeName.text = route.name
    }

    fun updateData(data: List<Routes.Route>) {
        items = data
        notifyDataSetChanged()
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val routeNumber: TextView = itemView.tv_route_number
        val routeName: TextView = itemView.tv_route_name
    }
}