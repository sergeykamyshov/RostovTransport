package ru.sergeykamyshov.rostovtransport.presentation.complain

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.item_violation.view.*
import ru.sergeykamyshov.rostovtransport.R
import ru.sergeykamyshov.rostovtransport.base.extentions.onClickDebounce

class ViolationsAdapter(var data: List<ViolationItem>) : RecyclerView.Adapter<ViolationsAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.item_violation, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int = data.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(data[position])
    }

    fun getCheckedPositions(): List<Int> {
        val positions = mutableListOf<Int>()
        for (i in 0 until data.size) {
            if (data[i].checked) positions.add(i)
        }
        return positions
    }

    fun getCheckedItems(): List<ViolationItem> {
        val items = mutableListOf<ViolationItem>()
        for (item in data) {
            if (item.checked) items.add(item)
        }
        return items
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(item: ViolationItem) {
            itemView.cb_violation.text = item.name
            itemView.cb_violation.isChecked = item.checked
            itemView.cb_violation.onClickDebounce {
                item.checked = itemView.cb_violation.isChecked
            }
        }
    }
}