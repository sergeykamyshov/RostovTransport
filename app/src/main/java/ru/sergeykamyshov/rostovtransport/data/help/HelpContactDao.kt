package ru.sergeykamyshov.rostovtransport.data.help

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import io.reactivex.Completable
import io.reactivex.Single

@Dao
interface HelpContactDao {

    @Query("SELECT * FROM helpContact WHERE type = :type")
    fun getByType(type: String): Single<List<ContactEntity>>

    @Insert
    fun add(entities: List<ContactEntity>): Completable

    @Query("DELETE FROM helpContact WHERE type = :type")
    fun removeByType(type: String): Completable

}