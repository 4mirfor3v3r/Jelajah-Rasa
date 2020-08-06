package com.amier.jelajahrasa.di.core.module

import android.content.Context
import androidx.room.Room
import com.amier.jelajahrasa.datas.source.local.AppDatabase
import com.amier.jelajahrasa.datas.source.local.dao.HomeDao
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class DatabaseModule {

    @Provides
    @Singleton
    fun provideAppDatabase(context: Context): AppDatabase {
        return Room.databaseBuilder(context, AppDatabase::class.java, AppDatabase.DB_NAME)
            .fallbackToDestructiveMigrationFrom()
            .allowMainThreadQueries().build()
    }

    @Provides
    fun provideHomeDao(database: AppDatabase): HomeDao {
        return database.homeDao
    }

}