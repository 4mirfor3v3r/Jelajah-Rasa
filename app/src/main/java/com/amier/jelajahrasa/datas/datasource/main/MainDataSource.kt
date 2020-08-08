package com.amier.jelajahrasa.datas.datasource.main

import com.amier.jelajahrasa.datas.model.BaseResponse
import com.amier.jelajahrasa.datas.model.Food
import com.amier.jelajahrasa.datas.model.PagedFoods
import com.amier.jelajahrasa.datas.source.local.entity.LocalFood
import com.amier.jelajahrasa.external.events.Resource

interface MainDataSource {
    interface Remote {
        suspend fun getPagedFood(page: Int, pageSize: Int): Resource<PagedFoods?>?
        suspend fun addToLikes(userId:String?,foodId:Int?) : Resource<BaseResponse<Food>?>?
        suspend fun removeFromLikes(userId:String?,foodId:Int?) : Resource<BaseResponse<Food>?>?
    }

    interface Local {
        suspend fun savePagedFood(list: List<LocalFood>)
    }
}