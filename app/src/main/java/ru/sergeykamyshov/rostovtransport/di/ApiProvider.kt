package ru.sergeykamyshov.rostovtransport.di

import ru.sergeykamyshov.rostovtransport.data.Network
import ru.sergeykamyshov.rostovtransport.data.json.JsonDataApi
import ru.sergeykamyshov.rostovtransport.data.news.NewsApi
import ru.sergeykamyshov.rostovtransport.data.transport.TransportApi

class ApiProvider(network: Network) {

    val newsApi: NewsApi =
            network.newsRetrofit.create(NewsApi::class.java)
    val jsonDataApi: JsonDataApi =
            network.jsonDataRetrofit.create(JsonDataApi::class.java)
    val transportApi: TransportApi =
            network.transportRetrofit.create(TransportApi::class.java)

}
