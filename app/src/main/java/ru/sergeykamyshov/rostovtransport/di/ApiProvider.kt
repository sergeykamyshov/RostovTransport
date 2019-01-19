package ru.sergeykamyshov.rostovtransport.di

import android.content.Context
import ru.sergeykamyshov.rostovtransport.data.Network
import ru.sergeykamyshov.rostovtransport.data.json.JsonDataApi
import ru.sergeykamyshov.rostovtransport.data.news.NewsApi
import ru.sergeykamyshov.rostovtransport.data.online.OnlineTransportApi

class ApiProvider(context: Context, network: Network) {

    val newsApi: NewsApi =
            network.newsRetrofit.create(NewsApi::class.java)
    val jsonDataApi: JsonDataApi =
            network.jsonDataRetrofit.create(JsonDataApi::class.java)
    val onlineTransportApi: OnlineTransportApi =
            network.transportOnlineRetrofit.create(OnlineTransportApi::class.java)

}
