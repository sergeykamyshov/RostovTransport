package ru.sergeykamyshov.rostovtransport.data.card

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import io.reactivex.Completable
import io.reactivex.Single

@Dao
interface DepositAddressDao {

    @Query("SELECT * FROM card_deposit_address")
    fun getAll(): Single<List<DepositAddressEntity>>

    @Insert
    fun add(entities: List<DepositAddressEntity>): Completable

    @Query("DELETE FROM card_deposit_address")
    fun clear(): Completable

}