package com.acemirr.jelajah_rasa_data.source.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.acemirr.jelajah_rasa_data.source.local.dao.HomeDao
import com.acemirr.jelajah_rasa_data.source.local.entity.LocalFood
import com.acemirr.jelajah_rasa_data.utils.FactTypeConverter


@Database(entities = [LocalFood::class], version = 1, exportSchema = false)
@TypeConverters(FactTypeConverter::class)
abstract class AppDatabase : RoomDatabase() {
    abstract val homeDao: HomeDao

    companion object {
        const val DB_NAME = "com.acemirr.jelajah_rasa.DATABASE.db"
    }
}