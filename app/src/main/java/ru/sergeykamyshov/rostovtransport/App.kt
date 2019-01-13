package ru.sergeykamyshov.rostovtransport

import android.app.Application
import com.crashlytics.android.Crashlytics
import com.google.gson.GsonBuilder
import io.fabric.sdk.android.Fabric
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import ru.sergeykamyshov.rostovtransport.dagger.AppComponent
import ru.sergeykamyshov.rostovtransport.dagger.DaggerAppComponent
import ru.sergeykamyshov.rostovtransport.data.network.NewsRestService
import ru.sergeykamyshov.rostovtransport.data.network.OnlineRestService
import ru.sergeykamyshov.rostovtransport.data.network.RestService
import timber.log.Timber

class App : Application() {

    val BITBUCKET_BASE_URL: String = "https://bitbucket.org/sergeykamyshov/rostov-transport-data/raw/master/api/1.0/"
    val NEWS_BASE_URL: String = "http://rostov-transport.info"
    val ONLINE_BASE_URL: String = "http://bus.perseus.su"
    val JSON_DATE_FORMAT: String = "dd.MM.yyyy"

    companion object {
        lateinit var retrofit: Retrofit
        lateinit var retrofitNews: Retrofit
        lateinit var retrofitOnline: Retrofit
        lateinit var daggerComponent: AppComponent

        fun createRestService(): RestService {
            return retrofit.create(RestService::class.java)
        }

        fun createNewsRestService(): NewsRestService {
            return retrofitNews.create(NewsRestService::class.java)
        }

        fun createOnlineRestService(): OnlineRestService {
            return retrofitOnline.create(OnlineRestService::class.java)
        }
    }

    override fun onCreate() {
        super.onCreate()

        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }

        // Retrofit
        val gson = GsonBuilder().setDateFormat(JSON_DATE_FORMAT).create()
        retrofit = Retrofit.Builder()
                .baseUrl(BITBUCKET_BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build()
        retrofitNews = Retrofit.Builder()
                .baseUrl(NEWS_BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build()
        retrofitOnline = Retrofit.Builder()
                .baseUrl(ONLINE_BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build()

        // Dagger
        daggerComponent = DaggerAppComponent.create()

        // Crashlytics
        Fabric.with(this, Crashlytics())
    }

}