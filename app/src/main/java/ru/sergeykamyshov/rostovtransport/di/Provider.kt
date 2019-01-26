package ru.sergeykamyshov.rostovtransport.di

import android.content.Context
import ru.sergeykamyshov.rostovtransport.data.Network

class Provider(context: Context) {

    private val network = Network()
    val api = ApiProvider(network)
    private val cachePrefs = context.getSharedPreferences("json-cache", Context.MODE_PRIVATE)
    private val dataSource = DataSourceProvider(
            context,
            cachePrefs,
            api
    )
    val useCase = UseCaseProvider(dataSource)

}