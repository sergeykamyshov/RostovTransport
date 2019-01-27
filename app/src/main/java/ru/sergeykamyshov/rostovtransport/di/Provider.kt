package ru.sergeykamyshov.rostovtransport.di

import android.content.Context
import android.content.SharedPreferences
import ru.sergeykamyshov.rostovtransport.data.AppDatabase
import ru.sergeykamyshov.rostovtransport.data.Network

class Provider(context: Context) {

    private val network: Network by lazy { Network() }
    private val db: AppDatabase by lazy { AppDatabase.getInstance(context) }
    val api: ApiProvider by lazy { ApiProvider(network) }
    private val cachePrefs: SharedPreferences by lazy {
        context.getSharedPreferences("json_cache", Context.MODE_PRIVATE)
    }
    private val dataSource: DataSourceProvider by lazy {
        DataSourceProvider(
                context,
                cachePrefs,
                db,
                api
        )
    }
    val useCase: UseCaseProvider by lazy { UseCaseProvider(dataSource) }

}