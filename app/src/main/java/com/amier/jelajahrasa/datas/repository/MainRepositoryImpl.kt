package com.amier.jelajahrasa.datas.repository

import com.amier.jelajahrasa.datas.datasource.main.MainDataSource
import com.amier.jelajahrasa.datas.model.BaseResponse
import com.amier.jelajahrasa.datas.model.Food
import com.amier.jelajahrasa.domain.repository.MainRepository
import com.amier.jelajahrasa.utils.Resource
import javax.inject.Inject

class MainRepositoryImpl @Inject constructor(private val local: MainDataSource.Local, private val remote: MainDataSource.Remote):MainRepository {
    override suspend fun getPagedFoods(page: Int, pageSize: Int): Resource<List<Food>?>? {
        return remote.getPagedFood(page, pageSize)
    }
}