package ru.sergeykamyshov.rostovtransport

import androidx.multidex.MultiDexApplication
import ru.sergeykamyshov.rostovtransport.di.Provider
import timber.log.Timber

class App : MultiDexApplication() {

    override fun onCreate() {
        super.onCreate()

        provider = Provider(this)

        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }
    }

    companion object {
        lateinit var provider: Provider
    }

}