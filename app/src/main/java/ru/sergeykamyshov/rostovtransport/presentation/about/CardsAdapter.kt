package ru.sergeykamyshov.rostovtransport.presentation.about

import android.content.Context
import com.hannesdorfmann.adapterdelegates4.ListDelegationAdapter

class CardsAdapter(
        private val context: Context
) : ListDelegationAdapter<List<Any>>() {

    init {
        items = listOf(
                CardItem.HeaderCard,
                CardItem.TextCard,
                CardItem.ContactsCard
        )
        delegatesManager
                .addDelegate(HeaderCardDelegate(context))
                .addDelegate(TextCardDelegate(context))
                .addDelegate(ContactsCardDelegate(context))
    }

}