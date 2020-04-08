package com.amier.jelajahrasa.data.repository

import com.amier.jelajahrasa.data.api.ApiHelper
import com.amier.jelajahrasa.data.model.HighItemMain
import com.amier.jelajahrasa.data.model.HighLikesMain
import io.reactivex.Single

class MainRepo (private val apiHelper: ApiHelper){
    fun getFoods():Single<HighItemMain>{
        return apiHelper.getFoods()
    }
    fun addLikes(userId:String?,foodId:Int?):Single<HighLikesMain>{
        return apiHelper.addLikes(userId, foodId)
    }
    fun removeLikes(userId:String?,foodId:Int?):Single<HighLikesMain>{
        return apiHelper.removeLikes(userId, foodId)
    }
}