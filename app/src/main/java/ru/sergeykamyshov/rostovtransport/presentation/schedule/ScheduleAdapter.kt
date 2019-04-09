package ru.sergeykamyshov.rostovtransport.presentation.schedule

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.item_schedule.view.*
import ru.sergeykamyshov.rostovtransport.R
import ru.sergeykamyshov.rostovtransport.data.models.schedule.Direction

class ScheduleAdapter(
        private val context: Context,
        private var items: List<Direction>
) : RecyclerView.Adapter<ScheduleAdapter.ViewHolder>() {

    private val layoutInflater = LayoutInflater.from(context)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = layoutInflater.inflate(R.layout.item_schedule, parent, false)
        return ViewHolder(view!!)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.cityName.text = items.get(position).city
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val cityName: TextView = itemView.tv_city_name
    }

    fun updateData(data: List<Direction>) {
        items = data
        notifyDataSetChanged()
    }

}
