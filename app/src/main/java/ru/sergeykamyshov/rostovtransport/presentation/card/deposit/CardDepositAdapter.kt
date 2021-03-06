package ru.sergeykamyshov.rostovtransport.presentation.card.deposit

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.item_card_deposit.view.*
import ru.sergeykamyshov.rostovtransport.R
import ru.sergeykamyshov.rostovtransport.domain.card.DepositAddress

class CardDepositAdapter(
        private val context: Context
) : RecyclerView.Adapter<CardDepositAdapter.ViewHolder>() {

    private val layoutInflater = LayoutInflater.from(context)
    private var items: List<DepositAddress> = emptyList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(layoutInflater.inflate(R.layout.item_card_deposit, parent, false))
    }

    override fun getItemCount() = items.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val address = items[position]
        holder.desc.text = address.desc
        holder.address.text = address.address
        holder.schedule.text = address.schedule
    }

    fun update(items: List<DepositAddress>) {
        this.items = items
        notifyDataSetChanged()
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val desc: TextView = itemView.tv_card_deposit_desc
        val address: TextView = itemView.tv_card_deposit_address
        val schedule: TextView = itemView.tv_card_deposit_schedule
    }

}
