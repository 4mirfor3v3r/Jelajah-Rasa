package com.acemirr.jelajah_rasa_data.source.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.acemirr.jelajah_rasa_data.source.local.entity.LocalFood

@Dao
interface HomeDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertListLikedFood(list:List<LocalFood>):Long

    @Insert(onConflict = OnConflictStrategy.ABORT)
    suspend fun insertLikedFood(list:LocalFood):Long

    @Query("SELECT * FROM food ORDER BY id DESC LIMIT :page,:pageSize")
    suspend fun getPagedLikedFood(page: Int, pageSize: Int): List<LocalFood>

    @Query("SELECT COUNT(*) FROM food")
    suspend fun getCountLikedFood():Int?
}