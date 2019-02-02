package ru.sergeykamyshov.rostovtransport.presentation.card.buy

import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.FragmentActivity
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.recycler_item_card_buy.view.*
import ru.sergeykamyshov.rostovtransport.R
import ru.sergeykamyshov.rostovtransport.base.extentions.hide
import ru.sergeykamyshov.rostovtransport.base.extentions.show
import ru.sergeykamyshov.rostovtransport.domain.card.BuyAddress

class CardBuyAdapter(
        private val context: FragmentActivity
) : RecyclerView.Adapter<CardBuyAdapter.ViewHolder>() {

    private val layoutInflater = context.layoutInflater
    private var items: List<BuyAddress> = emptyList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(layoutInflater.inflate(R.layout.recycler_item_card_buy, parent, false))
    }

    override fun getItemCount() = items.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = items[position]
        with(holder) {
            desc.text = item.desc
            if (item.note.isEmpty()) {
                note.hide()
            } else {
                note.text = item.note
                note.show()
            }
        }
    }

    fun update(items: List<BuyAddress>) {
        this.items = items
        notifyDataSetChanged()
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val desc: TextView = itemView.tv_card_buy_desc
        val note: TextView = itemView.tv_card_buy_note
    }

}
