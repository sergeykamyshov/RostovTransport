package ru.sergeykamyshov.rostovtransport.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import ru.sergeykamyshov.rostovtransport.data.card.BuyAddressDao
import ru.sergeykamyshov.rostovtransport.data.card.BuyAddressEntity
import ru.sergeykamyshov.rostovtransport.data.card.DepositAddressDao
import ru.sergeykamyshov.rostovtransport.data.card.DepositAddressEntity
import ru.sergeykamyshov.rostovtransport.data.database.StringListConverter
import ru.sergeykamyshov.rostovtransport.data.help.ContactEntity
import ru.sergeykamyshov.rostovtransport.data.help.HelpContactDao

@Database(entities = [ContactEntity::class, DepositAddressEntity::class, BuyAddressEntity::class], version = 1)
@TypeConverters(StringListConverter::class)
abstract class AppDatabase : RoomDatabase() {

    abstract fun helpContactDao(): HelpContactDao
    abstract fun depositAddressDao(): DepositAddressDao
    abstract fun buyAddressDao(): BuyAddressDao

    companion object {
        fun getInstance(context: Context): AppDatabase {
            return Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "rt.db"
            ).build()
        }
    }

}