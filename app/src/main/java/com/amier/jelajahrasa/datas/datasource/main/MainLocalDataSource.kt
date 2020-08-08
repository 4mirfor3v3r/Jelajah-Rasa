package com.amier.jelajahrasa.datas.datasource.main

import com.amier.jelajahrasa.datas.source.local.dao.HomeDao
import com.amier.jelajahrasa.datas.source.local.entity.LocalFood
import javax.inject.Inject

class MainLocalDataSource @Inject constructor(private val dao: HomeDao) : MainDataSource.Local {
    override suspend fun savePagedFood(list: List<LocalFood>) {
        dao.insertListLikedFood(list)
    }

}