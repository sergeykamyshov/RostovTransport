package ru.sergeykamyshov.rostovtransport.presentation.about

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.recycler_item_about_contact.view.*
import ru.sergeykamyshov.rostovtransport.R

class ContactsAdapter(
        private val context: Context,
        private val items: List<String>
) : RecyclerView.Adapter<ContactsAdapter.ViewHolder>() {

    private val inflater: LayoutInflater = LayoutInflater.from(context)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(inflater.inflate(R.layout.recycler_item_about_contact, parent, false))
    }

    override fun getItemCount() = items.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val contact = items[position]
        holder.tvPosition.text = "Position"
        holder.tvName.text = "Name"
        holder.tvPhone.text = "Phone"
        holder.tvEmail.text = "Email"
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvPosition: TextView = itemView.tv_contact_position
        val tvName: TextView = itemView.tv_contact_name
        val tvPhone: TextView = itemView.tv_contact_phone
        val tvEmail: TextView = itemView.tv_contact_email
    }

}