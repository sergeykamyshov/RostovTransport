package ru.sergeykamyshov.rostovtransport.ui.card.buy

import android.support.v4.app.FragmentActivity
import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import ru.sergeykamyshov.rostovtransport.R
import ru.sergeykamyshov.rostovtransport.data.network.model.card.CardBuy

class CardBuyAdapter(var mContext: FragmentActivity?,
                     var mData: List<CardBuy.Address>) : RecyclerView.Adapter<CardBuyAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = mContext?.layoutInflater?.inflate(R.layout.recycler_item_card_buy, parent, false)
        return ViewHolder(view!!)
    }

    override fun getItemCount() = mData.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = mData.get(position)
        holder.desc?.text = item.desc
        if (item.note.isEmpty()) holder.note?.visibility = View.GONE else holder.note?.text = item.note
    }

    fun updateData(data: List<CardBuy.Address>) {
        mData = data
        notifyDataSetChanged()
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var desc = itemView?.findViewById<TextView>(R.id.tv_card_buy_desc)
        var note = itemView?.findViewById<TextView>(R.id.tv_card_buy_note)
    }

}
