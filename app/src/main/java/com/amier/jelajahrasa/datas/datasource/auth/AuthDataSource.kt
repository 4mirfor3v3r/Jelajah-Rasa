package com.amier.jelajahrasa.datas.datasource.auth

import com.amier.jelajahrasa.datas.model.BaseResponse
import com.amier.jelajahrasa.datas.model.User
import com.amier.jelajahrasa.external.events.Resource

interface AuthDataSource {
    interface Remote {
        suspend fun performLogin(email:String,password:String): Resource<BaseResponse<User>>
        suspend fun performRegister(name: String, email: String, password: String): Resource<BaseResponse<User>>
    }

    interface Local  {
        suspend fun saveUserPreferences(user:BaseResponse<User>)
    }
}