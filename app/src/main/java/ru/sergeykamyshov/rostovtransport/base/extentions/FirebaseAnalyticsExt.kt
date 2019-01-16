package ru.sergeykamyshov.rostovtransport.base.extentions

import android.os.Bundle
import com.google.firebase.analytics.FirebaseAnalytics

fun FirebaseAnalytics.sendEvent(event: String, params: Map<String, String> = emptyMap()) {
    val bundle = Bundle()
    for ((key, value) in params) {
        bundle.putString(key, value)
    }
    logEvent(event, bundle)
}