package ru.sergeykamyshov.rostovtransport.data

import com.google.gson.GsonBuilder
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class Network {

    var newsRetrofit: Retrofit
    var jsonDataRetrofit: Retrofit
    var transportRetrofit: Retrofit

    init {
        val gson = GsonBuilder().setDateFormat(DATE_FORMAT).create()
        newsRetrofit = Retrofit.Builder()
                .baseUrl(NEWS_BASE_URL)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build()
        jsonDataRetrofit = Retrofit.Builder()
                .baseUrl(BITBUCKET_BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build()
        transportRetrofit = Retrofit.Builder()
                .baseUrl(TRANSPORT_BASE_URL)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build()
    }

    companion object {
        const val BITBUCKET_BASE_URL: String = "https://bitbucket.org/sergeykamyshov/rostov-transport-data/raw/master/api/1.0/"
        const val NEWS_BASE_URL: String = "http://rostov-transport.info"
        const val TRANSPORT_BASE_URL: String = "http://bus.perseus.su"
        const val DATE_FORMAT: String = "dd.MM.yyyy"
    }

}