package com.amier.jelajahrasa.domain.repository

import com.amier.jelajahrasa.datas.model.Food
import com.amier.jelajahrasa.external.events.Resource

interface MainRepository {
    suspend fun getPagedFoods(page:Int,pageSize:Int): Resource<List<Food>?>?
    suspend fun addToLikes(userId:String?,foodId:Int?) : Resource<Food?>?
    suspend fun removeFromLikes(userId:String?,foodId:Int?) : Resource<Food?>?
}