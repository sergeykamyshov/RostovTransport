package ru.sergeykamyshov.rostovtransport.data.help

import android.content.Context
import io.reactivex.Single
import ru.sergeykamyshov.rostovtransport.domain.help.Contact
import ru.sergeykamyshov.rostovtransport.domain.help.HelpDataSource
import timber.log.Timber

class HelpRepository(
        private val context: Context
) : HelpDataSource {

    // context
    // prefs
    // file utils
    // db

    override fun getDepartments(): Single<List<Contact>> {
        Timber.d("Start getDepartments")
        // get prefs hash
        // get file hash

        // equals?

        // yes
        // get db data

        // no
        // parse file
        // save to db
        // set hash to prefs
        // get db data

        Timber.d("Return Single emptyList")
        return Single.just(emptyList())
    }

}