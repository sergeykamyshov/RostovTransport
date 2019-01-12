package ru.sergeykamyshov.rostovtransport.presentation.help.base

import android.support.v4.app.FragmentActivity
import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import kotlinx.android.synthetic.main.recycler_item_help.view.*
import ru.sergeykamyshov.rostovtransport.R
import ru.sergeykamyshov.rostovtransport.base.extentions.makeCall
import ru.sergeykamyshov.rostovtransport.base.extentions.openOnMap
import ru.sergeykamyshov.rostovtransport.data.network.model.help.Help

class BaseAdapter(var mContext: FragmentActivity?,
                  var mData: List<Help.Contact>) : RecyclerView.Adapter<BaseAdapter.ViewHolder>() {

    private val layoutInflater = mContext?.layoutInflater

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = layoutInflater?.inflate(R.layout.recycler_item_help, parent, false)
        return ViewHolder(view!!)
    }

    override fun getItemCount(): Int {
        return mData.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val contact = mData[position]

        if (contact.name.isEmpty()) holder.name.visibility = View.GONE else holder.name.text = contact.name
        if (contact.desc.isEmpty()) holder.desc.visibility = View.GONE else holder.desc.text = contact.desc

        // Подготавливаем адреса
        if (contact.address.isEmpty()) {
            holder.address.visibility = View.GONE
            holder.imgAddress.visibility = View.GONE
        } else {
            holder.address.text = contact.address
            holder.address.setOnClickListener {
                mContext?.openOnMap(contact.address)
            }
        }

        // Подготавливаем телефоны
        val phones = contact.phones
        if (phones.isEmpty()) {
            holder.phonesLayout.visibility = View.GONE
        } else {
            holder.phonesLayout.removeAllViews()

            for (i in 0 until phones.size) {
                val phoneLayout = layoutInflater?.inflate(R.layout.help_contact_item, holder.phonesLayout, false)
                val phoneNumber = phoneLayout?.findViewById<TextView>(R.id.tv_help_contact_phone)
                phoneNumber?.text = phones[i]

                phoneNumber?.setOnClickListener {
                    mContext?.makeCall(phones[i])
                }

                holder.phonesLayout.addView(phoneLayout)
            }
        }
    }

    fun updateData(data: List<Help.Contact>) {
        mData = data
        notifyDataSetChanged()
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val name: TextView = itemView.tv_help_contact_name
        val desc: TextView = itemView.tv_help_contact_desc
        val phonesLayout: LinearLayout = itemView.ll_help_contact_phones
        val imgAddress: ImageView = itemView.img_help_contact_address
        val address: TextView = itemView.tv_help_contact_address
    }
}