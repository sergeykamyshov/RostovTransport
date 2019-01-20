package ru.sergeykamyshov.rostovtransport.presentation.card.deposit

import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.FragmentActivity
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.recycler_item_card_deposit.view.*
import ru.sergeykamyshov.rostovtransport.R
import ru.sergeykamyshov.rostovtransport.data.models.card.CardDeposit

class CardDepositAdapter(
        var context: FragmentActivity?,
        var items: List<CardDeposit.Address>
) : RecyclerView.Adapter<CardDepositAdapter.ViewHolder>() {

    private val layoutInflater = context?.layoutInflater

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = layoutInflater?.inflate(R.layout.recycler_item_card_deposit, parent, false)
        return ViewHolder(view!!)
    }

    override fun getItemCount() = items.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val address = items[position]
        holder.desc.text = address.desc
        holder.address.text = address.address
        holder.schedule.text = address.schedule
    }

    fun updateData(data: List<CardDeposit.Address>) {
        items = data
        notifyDataSetChanged()
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var desc: TextView = itemView.tv_card_deposit_desc
        var address: TextView = itemView.tv_card_deposit_address
        var schedule: TextView = itemView.tv_card_deposit_schedule
    }

}
