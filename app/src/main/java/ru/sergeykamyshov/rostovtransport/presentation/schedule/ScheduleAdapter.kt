package ru.sergeykamyshov.rostovtransport.presentation.schedule

import android.support.v4.app.FragmentActivity
import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import kotlinx.android.synthetic.main.recycler_item_schedule.view.*
import ru.sergeykamyshov.rostovtransport.R
import ru.sergeykamyshov.rostovtransport.data.network.model.schedule.Direction
import ru.sergeykamyshov.rostovtransport.presentation.base.OnItemClickListener

class ScheduleAdapter(var mContext: FragmentActivity?,
                      var mData: List<Direction>,
                      var mListener: OnItemClickListener) : RecyclerView.Adapter<ScheduleAdapter.ViewHolder>() {

    private val layoutInflater = mContext?.layoutInflater

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = layoutInflater?.inflate(R.layout.recycler_item_schedule, parent, false)
        return ViewHolder(view!!)
    }

    override fun getItemCount(): Int {
        return mData.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.cityName.text = mData.get(position).city
        holder.bind(mListener)
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val cityName: TextView = itemView.tv_city_name

        fun bind(mListener: OnItemClickListener) {
            itemView.setOnClickListener {
                mListener.onItemClick(cityName.text as String)
            }
        }
    }

    fun updateData(data: List<Direction>) {
        mData = data
        notifyDataSetChanged()
    }

}
