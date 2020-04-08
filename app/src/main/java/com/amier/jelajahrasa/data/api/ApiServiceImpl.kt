package com.amier.jelajahrasa.data.api

import com.amier.jelajahrasa.data.model.HighItemMain
import com.amier.jelajahrasa.data.model.HighLikesMain
import com.amier.jelajahrasa.data.model.HighUser
import io.reactivex.Single

class ApiServiceImpl:ApiService {
    override fun getFoods(): Single<HighItemMain> {
        return NetworkConfig.api().getFoods()
    }

    override fun register(name:String, email: String, password: String): Single<HighUser> {
        return NetworkConfig.api().register(name, email, password)
    }

    override fun login(email: String, password: String): Single<HighUser> {
        return NetworkConfig.api().login(email,password)
    }

    override fun addLikes(userID: String?, foodID: Int?): Single<HighLikesMain> {
        return NetworkConfig.api().addLikes(userID, foodID)
    }

    override fun removeLikes(userID: String?, foodID: Int?): Single<HighLikesMain> {
        return NetworkConfig.api().removeLikes(userID, foodID)
    }
}