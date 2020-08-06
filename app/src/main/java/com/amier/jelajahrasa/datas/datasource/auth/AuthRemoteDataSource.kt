package com.amier.jelajahrasa.datas.datasource.auth

import com.amier.jelajahrasa.datas.model.Food
import com.amier.jelajahrasa.datas.source.remote.ApiServiceImpl
import com.amier.jelajahrasa.datas.datasource.main.MainDataSource
import com.amier.jelajahrasa.datas.model.BaseResponse
import com.amier.jelajahrasa.datas.model.User
import com.amier.jelajahrasa.utils.Resource
import retrofit2.Response
import retrofit2.Retrofit
import javax.inject.Inject

class AuthRemoteDataSource @Inject constructor(private val apiService: ApiServiceImpl) : AuthDataSource.Remote {
    override suspend fun performLogin(email: String, password: String): Resource<BaseResponse<User>> {
        val response = apiService.login(email, password)

        return if (response.isSuccessful){
            Resource.success(response.body())
        }
        else {
            when (response.code()) {
                400 -> Resource.error("400 Bad Request", null)
                401 -> Resource.error("401 Unauthorized", null)
                403 -> Resource.error("403 Forbidden", null)
                else -> Resource.error(response.message(), null)
            }
        }
    }

    override suspend fun performRegister(name: String, email: String, password: String): Resource<BaseResponse<User>> {
        TODO("Not yet implemented")
    }

}