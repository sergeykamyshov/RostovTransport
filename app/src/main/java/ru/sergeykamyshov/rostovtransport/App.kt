package ru.sergeykamyshov.rostovtransport

import androidx.multidex.MultiDexApplication
import com.google.firebase.analytics.FirebaseAnalytics
import ru.sergeykamyshov.rostovtransport.di.Provider
import timber.log.Timber

class App : MultiDexApplication() {

    override fun onCreate() {
        super.onCreate()

        provider = Provider(this)

        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        } else {
            // Firebase Analytics
            firebaseAnalytics = FirebaseAnalytics.getInstance(this)
        }
    }

    companion object {
        lateinit var provider: Provider
        lateinit var firebaseAnalytics: FirebaseAnalytics
    }

}