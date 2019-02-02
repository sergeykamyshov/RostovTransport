package ru.sergeykamyshov.rostovtransport.data.card

import android.content.Context
import android.content.SharedPreferences
import com.google.gson.Gson
import io.reactivex.Completable
import io.reactivex.Single
import io.reactivex.functions.BiFunction
import ru.sergeykamyshov.rostovtransport.BuildConfig
import ru.sergeykamyshov.rostovtransport.base.utils.FileUtils
import ru.sergeykamyshov.rostovtransport.data.help.DepositAddressDao
import ru.sergeykamyshov.rostovtransport.domain.card.CardDataSource
import ru.sergeykamyshov.rostovtransport.domain.card.DepositAddress
import timber.log.Timber

class CardRepository(
        private val context: Context,
        private val cachePrefs: SharedPreferences,
        private val depositAddressDao: DepositAddressDao
) : CardDataSource {

    private val gson = Gson()

    override fun getDepositAddresses(): Single<List<DepositAddress>> {
        Timber.d("Start")
        return getDepositAddresses(DEPOSIT_ADDRESS_PREF, DEPOSIT_ADDRESS_FILE)
    }

    private fun getDepositAddresses(hashPref: String, file: String): Single<List<DepositAddress>> {
        return compareHash(hashPref, file)
                .flatMap { equals ->
                    if (equals) {
                        getFromDb()
                    } else {
                        updateAndGetFromDb(hashPref, file)
                    }
                }
                .map { it.map { item -> item.toDepositAddress() } }
    }

    private fun compareHash(hashPref: String, file: String): Single<Boolean> {
        return Single.zip(
                getSavedHash(hashPref),
                getFileHash(file),
                BiFunction<String, String, Boolean> { savedHash, fileHash ->
                    Timber.d("Hashes equals = ${savedHash == fileHash}")
                    savedHash == fileHash
                }
        )
    }

    private fun getSavedHash(hashPref: String): Single<String> {
        val hash = cachePrefs.getString(hashPref, "")
        Timber.d("Saved hash = $hash")
        return Single.just(hash)
    }

    private fun getFileHash(file: String): Single<String> {
        val md5Hash = FileUtils.getMd5Hash(context, file)
        Timber.d("File hash = $md5Hash")
        return Single.just(md5Hash)
    }

    private fun getFromDb(): Single<List<DepositAddressEntity>> {
        return depositAddressDao.getAll()
    }

    private fun updateAndGetFromDb(hashPref: String, file: String): Single<List<DepositAddressEntity>> {
        return clearTable()
                .andThen(getFromJson(file))
                .flatMap { addresses -> convertToEntities(addresses) }
                .flatMapCompletable { entities -> saveEntitiesToDb(entities) }
                .andThen(getFileHash(file))
                .flatMapCompletable { hash -> saveHashToPrefs(hashPref, hash) }
                .andThen(getFromDb())
    }

    private fun clearTable(): Completable {
        Timber.d("Clear table")
        return depositAddressDao.clear()
    }

    private fun getFromJson(file: String): Single<List<DepositAddress>> {
        Timber.d("Get from json")
        return Single.fromCallable {
            val json = FileUtils.getJson(context, file)
            gson.fromJson(json, Array<DepositAddress>::class.java).toList()
        }
    }

    private fun convertToEntities(addresses: List<DepositAddress>): Single<List<DepositAddressEntity>> {
        Timber.d("Convert entities. Size ${addresses.size}")
        val entities = addresses.map {
            DepositAddressEntity().apply {
                type = it.type
                desc = it.desc
                address = it.address
                schedule = it.schedule
                location = it.location
            }
        }
        return Single.just(entities)
    }

    private fun saveEntitiesToDb(entities: List<DepositAddressEntity>): Completable {
        Timber.d("Save to db. Size ${entities.size}")
        return depositAddressDao.add(entities)
    }

    private fun saveHashToPrefs(hashPref: String, hash: String): Completable {
        Timber.d("Save hash $hash")
        return Completable.fromAction {
            cachePrefs.edit().putString(hashPref, hash).apply()
        }
    }

    companion object {
        const val DEPOSIT_ADDRESS_PREF = "${BuildConfig.APPLICATION_ID}.DEPOSIT_ADDRESS_HASH"
        const val DEPOSIT_ADDRESS_FILE = "card_deposit.json"
    }

}