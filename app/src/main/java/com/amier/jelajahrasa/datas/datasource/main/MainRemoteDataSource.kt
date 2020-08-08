package com.amier.jelajahrasa.datas.datasource.main

import com.amier.jelajahrasa.datas.model.BaseResponse
import com.amier.jelajahrasa.datas.model.Food
import com.amier.jelajahrasa.datas.source.remote.ApiServiceImpl
import com.amier.jelajahrasa.datas.model.PagedFoods
import com.amier.jelajahrasa.external.events.Resource
import javax.inject.Inject

class MainRemoteDataSource @Inject constructor(private val apiService: ApiServiceImpl) : MainDataSource.Remote {
    override suspend fun getPagedFood(page: Int, pageSize: Int): Resource<PagedFoods?>? {
        val res = apiService.getFoods(page, pageSize)
        return if (res.isSuccessful) {
            val dat = res.body()
            if (dat != null) {
                when (dat.status) {
                    "ok" -> {
                        Resource.success(dat.response)
                    }
                    "error" -> {
                        Resource.error(dat.msg, dat.response)
                    }
                    "max" -> {
                        Resource.error(dat.msg, null)
                    }
                    else -> Resource.error("Error Data Not Available", null)
                }
            } else {
                Resource.error("Error Body is Null", null)
            }
        } else
            Resource.error("Response Not Successfully", null)
    }

    override suspend fun addToLikes(userId: String?, foodId: Int?): Resource<BaseResponse<Food>?>? {
        TODO("Not yet implemented")
    }

    override suspend fun removeFromLikes(userId: String?, foodId: Int?): Resource<BaseResponse<Food>?>? {
        TODO("Not yet implemented")
    }

}