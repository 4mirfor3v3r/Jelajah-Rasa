package com.amier.jelajahrasa.datas.utils

import androidx.room.TypeConverter
import com.amier.jelajahrasa.datas.source.local.entity.LocalFact
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class FactTypeConverter {
    private val gson = Gson()

    @TypeConverter
    fun stringToFact(data: String?): LocalFact? {
        if (data == null) {
            return null
        }
        val listType = object : TypeToken<LocalFact>() {}.type
        return gson.fromJson(data, listType)
    }

    @TypeConverter
    fun factToString(someObjects: LocalFact?): String {
        return gson.toJson(someObjects)
    }
}

class ArrayStringTypeConverter {
    @TypeConverter
    fun fromString(value: String?): List<String> {
        val listType = object : TypeToken<List<String?>?>() {}.type
        return Gson().fromJson(value, listType)
    }

    @TypeConverter
    fun fromList(list: List<String?>?): String {
        val gson = Gson()
        return gson.toJson(list)
    }
}