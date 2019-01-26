package ru.sergeykamyshov.rostovtransport.di

import android.content.Context
import ru.sergeykamyshov.rostovtransport.data.AppDatabase
import ru.sergeykamyshov.rostovtransport.data.Network

class Provider(context: Context) {

    private val db: AppDatabase by lazy { AppDatabase.getInstance(context) }
    // TODO: make all lazy
    private val network = Network()
    val api = ApiProvider(network)
    private val cachePrefs = context.getSharedPreferences("json-cache", Context.MODE_PRIVATE)
    private val dataSource = DataSourceProvider(
            context,
            cachePrefs,
            db,
            api
    )
    val useCase = UseCaseProvider(dataSource)

}