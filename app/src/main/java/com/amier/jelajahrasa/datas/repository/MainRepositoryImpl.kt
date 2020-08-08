package com.amier.jelajahrasa.datas.repository

import com.amier.jelajahrasa.datas.datasource.main.MainDataSource
import com.amier.jelajahrasa.datas.model.Food
import com.amier.jelajahrasa.domain.repository.MainRepository
import com.amier.jelajahrasa.external.events.Resource
import javax.inject.Inject

class MainRepositoryImpl @Inject constructor(private val local: MainDataSource.Local, private val remote: MainDataSource.Remote) : MainRepository {
    override suspend fun getPagedFoods(page: Int, pageSize: Int): Resource<List<Food>?>? {
        val res = remote.getPagedFood(page, pageSize)
        return if (res?.message == null) {
            Resource.success(res?.data?.foods)
        } else {
            Resource.error(res.message, res.data?.foods)
        }
    }

    override suspend fun addToLikes(userId: String?, foodId: Int?): Resource<Food?>? {
        TODO("Not yet implemented")
    }

    override suspend fun removeFromLikes(userId: String?, foodId: Int?): Resource<Food?>? {
        TODO("Not yet implemented")
    }
}