package ru.sergeykamyshov.rostovtransport.data.help

import android.content.Context
import android.content.SharedPreferences
import com.google.gson.Gson
import io.reactivex.Completable
import io.reactivex.Single
import io.reactivex.functions.BiFunction
import ru.sergeykamyshov.rostovtransport.BuildConfig
import ru.sergeykamyshov.rostovtransport.base.utils.FileUtils
import ru.sergeykamyshov.rostovtransport.domain.help.Contact
import ru.sergeykamyshov.rostovtransport.domain.help.HelpDataSource
import timber.log.Timber

class HelpRepository(
        private val context: Context,
        private val cachePrefs: SharedPreferences,
        private val helpContactDao: HelpContactDao
) : HelpDataSource {

    private val gson = Gson()

    override fun getDepartments() = getContacts(DEPARTMENTS_PREF, DEPARTMENTS_FILE)

    override fun getStations() = getContacts(STATIONS_PREF, STATIONS_FILE)

    override fun getBusiness() = getContacts(BUSINESS_PREF, BUSINESS_FILE)

    private fun getContacts(hashPref: String, file: String): Single<List<Contact>> {
        Timber.d("Start getDepartments")
        return compareHash(hashPref, file)
                .flatMap { equals ->
                    if (equals) {
                        Timber.d("Condition YES - getFromDb()")
                        getFromDb()
                    } else {
                        Timber.d("Condition NO - updateAndGetFromDb()")
                        updateAndGetFromDb(file)
                    }
                }.map { it.map { item -> item.toContact() } }
    }

    private fun compareHash(hashPref: String, file: String): Single<Boolean> {
        return Single.zip(
                getSavedHash(hashPref),
                getFileHash(file),
                BiFunction<String, String, Boolean> { savedHash, fileHash ->
                    Timber.d("Saved hash=$savedHash, fileHash=$fileHash")
                    savedHash == fileHash
                }
        )
    }

    private fun getSavedHash(hashPref: String): Single<String> {
        return Single.just(cachePrefs.getString(hashPref, ""))
    }

    private fun getFileHash(file: String): Single<String> {
        return Single.just(FileUtils.getMd5Hash(context, file))
    }

    private fun getFromDb(): Single<List<ContactEntity>> {
        Timber.i("Start getFromDb")
        return helpContactDao.getAll()
    }

    private fun updateAndGetFromDb(file: String): Single<List<ContactEntity>> {
        Timber.i("Start updateAndGetFromDb")
        return clearTable()
                .andThen(getContractsFromJson())
                .flatMap { convertToEntities(it) }
                .flatMapCompletable { saveEntitiesToDb(it) }
                .andThen(getFileHash(file))
                .flatMapCompletable { saveHashToPrefs(it) }
                .andThen(getFromDb())
    }

    private fun clearTable(): Completable {
        Timber.d("Clear table")
        return helpContactDao.clear()
    }

    private fun getContractsFromJson(): Single<List<Contact>> {
        return Single.fromCallable {
            val json = FileUtils.getJson(context, "help_departments.json")
            val list = gson.fromJson(json, Array<Contact>::class.java).toList()
            Timber.i("Parsed departments. ${list.size} items")
            list
        }
    }

    private fun convertToEntities(contacts: List<Contact>): Single<List<ContactEntity>> {
        Timber.i("Convert contacts into entities. ${contacts.size} items")
        val entities = contacts.map {
            ContactEntity().apply {
                name = it.name
                desc = it.desc
                phones = it.phones
                address = it.address
                site = it.site
                emails = it.emails
            }
        }
        return Single.just(entities)
    }

    private fun saveEntitiesToDb(entities: List<ContactEntity>): Completable {
        Timber.i("Insert Entity objects to table. Entities size = ${entities.size}")
        return helpContactDao.add(entities)
    }

    private fun saveHashToPrefs(hash: String): Completable {
        return Completable.fromAction {
            Timber.i("Insert file hash $hash to prefs")
            cachePrefs.edit().putString("helpDepartmentsHash", hash).apply()
        }
    }

    companion object {
        const val DEPARTMENTS_PREF = "${BuildConfig.APPLICATION_ID}.DEPARTMENTS_HASH"
        const val STATIONS_PREF = "${BuildConfig.APPLICATION_ID}.STATIONS_HASH"
        const val BUSINESS_PREF = "${BuildConfig.APPLICATION_ID}.BUSINESS_HASH"
        const val DEPARTMENTS_FILE = "help_departments.json"
        const val STATIONS_FILE = "help_departments.json"
        const val BUSINESS_FILE = "help_departments.json"
    }

}