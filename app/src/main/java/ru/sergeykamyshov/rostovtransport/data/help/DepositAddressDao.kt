package ru.sergeykamyshov.rostovtransport.data.help

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import io.reactivex.Completable
import io.reactivex.Single
import ru.sergeykamyshov.rostovtransport.data.card.DepositAddressEntity

@Dao
interface DepositAddressDao {

    @Query("SELECT * FROM depositAddress")
    fun getAll(): Single<List<DepositAddressEntity>>

    @Insert
    fun add(entities: List<DepositAddressEntity>): Completable

    @Query("DELETE FROM helpContact")
    fun clear(): Completable

}