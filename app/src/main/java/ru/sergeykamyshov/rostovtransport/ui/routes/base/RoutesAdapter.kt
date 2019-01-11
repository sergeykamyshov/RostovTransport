package ru.sergeykamyshov.rostovtransport.ui.routes.base

import android.support.v4.app.FragmentActivity
import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import ru.sergeykamyshov.rostovtransport.R
import ru.sergeykamyshov.rostovtransport.data.network.model.routes.Routes

class RoutesAdapter(var mContext: FragmentActivity?,
                    var mData: List<Routes.Route>,
                    var onItemClickListener: OnItemClickListener) : RecyclerView.Adapter<RoutesAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = mContext?.layoutInflater?.inflate(R.layout.recycler_item_routes, parent, false)
        return ViewHolder(view!!)
    }

    override fun getItemCount(): Int {
        return mData.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val route = mData.get(position)
        holder.routeNumber?.text = route.number
        holder.routeName?.text = route.name
        holder.container?.setOnClickListener {
            onItemClickListener.onItemClick(route.id)
        }
    }

    fun updateData(data: List<Routes.Route>) {
        mData = data
        notifyDataSetChanged()
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val container = itemView?.findViewById<ViewGroup>(R.id.container_item_routes)
        val routeNumber = itemView?.findViewById<TextView>(R.id.tv_route_number)
        val routeName = itemView?.findViewById<TextView>(R.id.tv_route_name)
    }
}