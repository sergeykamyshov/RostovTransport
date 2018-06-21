package ru.sergeykamyshov.rostovtransport

import android.app.Application
import com.google.gson.GsonBuilder
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import ru.sergeykamyshov.rostovtransport.dagger.AppComponent
import ru.sergeykamyshov.rostovtransport.dagger.DaggerAppComponent
import ru.sergeykamyshov.rostovtransport.data.network.NewsRestService
import ru.sergeykamyshov.rostovtransport.data.network.RestService

class App : Application() {

    val TEST_DOMAIN: String = "http://howtoandroid.ru"
    val NEWS_DOMAIN: String = "http://rostov-transport.info"
    val JSON_DATE_FORMAT: String = "dd.MM.yyyy"

    companion object {
        lateinit var retrofit: Retrofit
        lateinit var retrofitNews: Retrofit
        lateinit var daggerComponent: AppComponent

        fun createRestService(): RestService {
            return retrofit.create(RestService::class.java)
        }

        fun createNewsRestService(): NewsRestService {
            return retrofitNews.create(NewsRestService::class.java)
        }
    }

    override fun onCreate() {
        super.onCreate()

        // Retrofit
        val gson = GsonBuilder().setDateFormat(JSON_DATE_FORMAT).create()
        retrofit = Retrofit.Builder()
                .baseUrl(TEST_DOMAIN)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build()
        retrofitNews = Retrofit.Builder()
                .baseUrl(NEWS_DOMAIN)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build()

        // Dagger
        daggerComponent = DaggerAppComponent.create()
    }

}