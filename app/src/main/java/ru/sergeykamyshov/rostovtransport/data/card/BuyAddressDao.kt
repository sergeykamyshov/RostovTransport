package ru.sergeykamyshov.rostovtransport.data.card

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import io.reactivex.Completable
import io.reactivex.Single

@Dao
interface BuyAddressDao {

    @Query("SELECT * FROM card_buy_address")
    fun getAll(): Single<List<BuyAddressEntity>>

    @Insert
    fun add(entities: List<BuyAddressEntity>): Completable

    @Query("DELETE FROM card_buy_address")
    fun clear(): Completable

}