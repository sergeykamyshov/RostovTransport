package ru.sergeykamyshov.rostovtransport.presentation.transport.list

import android.content.Context
import com.hannesdorfmann.adapterdelegates4.ListDelegationAdapter

class RoutesListAdapter(
        context: Context,
        callback: Callback
) : ListDelegationAdapter<List<Any>>() {

    interface Callback {
        fun onRouteClick(route: Route)
        fun onFavoriteClick(route: Route)
        fun onNotFavoriteClick(route: Route)
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