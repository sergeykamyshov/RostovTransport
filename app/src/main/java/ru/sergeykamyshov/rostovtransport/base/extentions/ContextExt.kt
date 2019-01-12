package ru.sergeykamyshov.rostovtransport.base.extentions

import android.content.Context
import android.content.Intent
import android.net.Uri

fun Context.makeCall(phone: String): Boolean {
    val number = phone.trim()
            .replace("(", "")
            .replace(")", "")
            .replace(" ", "")
    val intent = Intent(Intent.ACTION_DIAL)
    intent.data = Uri.parse("tel:$number")
    return resolveAndStartActivity(intent)
}

fun Context.sendEmail(email: String, subject: String = "", text: String = ""): Boolean {
    val intent = Intent(Intent.ACTION_SENDTO)
    intent.data = Uri.parse("mailto:$email")
    if (subject.isNotEmpty()) {
        intent.putExtra(Intent.EXTRA_SUBJECT, subject)
    }
    if (text.isNotEmpty()) {
        intent.putExtra(Intent.EXTRA_TEXT, text)
    }
    return resolveAndStartActivity(intent)
}

fun Context.openOnMap(address: String): Boolean {
    val intent = Intent(Intent.ACTION_VIEW, Uri.parse("geo:?q=$address"))
    return resolveAndStartActivity(intent)
}

fun Context.openInBrowser(url: String) {
    val uri = Uri.parse(url)
    val intent = Intent(Intent.ACTION_VIEW, uri)
    resolveAndStartActivity(intent)
}

fun Context.resolveAndStartActivity(intent: Intent): Boolean {
    intent.resolveActivity(packageManager)?.let {
        startActivity(intent)
        return true
    }
    return false
}