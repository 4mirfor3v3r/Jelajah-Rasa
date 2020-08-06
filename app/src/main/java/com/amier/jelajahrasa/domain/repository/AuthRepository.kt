package com.amier.jelajahrasa.domain.repository

import com.amier.jelajahrasa.datas.model.BaseResponse
import com.amier.jelajahrasa.datas.model.Food
import com.amier.jelajahrasa.datas.model.User
import com.amier.jelajahrasa.utils.Resource

interface AuthRepository {
    suspend fun performLogin(email:String,password:String): Resource<BaseResponse<User>>
    suspend fun performRegister(name: String, email: String, password: String): Resource<BaseResponse<User>>
}