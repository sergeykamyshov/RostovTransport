package ru.sergeykamyshov.rostovtransport

import androidx.multidex.MultiDexApplication
import com.crashlytics.android.Crashlytics
import com.google.firebase.analytics.FirebaseAnalytics
import io.fabric.sdk.android.Fabric
import ru.sergeykamyshov.rostovtransport.di.Provider
import timber.log.Timber

class App : MultiDexApplication() {

    override fun onCreate() {
        super.onCreate()

        provider = Provider(this)

        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }

        // Firebase Analytics
        firebaseAnalytics = FirebaseAnalytics.getInstance(this)

        // Fabric Crashlytics
        Fabric.with(this, Crashlytics())
    }

    companion object {
        lateinit var provider: Provider
        lateinit var firebaseAnalytics: FirebaseAnalytics
    }

}