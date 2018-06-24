package ru.sergeykamyshov.rostovtransport.ui.card.deposit

import android.support.v4.app.FragmentActivity
import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import ru.sergeykamyshov.rostovtransport.R
import ru.sergeykamyshov.rostovtransport.data.network.model.card.CardDeposit

class CardDepositAdapter(var mContext: FragmentActivity?,
                         var mData: List<CardDeposit.Address>) : RecyclerView.Adapter<CardDepositAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = mContext?.layoutInflater?.inflate(R.layout.recycler_item_card_deposit, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount() = mData.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val address = mData.get(position)
        holder.desc?.text = address.desc
        holder.address?.text = address.address
        holder.schedule?.text = address.schedule
    }

    fun updateData(data: List<CardDeposit.Address>) {
        mData = data
        notifyDataSetChanged()
    }

    class ViewHolder(itemView: View?) : RecyclerView.ViewHolder(itemView) {
        var desc = itemView?.findViewById<TextView>(R.id.tv_card_deposit_desc)
        var address = itemView?.findViewById<TextView>(R.id.tv_card_deposit_address)
        var schedule = itemView?.findViewById<TextView>(R.id.tv_card_deposit_schedule)
    }

}
