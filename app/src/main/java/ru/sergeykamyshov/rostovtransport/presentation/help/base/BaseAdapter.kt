package ru.sergeykamyshov.rostovtransport.presentation.help.base

import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.fragment.app.FragmentActivity
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.recycler_item_help.view.*
import ru.sergeykamyshov.rostovtransport.R
import ru.sergeykamyshov.rostovtransport.base.extentions.hide
import ru.sergeykamyshov.rostovtransport.base.extentions.makeCall
import ru.sergeykamyshov.rostovtransport.base.extentions.onClickDebounce
import ru.sergeykamyshov.rostovtransport.base.extentions.openOnMap
import ru.sergeykamyshov.rostovtransport.domain.help.Contact

class BaseAdapter(
        var context: FragmentActivity?,
        var items: List<Contact>
) : RecyclerView.Adapter<BaseAdapter.ViewHolder>() {

    private val layoutInflater = context?.layoutInflater

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = layoutInflater?.inflate(R.layout.recycler_item_help, parent, false)
        return ViewHolder(view!!)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val contact = items[position]

        if (contact.name.isEmpty()) holder.name.hide() else holder.name.text = contact.name
        if (contact.desc.isEmpty()) holder.desc.hide() else holder.desc.text = contact.desc

        // Подготавливаем адреса
        if (contact.address.isEmpty()) {
            holder.address.hide()
            holder.imgAddress.hide()
        } else {
            holder.address.text = contact.address
            holder.address.onClickDebounce {
                context?.openOnMap(contact.address)
            }
        }

        // Подготавливаем телефоны
        val phones = contact.phones
        if (phones.isEmpty()) {
            holder.phonesLayout.hide()
        } else {
            holder.phonesLayout.removeAllViews()

            for (i in 0 until phones.size) {
                val phoneLayout = layoutInflater?.inflate(R.layout.help_contact_item, holder.phonesLayout, false)
                val phoneNumber = phoneLayout?.findViewById<TextView>(R.id.tv_help_contact_phone)
                phoneNumber?.text = phones[i]

                phoneNumber?.onClickDebounce {
                    context?.makeCall(phones[i])
                }

                holder.phonesLayout.addView(phoneLayout)
            }
        }
    }

    fun updateData(data: List<Contact>) {
        items = data
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