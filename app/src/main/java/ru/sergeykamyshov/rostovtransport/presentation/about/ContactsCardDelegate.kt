package ru.sergeykamyshov.rostovtransport.presentation.about

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.hannesdorfmann.adapterdelegates4.AdapterDelegate
import kotlinx.android.synthetic.main.about_card_contacts.view.*
import ru.sergeykamyshov.rostovtransport.R

class ContactsCardDelegate(private val context: Context) : AdapterDelegate<List<Any>>() {

    private val inflater: LayoutInflater = LayoutInflater.from(context)

    override fun isForViewType(items: List<Any>, position: Int): Boolean {
        return items[position] is CardItem.ContactsCard
    }

    override fun onCreateViewHolder(parent: ViewGroup): RecyclerView.ViewHolder {
        return ViewHolder(inflater.inflate(R.layout.about_card_contacts, parent, false))
    }

    override fun onBindViewHolder(items: List<Any>, position: Int, holder: RecyclerView.ViewHolder, payloads: List<Any>) {
//        val item = items[position] as CardItem.ContactsCard
        val viewHolder = holder as ViewHolder

        with(viewHolder) {
            rvContacts.layoutManager = LinearLayoutManager(context)
            rvContacts.adapter = ContactsAdapter(context, listOf("One", "Two", "Three", "Four", "Five"))
        }
    }

    internal class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val rvContacts: RecyclerView = itemView.rv_contacts
    }

}