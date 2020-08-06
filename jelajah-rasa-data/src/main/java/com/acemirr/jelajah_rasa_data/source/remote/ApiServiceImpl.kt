package com.acemirr.jelajah_rasa_data.source.remote

import com.acemirr.jelajah_rasa_data.model.BaseResponse
import com.acemirr.jelajah_rasa_data.model.Food
import com.acemirr.jelajah_rasa_data.model.User
import retrofit2.Response

class ApiServiceImpl : ApiService {
    override fun getFoods(page: Int, pageSize: Int): Response<BaseResponse<List<Food>>> {
        return NetworkConfig.api().getFoods(page, pageSize)
    }

    override fun register(
        name: String,
        email: String,
        password: String
    ): Response<BaseResponse<User>> {
        return NetworkConfig.api().register(name, email, password)
    }

    override fun login(email: String, password: String): Response<BaseResponse<User>> {
        return NetworkConfig.api().login(email, password)
    }

    override fun addLikes(userID: String?, foodID: Int?): Response<BaseResponse<Food>> {
        return NetworkConfig.api().addLikes(userID, foodID)
    }

    override fun removeLikes(userID: String?, foodID: Int?): Response<BaseResponse<Food>> {
        return NetworkConfig.api().removeLikes(userID, foodID)
    }
}