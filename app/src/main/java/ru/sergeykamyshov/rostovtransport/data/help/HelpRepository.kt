package ru.sergeykamyshov.rostovtransport.data.help

import android.content.Context
import android.content.SharedPreferences
import com.google.gson.Gson
import io.reactivex.Single
import ru.sergeykamyshov.rostovtransport.base.utils.FileUtils
import ru.sergeykamyshov.rostovtransport.domain.help.Contact
import ru.sergeykamyshov.rostovtransport.domain.help.HelpDataSource
import timber.log.Timber

class HelpRepository(
        private val context: Context,
        private val cachePrefs: SharedPreferences
) : HelpDataSource {

    private val gson = Gson()

    // context
    // prefs
    // file utils
    // db

    override fun getDepartments(): Single<List<Contact>> {
        Timber.d("Start getDepartments")

        // get prefs hash
        val savedHash = cachePrefs.getString("helpDepartmentsHash", "")
        Timber.d("Saved hash = $savedHash")
        // get file hash
        val fileHash = FileUtils.getMd5Hash(context, "help_departments.json")
        Timber.d("File hash = $fileHash")

        // equals?
//        if (fileHash == savedHash) {
            // yes
            // TODO: get db data
//            return Single.just(emptyList())
//        } else {
            // no
            // parse file
            val json = FileUtils.getJson(context, "help_departments.json")
            val list = gson.fromJson(json, Array<Contact>::class.java).toList()
            Timber.i("Departments size ${list.size}")

            // TODO: clear db table
            // TODO: save to db
            // set hash to prefs
            cachePrefs.edit().putString("helpDepartmentsHash", fileHash).apply()
            // TODO: get db data

            Timber.d("Return Single list size ${list.size}")
            return Single.just(list)
//        }
    }

}