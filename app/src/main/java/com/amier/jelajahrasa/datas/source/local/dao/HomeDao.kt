package com.amier.jelajahrasa.datas.source.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.amier.jelajahrasa.datas.source.local.entity.LocalFood

@Dao
interface HomeDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertListLikedFood(list:List<LocalFood>)

    @Insert(onConflict = OnConflictStrategy.ABORT)
    suspend fun insertLikedFood(list:LocalFood):Long

    @Query("SELECT * FROM food ORDER BY id DESC LIMIT :page,:pageSize")
    suspend fun getPagedLikedFood(page: Int, pageSize: Int): List<LocalFood>

    @Query("SELECT COUNT(*) FROM food")
    suspend fun getCountLikedFood():Int?
}