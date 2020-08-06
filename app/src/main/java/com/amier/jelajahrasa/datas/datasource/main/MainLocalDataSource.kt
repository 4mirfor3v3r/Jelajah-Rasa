package com.amier.jelajahrasa.datas.datasource.main

import com.amier.jelajahrasa.datas.model.Food
import com.amier.jelajahrasa.datas.source.local.dao.HomeDao
import com.amier.jelajahrasa.datas.source.local.entity.LocalFood
import com.amier.jelajahrasa.datas.utils.Mapper
import com.amier.jelajahrasa.datas.datasource.main.MainDataSource
import com.amier.jelajahrasa.utils.Resource
import javax.inject.Inject

class MainLocalDataSource @Inject constructor(private val dao: HomeDao) : MainDataSource.Local {
    override suspend fun savePagedFood(list: List<LocalFood>) {
        dao.insertListLikedFood(list)
    }

    override suspend fun getPagedFood(page: Int, pageSize: Int): Resource<List<Food>?>? {
        val maxPages = (dao.getCountLikedFood() ?: 0) / pageSize
        return if (page < maxPages) {
            val res = dao.getPagedLikedFood(page, pageSize)
            return if (res.isNotEmpty()){
                Resource.success(Mapper.toRemoteList(res))
            } else
                Resource.error("Data Not Available",null)
        }
        else Resource.error("Data Not Available",null)
    }

}