package ru.sergeykamyshov.rostovtransport.di

import ru.sergeykamyshov.rostovtransport.data.news.NewsRepository

class DataSourceProvider(api: ApiProvider) {

    val news = NewsRepository(api.newsApi)

}