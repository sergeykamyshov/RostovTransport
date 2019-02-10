package ru.sergeykamyshov.rostovtransport.data.card

import android.content.Context
import android.content.SharedPreferences
import com.google.gson.Gson
import io.reactivex.Completable
import io.reactivex.Single
import io.reactivex.functions.BiFunction
import ru.sergeykamyshov.rostovtransport.BuildConfig
import ru.sergeykamyshov.rostovtransport.base.utils.FileUtils
import ru.sergeykamyshov.rostovtransport.domain.card.BuyAddress
import ru.sergeykamyshov.rostovtransport.domain.card.CardDataSource
import ru.sergeykamyshov.rostovtransport.domain.card.DepositAddress

class CardRepository(
        private val context: Context,
        private val cachePrefs: SharedPreferences,
        private val buyAddressDao: BuyAddressDao,
        private val depositAddressDao: DepositAddressDao
) : CardDataSource {

    private val gson = Gson()

    override fun getBuyAddresses(): Single<List<BuyAddress>> {
        return getBuyAddresses(
                BUY_ADDRESS_PREF,
                BUY_ADDRESS_FILE
        )
    }

    override fun getDepositAddresses(): Single<List<DepositAddress>> {
        return getDepositAddresses(
                DEPOSIT_ADDRESS_PREF,
                DEPOSIT_ADDRESS_FILE
        )
    }

    private fun getBuyAddresses(hashPref: String, file: String): Single<List<BuyAddress>> {
        return compareHash(hashPref, file)
                .flatMap { equals ->
                    if (equals) {
                        buyAddressDao.getAll()
                    } else {
                        buyAddressDao.clear()
                                .andThen(Single.fromCallable {
                                    val json = FileUtils.getJson(context, file)
                                    gson.fromJson(json, Array<BuyAddress>::class.java).toList()
                                })
                                .map { addresses ->
                                    addresses.map { item ->
                                        BuyAddressEntity().apply {
                                            type = item.type
                                            desc = item.desc
                                            note = item.note
                                            locations = item.locations
                                        }
                                    }
                                }
                                .flatMapCompletable { entities ->
                                    buyAddressDao.add(entities)
                                }
                                .andThen(getFileHash(file))
                                .flatMapCompletable { hash -> saveHashToPrefs(hashPref, hash) }
                                .andThen(buyAddressDao.getAll())
                    }
                }
                .map { it.map { item -> item.toBuyAddress() } }
    }

    private fun getDepositAddresses(hashPref: String, file: String): Single<List<DepositAddress>> {
        return compareHash(hashPref, file)
                .flatMap { equals ->
                    if (equals) {
                        depositAddressDao.getAll()
                    } else {
                        depositAddressDao.clear()
                                .andThen(Single.fromCallable {
                                    val json = FileUtils.getJson(context, file)
                                    gson.fromJson(json, Array<DepositAddress>::class.java).toList()
                                })
                                .map { addresses ->
                                    addresses.map { item ->
                                        DepositAddressEntity().apply {
                                            type = item.type
                                            desc = item.desc
                                            address = item.address
                                            schedule = item.schedule
                                            location = item.location
                                        }
                                    }
                                }
                                .flatMapCompletable { entities ->
                                    depositAddressDao.add(entities)
                                }
                                .andThen(getFileHash(file))
                                .flatMapCompletable { hash -> saveHashToPrefs(hashPref, hash) }
                                .andThen(depositAddressDao.getAll())
                    }
                }
                .map { it.map { item -> item.toDepositAddress() } }
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
        val hash = cachePrefs.getString(hashPref, "")
        return Single.just(hash)
    }

    private fun getFileHash(file: String): Single<String> {
        val md5Hash = FileUtils.getMd5Hash(context, file)
        return Single.just(md5Hash)
    }

    private fun saveHashToPrefs(hashPref: String, hash: String): Completable {
        return Completable.fromAction {
            cachePrefs.edit().putString(hashPref, hash).apply()
        }
    }

    companion object {
        const val BUY_ADDRESS_PREF = "${BuildConfig.APPLICATION_ID}.BUY_ADDRESS_HASH"
        const val DEPOSIT_ADDRESS_PREF = "${BuildConfig.APPLICATION_ID}.DEPOSIT_ADDRESS_HASH"

        const val BUY_ADDRESS_FILE = "card_buy.json"
        const val DEPOSIT_ADDRESS_FILE = "card_deposit.json"
    }

}