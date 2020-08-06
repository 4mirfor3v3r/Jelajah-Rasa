package com.acemirr.jelajah_rasa_data.utils

import androidx.room.TypeConverter
import com.acemirr.jelajah_rasa_data.source.local.entity.LocalFact
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class FactTypeConverter{
    private val gson = Gson()

    @TypeConverter
    fun stringToFact(data: String?): LocalFact? {
        if (data == null) { return null }
        val listType = object : TypeToken<LocalFact>() {}.type
        return gson.fromJson(data, listType)
    }

    @TypeConverter
    fun factToString(someObjects: LocalFact?): String {
        return gson.toJson(someObjects)
    }
}