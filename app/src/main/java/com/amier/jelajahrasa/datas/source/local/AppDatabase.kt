package com.amier.jelajahrasa.datas.source.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.amier.jelajahrasa.datas.source.local.dao.HomeDao
import com.amier.jelajahrasa.datas.source.local.entity.LocalFood
import com.amier.jelajahrasa.datas.utils.ArrayStringTypeConverter
import com.amier.jelajahrasa.datas.utils.FactTypeConverter


@Database(entities = [LocalFood::class], version = 1, exportSchema = false)
@TypeConverters(FactTypeConverter::class,ArrayStringTypeConverter::class)
abstract class AppDatabase : RoomDatabase() {
    abstract val homeDao: HomeDao

    companion object {
        const val DB_NAME = "com.amier.jelajah_rasa.DATABASE.db"
    }
}