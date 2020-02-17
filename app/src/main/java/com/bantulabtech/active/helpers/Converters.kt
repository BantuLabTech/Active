package com.bantulabtech.active.helpers

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.util.*

class Converters {
    @TypeConverter
    fun toDate(dateLong: Long?): Date? {
        return dateLong?.let { Date(it) }
    }

    @TypeConverter
    fun toLong(date: Date?): Long? {
        return date?.time
    }

    object ArrayToStringConverter {
        @TypeConverter
        fun restoreList(listOfString: String?): List<String> {
            return Gson().fromJson(
                listOfString,
                object : TypeToken<List<String?>?>() {}.type
            )
        }

        @TypeConverter
        fun saveList(listOfString: List<String?>?): String {
            return Gson().toJson(listOfString)
        }
    }
}