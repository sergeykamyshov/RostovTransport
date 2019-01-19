package ru.sergeykamyshov.rostovtransport.data

import com.google.gson.GsonBuilder
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class Network {

    val BITBUCKET_BASE_URL: String = "https://bitbucket.org/sergeykamyshov/rostov-transport-data/raw/master/api/1.0/"
    val NEWS_BASE_URL: String = "http://rostov-transport.info"
    val ONLINE_BASE_URL: String = "http://bus.perseus.su"
    val DATE_FORMAT: String = "dd.MM.yyyy"

    var newsRetrofit: Retrofit
    var jsonDataRetrofit: Retrofit
    var transportOnlineRetrofit: Retrofit

    init {
        val gson = GsonBuilder().setDateFormat(DATE_FORMAT).create()
        newsRetrofit = Retrofit.Builder()
                .baseUrl(NEWS_BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build()
        jsonDataRetrofit = Retrofit.Builder()
                .baseUrl(BITBUCKET_BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build()
        transportOnlineRetrofit = Retrofit.Builder()
                .baseUrl(ONLINE_BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build()
    }

}