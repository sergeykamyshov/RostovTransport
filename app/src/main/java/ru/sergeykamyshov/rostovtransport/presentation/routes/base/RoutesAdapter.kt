package ru.sergeykamyshov.rostovtransport.presentation.routes.base

import android.support.v4.app.FragmentActivity
import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import kotlinx.android.synthetic.main.recycler_item_routes.view.*
import ru.sergeykamyshov.rostovtransport.R
import ru.sergeykamyshov.rostovtransport.data.network.model.routes.Routes

class RoutesAdapter(var mContext: FragmentActivity?,
                    var mData: List<Routes.Route>,
                    var onItemClickListener: OnItemClickListener) : RecyclerView.Adapter<RoutesAdapter.ViewHolder>() {

    private val layoutInflater = mContext?.layoutInflater

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = layoutInflater?.inflate(R.layout.recycler_item_routes, parent, false)
        return ViewHolder(view!!)
    }

    override fun getItemCount(): Int {
        return mData.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val route = mData[position]
        holder.routeNumber.text = route.number
        holder.routeName.text = route.name
        holder.itemView.setOnClickListener {
            onItemClickListener.onItemClick(route.id)
        }
    }

    fun updateData(data: List<Routes.Route>) {
        mData = data
        notifyDataSetChanged()
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val routeNumber: TextView = itemView.tv_route_number
        val routeName: TextView = itemView.tv_route_name
    }
}