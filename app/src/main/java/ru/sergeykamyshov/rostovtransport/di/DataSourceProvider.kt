package ru.sergeykamyshov.rostovtransport.di

import android.content.Context
import android.content.SharedPreferences
import ru.sergeykamyshov.rostovtransport.data.AppDatabase
import ru.sergeykamyshov.rostovtransport.data.card.CardRepository
import ru.sergeykamyshov.rostovtransport.data.help.HelpRepository
import ru.sergeykamyshov.rostovtransport.data.news.NewsRepository

class DataSourceProvider(
        context: Context,
        cachePrefs: SharedPreferences,
        db: AppDatabase,
        api: ApiProvider
) {

    val news = NewsRepository(api.newsApi)

    val help = HelpRepository(context, cachePrefs, db.helpContactDao())

    val card = CardRepository(
            context,
            cachePrefs,
            db.buyAddressDao(),
            db.depositAddressDao()
    )

}