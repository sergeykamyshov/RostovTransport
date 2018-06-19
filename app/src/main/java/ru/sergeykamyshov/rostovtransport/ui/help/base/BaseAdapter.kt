package ru.sergeykamyshov.rostovtransport.ui.help.base

import android.support.v4.app.FragmentActivity
import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import ru.sergeykamyshov.rostovtransport.R
import ru.sergeykamyshov.rostovtransport.data.network.help.Help

class BaseAdapter(var mContext: FragmentActivity?,
                  var mData: List<Help.Contact>) : RecyclerView.Adapter<BaseAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = mContext?.layoutInflater?.inflate(R.layout.recycler_item_help, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return mData.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val contact = mData.get(position)

        if (contact.name.isEmpty()) holder.name?.visibility = View.GONE else holder.name?.text = contact.name
        if (contact.desc.isEmpty()) holder.desc?.visibility = View.GONE else holder.desc?.text = contact.desc
        if (contact.address.isEmpty()) holder.address?.visibility = View.GONE else holder.address?.text = contact.address

        val phones = contact.phones
        if (phones.isEmpty()) {
            holder.phones?.visibility = View.GONE
        } else {
            var formatedPhones = ""
            for (i in 0 until phones.size) {
                formatedPhones += phones[i]
                if (i < phones.size - 1) {
                    formatedPhones += "\n"
                }
            }
            holder.phones?.text = formatedPhones
        }
    }

    fun updateData(data: List<Help.Contact>) {
        mData = data
        notifyDataSetChanged()
    }

    class ViewHolder(itemView: View?) : RecyclerView.ViewHolder(itemView) {
        val container = itemView?.findViewById<ViewGroup>(R.id.container_item_help)
        val name = itemView?.findViewById<TextView>(R.id.tv_help_contact_name)
        val desc = itemView?.findViewById<TextView>(R.id.tv_help_contact_desc)
        val phones = itemView?.findViewById<TextView>(R.id.tv_help_contact_phones)
        val address = itemView?.findViewById<TextView>(R.id.tv_help_contact_address)
    }
}