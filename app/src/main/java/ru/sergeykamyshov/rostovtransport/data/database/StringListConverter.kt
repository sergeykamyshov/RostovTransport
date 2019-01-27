package ru.sergeykamyshov.rostovtransport.data.database

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class StringListConverter {

    private val gson = Gson()

    @TypeConverter
    fun fromList(list: List<String>): String {
        return gson.toJson(list)
    }

    @TypeConverter
    fun toList(list: String): List<String> {
        return gson.fromJson(list, object : TypeToken<List<String>>() {}.type)
    }
}