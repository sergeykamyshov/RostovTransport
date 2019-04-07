package ru.sergeykamyshov.rostovtransport.presentation.menu

import android.content.Context
import com.hannesdorfmann.adapterdelegates4.ListDelegationAdapter

class MenuAdapter(
        context: Context
) : ListDelegationAdapter<List<MenuOption>>() {

    init {
        delegatesManager.addDelegate(SimpleOptionDelegate(context))
    }

}