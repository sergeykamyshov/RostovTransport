package ru.sergeykamyshov.rostovtransport.ui.about

import android.support.v4.app.FragmentActivity
import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import ru.sergeykamyshov.rostovtransport.R
import ru.sergeykamyshov.rostovtransport.data.network.model.about.About

class ContactsAdapter(var mContext: FragmentActivity?,
                      var mData: List<About.Contact>) : RecyclerView.Adapter<ContactsAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = mContext?.layoutInflater?.inflate(R.layout.recycler_item_about_contact, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return mData.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val contact = mData.get(position)

        if (contact.position.isEmpty()) holder.position?.visibility = View.GONE else holder.position?.text = contact.position
        if (contact.name.isEmpty()) holder.name?.visibility = View.GONE else holder.name?.text = contact.name
        if (contact.email.isEmpty()) holder.email?.visibility = View.GONE else holder.email?.text = contact.email
        if (contact.phone.isEmpty()) holder.phone?.visibility = View.GONE else holder.phone?.text = contact.phone
    }

    fun updateData(data: List<About.Contact>) {
        mData = data
        notifyDataSetChanged()
    }

    class ViewHolder(itemView: View?) : RecyclerView.ViewHolder(itemView) {
        val position = itemView?.findViewById<TextView>(R.id.tv_contact_position)
        val name: TextView? = itemView?.findViewById(R.id.tv_contact_name)
        val email: TextView? = itemView?.findViewById(R.id.tv_contact_email)
        val phone: TextView? = itemView?.findViewById(R.id.tv_contact_phone)
    }

}