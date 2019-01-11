package ru.sergeykamyshov.rostovtransport.ui.help.base

import android.content.Intent
import android.net.Uri
import android.support.v4.app.FragmentActivity
import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import ru.sergeykamyshov.rostovtransport.R
import ru.sergeykamyshov.rostovtransport.data.network.model.help.Help

class BaseAdapter(var mContext: FragmentActivity?,
                  var mData: List<Help.Contact>) : RecyclerView.Adapter<BaseAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = mContext?.layoutInflater?.inflate(R.layout.recycler_item_help, parent, false)
        return ViewHolder(view!!)
    }

    override fun getItemCount(): Int {
        return mData.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val contact = mData.get(position)

        if (contact.name.isEmpty()) holder.name?.visibility = View.GONE else holder.name?.text = contact.name
        if (contact.desc.isEmpty()) holder.desc?.visibility = View.GONE else holder.desc?.text = contact.desc

        // Подготавливаем адреса
        if (contact.address.isEmpty()) {
            holder.address?.visibility = View.GONE
            holder.imgAddress?.visibility = View.GONE
        } else {
            holder.address?.text = contact.address
            holder.address?.setOnClickListener {
                val locationUri = Uri.parse("geo:?q=${contact.address}")
                val intent = Intent(Intent.ACTION_VIEW, locationUri)
                mContext?.startActivity(intent)
            }
        }

        // Подготавливаем телефоны
        val phones = contact.phones
        if (phones.isEmpty()) {
            holder.phonesLayout?.visibility = View.GONE
        } else {
            holder.phonesLayout?.removeAllViews()

            for (i in 0 until phones.size) {
                val phoneLayout = mContext?.layoutInflater?.inflate(R.layout.help_contact_item, holder.phonesLayout, false)
                val phoneNumber = phoneLayout?.findViewById<TextView>(R.id.tv_help_contact_phone)
                phoneNumber?.text = phones[i]

                phoneNumber?.setOnClickListener {
                    val intent = Intent(Intent.ACTION_DIAL)
                    val number = phones[i].trim()
                            .replace("(", "")
                            .replace(")", "")
                            .replace(" ", "")
                    intent.data = Uri.parse("tel:$number")
                    mContext?.startActivity(intent)
                }

                holder.phonesLayout?.addView(phoneLayout)
            }
        }
    }

    fun updateData(data: List<Help.Contact>) {
        mData = data
        notifyDataSetChanged()
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val container = itemView?.findViewById<ViewGroup>(R.id.container_item_help)
        val name = itemView?.findViewById<TextView>(R.id.tv_help_contact_name)
        val desc = itemView?.findViewById<TextView>(R.id.tv_help_contact_desc)
        val phonesLayout = itemView?.findViewById<LinearLayout>(R.id.ll_help_contact_phones)
        val imgAddress = itemView?.findViewById<ImageView>(R.id.img_help_contact_address)
        val address = itemView?.findViewById<TextView>(R.id.tv_help_contact_address)
    }
}