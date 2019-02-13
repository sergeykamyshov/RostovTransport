package ru.sergeykamyshov.rostovtransport.base.utils

import android.os.Bundle
import com.crashlytics.android.answers.Answers
import com.crashlytics.android.answers.CustomEvent
import ru.sergeykamyshov.rostovtransport.App
import ru.sergeykamyshov.rostovtransport.BuildConfig

object AnalyticsUtils {

    private const val CONTENT_VIEW_EVENT = "content_view"
    private const val CONTENT_VIEW_TYPE = "content_type"

    fun logContentViewEvent(contentType: String, params: Map<String, String> = emptyMap()) {
        if (BuildConfig.DEBUG) {
            return
        }

        val bundle = Bundle()
        bundle.putString(CONTENT_VIEW_TYPE, contentType)
        val contentViewEvent = CustomEvent(CONTENT_VIEW_EVENT)
        contentViewEvent.putCustomAttribute(CONTENT_VIEW_TYPE, contentType)

        for ((key, value) in params) {
            contentViewEvent.putCustomAttribute(key, value)
            bundle.putString(key, value)
        }

        App.firebaseAnalytics.logEvent(CONTENT_VIEW_EVENT, bundle)
        Answers.getInstance().logCustom(contentViewEvent)
    }

}