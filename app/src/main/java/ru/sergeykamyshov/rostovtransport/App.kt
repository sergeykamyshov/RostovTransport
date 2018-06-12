package ru.sergeykamyshov.rostovtransport

import android.app.Application
import com.google.gson.GsonBuilder
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import ru.sergeykamyshov.rostovtransport.dagger.AppComponent
import ru.sergeykamyshov.rostovtransport.dagger.DaggerAppComponent

class App : Application() {

    companion object {
        lateinit var retrofit: Retrofit
        lateinit var daggerComponent: AppComponent
    }

    override fun onCreate() {
        super.onCreate()

        // Retrofit
        val gson = GsonBuilder().setDateFormat("dd.MM.yyyy").create()
        retrofit = Retrofit.Builder()
                .baseUrl("http://howtoandroid.ru")
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build()

        // Dagger
        daggerComponent = DaggerAppComponent.create()
    }

}