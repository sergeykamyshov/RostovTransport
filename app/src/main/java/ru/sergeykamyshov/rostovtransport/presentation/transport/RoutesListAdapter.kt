package ru.sergeykamyshov.rostovtransport.presentation.transport

import android.content.Context
import com.hannesdorfmann.adapterdelegates4.ListDelegationAdapter

class RoutesListAdapter(
        context: Context,
        callback: Callback
) : ListDelegationAdapter<List<Any>>() {

    interface Callback {
        fun onRouteClick(route: Int)
    }

    init {
        delegatesManager.addDelegate(
                RouteDelegate(
                        context,
                        callback,
                        this
                )
        )
    }

}