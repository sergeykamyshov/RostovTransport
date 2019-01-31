package ru.sergeykamyshov.rostovtransport.presentation.about

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import kotlinx.android.synthetic.main.about_card_layout.*
import kotlinx.android.synthetic.main.about_contact_item.view.*
import kotlinx.android.synthetic.main.about_contacts_card_layout.*
import kotlinx.android.synthetic.main.about_top_card_layout.view.*
import kotlinx.android.synthetic.main.fragment_about.*
import ru.sergeykamyshov.rostovtransport.R
import ru.sergeykamyshov.rostovtransport.base.extentions.hide
import ru.sergeykamyshov.rostovtransport.base.extentions.makeCall
import ru.sergeykamyshov.rostovtransport.base.extentions.onClickDebounce
import ru.sergeykamyshov.rostovtransport.base.extentions.sendEmail
import ru.sergeykamyshov.rostovtransport.presentation.base.BaseFragment
import ru.sergeykamyshov.rostovtransport.presentation.main.MainActivity

class AboutFragment : BaseFragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_about, container, false)

        setActionBarTitle(R.string.title_about)

        val viewModel = ViewModelProviders.of(activity as MainActivity).get(AboutViewModel::class.java)
        val liveData = viewModel.getData()
        liveData.observe(this, Observer {
            // Добавляем карточки
            val cards = it?.cards
            for (i in cards?.indices!!) {
                // Первая карточка имеет разметку с картинкой
                if (i == 0) {
                    val topCard = inflater.inflate(R.layout.about_top_card_layout, about_main_layout, true)
                    val card = cards[0]
                    topCard.tv_about_top_card_title.text = card.title
                    topCard.tv_about_top_card_content.text = card.content
                    continue
                }
                inflater.inflate(R.layout.about_card_layout, about_main_layout, true)
                val card = cards.get(i)
                tv_about_card_title.text = card.title
                tv_about_card_content.text = card.content
            }

            // Добавляем карточку со списком контактов
            inflater.inflate(R.layout.about_contacts_card_layout, about_main_layout, true)
            for (contact in it.contacts) {
                val contactLayout = inflater.inflate(R.layout.about_contact_item, about_main_layout, false)
                if (contact.position.isEmpty()) {
                    contactLayout.tv_contact_position.hide()
                    contactLayout.img_contact_position.hide()
                } else contactLayout.tv_contact_position.text = contact.position
                if (contact.name.isEmpty()) {
                    contactLayout.tv_contact_name.hide()
                    contactLayout.img_contact_name.hide()
                } else contactLayout.tv_contact_name.text = contact.name
                if (contact.phone.isEmpty()) {
                    contactLayout.tv_contact_phone.hide()
                    contactLayout.img_contact_phone.hide()
                } else {
                    contactLayout.tv_contact_phone.text = contact.phone
                    contactLayout.tv_contact_phone.onClickDebounce {
                        activity?.makeCall(contact.phone)
                    }
                }
                if (contact.email.isEmpty()) {
                    contactLayout.tv_contact_email.hide()
                    contactLayout.img_contact_email.hide()
                } else {
                    contactLayout.tv_contact_email.text = contact.email
                    contactLayout.tv_contact_email.onClickDebounce {
                        activity?.sendEmail(contact.email)
                    }
                }

                ll_about_contacts_layout.addView(contactLayout)
            }
            about_progress.hide()
        })
        return view
    }

    companion object {
        const val TAG = "AboutFragment"

        fun newInstance() = AboutFragment()
    }

}