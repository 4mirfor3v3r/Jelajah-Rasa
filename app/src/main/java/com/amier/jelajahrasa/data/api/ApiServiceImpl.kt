package com.amier.jelajahrasa.data.api

import com.amier.jelajahrasa.data.model.Food
import com.amier.jelajahrasa.data.model.HighUser
import com.amier.jelajahrasa.data.model.User
import io.reactivex.Single
import okhttp3.Response
import okhttp3.ResponseBody

class ApiServiceImpl:ApiService {
    override fun getFoods(): Single<List<Food>> {
        TODO("Not yet implemented")
    }

    override fun register(name:String, email: String, password: String): Single<HighUser> {
        return NetworkConfig.api().register(name, email, password)
    }

    override fun login(email: String, password: String): Single<HighUser> {
        return NetworkConfig.api().login(email,password)
    }
}