package com.amier.jelajahrasa.data.repository

import com.amier.jelajahrasa.data.api.ApiHelper
import com.amier.jelajahrasa.data.model.HighUser
import com.amier.jelajahrasa.data.model.User
import io.reactivex.Single
import okhttp3.Response
import okhttp3.ResponseBody

class LoginRegisterRepo(private val apiHelper: ApiHelper) {
    fun login(email:String,password:String):Single<HighUser>{
        return apiHelper.login(email,password)
    }
    fun register(name:String, email: String, password: String):Single<HighUser>{
        return apiHelper.register(name,email,password)
    }
}