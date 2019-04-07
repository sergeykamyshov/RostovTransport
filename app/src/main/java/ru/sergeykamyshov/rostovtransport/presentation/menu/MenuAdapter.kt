package ru.sergeykamyshov.rostovtransport.presentation.menu

import android.content.Context
import com.hannesdorfmann.adapterdelegates4.ListDelegationAdapter

class MenuAdapter(
        context: Context,
        callback: Callback
) : ListDelegationAdapter<List<MenuOption>>() {

    interface Callback {
        fun onOptionClick(type: OptionType)
    }

    init {
        delegatesManager.addDelegate(
                SimpleOptionDelegate(
                        context,
                        callback,
                        this
                )
        )
    }

}