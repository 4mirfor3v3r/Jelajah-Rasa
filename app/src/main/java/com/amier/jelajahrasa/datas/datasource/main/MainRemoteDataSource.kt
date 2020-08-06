package com.amier.jelajahrasa.datas.datasource.main

import com.amier.jelajahrasa.datas.model.Food
import com.amier.jelajahrasa.datas.source.remote.ApiServiceImpl
import com.amier.jelajahrasa.datas.datasource.main.MainDataSource
import com.amier.jelajahrasa.utils.Resource
import javax.inject.Inject

class MainRemoteDataSource @Inject constructor(private val apiService: ApiServiceImpl) : MainDataSource.Remote {
    override suspend fun getPagedFood(page: Int, pageSize: Int): Resource<List<Food>?>? {
        val res = apiService.getFoods(page, pageSize)
        return if (res.isSuccessful) {
            val dat = res.body()
            if (dat != null) {
                if (dat.status != "error") {
                    Resource.success(dat.response)
                } else
                    Resource.error("Error Data Not Available", null)
            } else
                Resource.error("Error Body is Null", null)
        } else
            Resource.error("Response Not Successfully", null)
    }

}