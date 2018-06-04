package ru.sergeykamyshov.rostovtransport.ui.schedule

import android.support.v4.app.FragmentActivity
import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import ru.sergeykamyshov.rostovtransport.R

class ScheduleAdapter(var mContext: FragmentActivity?, var mData: List<String>) : RecyclerView.Adapter<ScheduleAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = mContext?.layoutInflater?.inflate(R.layout.recycler_item_schedule, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return mData.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.cityName?.text = mData.get(position)
    }

    class ViewHolder(itemView: View?) : RecyclerView.ViewHolder(itemView) {
        val cityName = itemView?.findViewById<TextView>(R.id.tv_city_name)
    }

}
