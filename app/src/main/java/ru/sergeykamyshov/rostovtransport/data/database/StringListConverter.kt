package ru.sergeykamyshov.rostovtransport.data.database

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class StringListConverter {

    @TypeConverter
    fun fromList(list: List<String>): String {
        return Gson().toJson(list)
    }

    @TypeConverter
    fun toList(list: String): List<String> {
        return Gson().fromJson(list, object : TypeToken<List<String>>() {}.type)
    }
}