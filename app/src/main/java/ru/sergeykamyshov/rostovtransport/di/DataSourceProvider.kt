package ru.sergeykamyshov.rostovtransport.di

import android.content.Context
import ru.sergeykamyshov.rostovtransport.data.help.HelpRepository
import ru.sergeykamyshov.rostovtransport.data.news.NewsRepository

class DataSourceProvider(
        context: Context,
        api: ApiProvider
) {

    val news = NewsRepository(api.newsApi)

    val help = HelpRepository(context)

}