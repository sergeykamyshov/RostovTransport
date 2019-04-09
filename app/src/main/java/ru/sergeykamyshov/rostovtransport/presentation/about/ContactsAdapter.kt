package ru.sergeykamyshov.rostovtransport.presentation.about

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.item_about_contact.view.*
import ru.sergeykamyshov.rostovtransport.R
import ru.sergeykamyshov.rostovtransport.base.extentions.*
import ru.sergeykamyshov.rostovtransport.domain.about.Contact

class ContactsAdapter(
        private val context: Context,
        private val items: MutableList<Contact> = mutableListOf()
) : RecyclerView.Adapter<ContactsAdapter.ViewHolder>() {

    init {
        items.add(Contact(
                "Редакция",
                "",
                "mail@rostov-transport.info",
                "+7 (989) 634-04-88"
        ))
        items.add(Contact(
                "Руководитель проекта и главный редактор",
                "Игорь Васильевич Негодаев",
                "head@rostov-transport.info",
                "+7 (989) 634-04-88"
        ))
        items.add(Contact(
                "Зам. руководителя проекта, обозреватель и редактор",
                "Максим Александрович Рассказов",
                "rasskazov@rostov-transport.info",
                ""
        ))
        items.add(Contact(
                "Зам. руководителя проекта, обозреватель и редактор",
                "Диана Александровна Орлова",
                "orlova@rostov-transport.info",
                ""
        ))

    }

    private val inflater: LayoutInflater = LayoutInflater.from(context)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(inflater.inflate(R.layout.item_about_contact, parent, false))
    }

    override fun getItemCount() = items.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val contact = items[position]

        with(holder) {
            tvPosition.text = contact.position
            if (contact.name.isBlank()) {
                ivName.hide()
                tvName.hide()
            } else {
                tvName.text = contact.name
                ivName.show()
                tvName.show()
            }
            if (contact.phone.isBlank()) {
                ivPhone.hide()
                tvPhone.hide()
            } else {
                tvPhone.text = contact.phone
                tvPhone.onClickDebounce {
                    context.makeCall(contact.phone)
                }
                ivPhone.show()
                tvPhone.show()
            }
            if (contact.email.isNotBlank()) {
                tvEmail.text = contact.email
                tvEmail.onClickDebounce {
                    context.sendEmail(contact.email)
                }
            }
        }
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvPosition: TextView = itemView.tv_position
        val ivName: ImageView = itemView.iv_name
        val tvName: TextView = itemView.tv_name
        val ivPhone: ImageView = itemView.iv_phone
        val tvPhone: TextView = itemView.tv_phone
        val tvEmail: TextView = itemView.tv_email
    }

}