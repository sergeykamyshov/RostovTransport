package ru.sergeykamyshov.rostovtransport.data.help

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import io.reactivex.Completable
import io.reactivex.Single

@Dao
interface HelpContactDao {

    @Query("SELECT * FROM helpContact")
    fun getAll(): Single<List<ContactEntity>>

    @Insert
    fun add(entity: ContactEntity): Completable

    @Insert
    fun add(entities: List<ContactEntity>): Completable

    @Query("DELETE FROM helpContact")
    fun clear(): Completable

}