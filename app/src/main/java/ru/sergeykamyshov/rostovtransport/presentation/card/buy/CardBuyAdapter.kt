package ru.sergeykamyshov.rostovtransport.presentation.card.buy

import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.FragmentActivity
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.recycler_item_card_buy.view.*
import ru.sergeykamyshov.rostovtransport.R
import ru.sergeykamyshov.rostovtransport.base.extentions.hide
import ru.sergeykamyshov.rostovtransport.data.models.card.CardBuy

class CardBuyAdapter(
        var context: FragmentActivity?,
        var items: List<CardBuy.Address>
) : RecyclerView.Adapter<CardBuyAdapter.ViewHolder>() {

    private val layoutInflater = context?.layoutInflater

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = layoutInflater?.inflate(R.layout.recycler_item_card_buy, parent, false)
        return ViewHolder(view!!)
    }

    override fun getItemCount() = items.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = items[position]
        holder.desc.text = item.desc
        if (item.note.isEmpty()) holder.note.hide() else holder.note.text = item.note
    }

    fun updateData(data: List<CardBuy.Address>) {
        items = data
        notifyDataSetChanged()
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var desc: TextView = itemView.tv_card_buy_desc
        var note: TextView = itemView.tv_card_buy_note
    }

}
