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

class HelpRepository(
        private val context: Context,
        private val cachePrefs: SharedPreferences,
        private val helpContactDao: HelpContactDao
) : HelpDataSource {

    private val gson = Gson()

    override fun getDepartments() = getContacts(
            DEPARTMENTS_TYPE,
            DEPARTMENTS_PREF,
            DEPARTMENTS_FILE
    )

    override fun getStations() = getContacts(
            STATIONS_TYPE,
            STATIONS_PREF,
            STATIONS_FILE
    )

    override fun getBusiness() = getContacts(
            BUSINESS_TYPE,
            BUSINESS_PREF,
            BUSINESS_FILE
    )

    private fun getContacts(type: String, hashPref: String, file: String): Single<List<Contact>> {
        return compareHash(hashPref, file)
                .flatMap { equals ->
                    if (equals) {
                        getFromDb(type)
                    } else {
                        updateAndGetFromDb(type, hashPref, file)
                    }
                }.map { it.map { item -> item.toContact() } }
    }

    private fun compareHash(hashPref: String, file: String): Single<Boolean> {
        return Single.zip(
                getSavedHash(hashPref),
                getFileHash(file),
                BiFunction<String, String, Boolean> { savedHash, fileHash ->
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

    private fun getFromDb(type: String): Single<List<ContactEntity>> {
        return helpContactDao.getByType(type)
    }

    private fun updateAndGetFromDb(type: String, hashPref: String, file: String): Single<List<ContactEntity>> {
        return removeFromTable(type)
                .andThen(getContractsFromJson(file))
                .map { contacts -> convertToEntities(contacts, type) }
                .flatMapCompletable { entities -> saveEntitiesToDb(entities) }
                .andThen(getFileHash(file))
                .flatMapCompletable { hash -> saveHashToPrefs(hashPref, hash) }
                .andThen(getFromDb(type))
    }

    private fun removeFromTable(type: String): Completable {
        return helpContactDao.removeByType(type)
    }

    private fun getContractsFromJson(file: String): Single<List<Contact>> {
        return Single.fromCallable {
            val json = FileUtils.getJson(context, file)
            gson.fromJson(json, Array<Contact>::class.java).toList()
        }
    }

    private fun convertToEntities(contacts: List<Contact>, type: String): List<ContactEntity> {
        return contacts.map {
            ContactEntity().apply {
                this.type = type
                name = it.name
                desc = it.desc
                phones = it.phones
                address = it.address
                site = it.site
                emails = it.emails
            }
        }
    }

    private fun saveEntitiesToDb(entities: List<ContactEntity>): Completable {
        return helpContactDao.add(entities)
    }

    private fun saveHashToPrefs(hashPref: String, hash: String): Completable {
        return Completable.fromAction {
            cachePrefs.edit().putString(hashPref, hash).apply()
        }
    }

    companion object {
        const val DEPARTMENTS_TYPE = "Departments"
        const val STATIONS_TYPE = "Stations"
        const val BUSINESS_TYPE = "Business"

        const val DEPARTMENTS_PREF = "${BuildConfig.APPLICATION_ID}.DEPARTMENTS_HASH"
        const val STATIONS_PREF = "${BuildConfig.APPLICATION_ID}.STATIONS_HASH"
        const val BUSINESS_PREF = "${BuildConfig.APPLICATION_ID}.BUSINESS_HASH"

        const val DEPARTMENTS_FILE = "help_departments.json"
        const val STATIONS_FILE = "help_stations.json"
        const val BUSINESS_FILE = "help_business.json"
    }

}