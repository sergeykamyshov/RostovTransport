package ru.sergeykamyshov.rostovtransport.base.utils

import android.os.Bundle
import ru.sergeykamyshov.rostovtransport.App
import ru.sergeykamyshov.rostovtransport.BuildConfig

object AnalyticsUtils {

    private const val CONTENT_VIEW_EVENT = "content_view"
    private const val CONTENT_VIEW_TYPE = "content_type"

    fun logContentViewEvent(contentType: String) {
        if (BuildConfig.DEBUG) {
            return
        }
        val bundle = Bundle()
        bundle.putString(CONTENT_VIEW_TYPE, contentType)
        App.firebaseAnalytics.logEvent(CONTENT_VIEW_EVENT, bundle)
    }

    fun logCustomEvent(event: String, params: Map<String, String> = emptyMap()) {
        if (BuildConfig.DEBUG) {
            return
        }
        val bundle = Bundle()
        for ((key, value) in params) {
            bundle.putString(key, value)
        }
        App.firebaseAnalytics.logEvent(event, bundle)
    }

}