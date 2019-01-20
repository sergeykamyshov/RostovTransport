package ru.sergeykamyshov.rostovtransport.presentation.schedule

import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.FragmentActivity
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.recycler_item_schedule.view.*
import ru.sergeykamyshov.rostovtransport.R
import ru.sergeykamyshov.rostovtransport.data.models.schedule.Direction
import ru.sergeykamyshov.rostovtransport.presentation.base.OnItemClickListener

class ScheduleAdapter(
        var context: FragmentActivity?,
        var items: List<Direction>,
        var listener: OnItemClickListener
) : RecyclerView.Adapter<ScheduleAdapter.ViewHolder>() {

    private val layoutInflater = context?.layoutInflater

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = layoutInflater?.inflate(R.layout.recycler_item_schedule, parent, false)
        return ViewHolder(view!!)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.cityName.text = items.get(position).city
        holder.bind(listener)
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
        items = data
        notifyDataSetChanged()
    }

}
