package com.amier.jelajahrasa.datas.source.remote

import com.amier.jelajahrasa.datas.model.BaseResponse
import com.amier.jelajahrasa.datas.model.Food
import com.amier.jelajahrasa.datas.model.PagedFoods
import com.amier.jelajahrasa.datas.model.User
import retrofit2.Response

class ApiServiceImpl : ApiService {
    override suspend fun getFoods(page: Int, pageSize: Int): Response<BaseResponse<PagedFoods>> {
        return NetworkConfig.api().getFoods(page, pageSize)
    }

    override suspend fun register(
        name: String,
        email: String,
        password: String
    ): Response<BaseResponse<User>> {
        return NetworkConfig.api().register(name, email, password)
    }

    override suspend fun login(email: String, password: String): Response<BaseResponse<User>> {
        return NetworkConfig.api().login(email, password)
    }

    override suspend fun addLikes(userID: String?, foodID: Int?): Response<BaseResponse<Food>> {
        return NetworkConfig.api().addLikes(userID, foodID)
    }

    override suspend fun removeLikes(userID: String?, foodID: Int?): Response<BaseResponse<Food>> {
        return NetworkConfig.api().removeLikes(userID, foodID)
    }
}