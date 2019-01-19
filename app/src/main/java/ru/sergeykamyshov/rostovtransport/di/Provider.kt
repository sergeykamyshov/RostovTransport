package ru.sergeykamyshov.rostovtransport.di

import android.content.Context
import ru.sergeykamyshov.rostovtransport.data.Network

class Provider(context: Context) {

    val network = Network()
    val apiProvider = ApiProvider(context, network)

}