package com.acemirr.jelajah_rasa_data.datasource.main

import com.acemirr.jelajah_rasa_data.model.Food
import com.acemirr.jelajah_rasa_data.source.local.dao.HomeDao
import com.acemirr.jelajah_rasa_data.source.local.entity.LocalFood
import com.acemirr.jelajah_rasa_data.utils.Mapper
import com.acemirr.jelajah_rasa_data.utils.Resource
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