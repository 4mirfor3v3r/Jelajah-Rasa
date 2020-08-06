package com.amier.jelajahrasa.domain.repository

import com.amier.jelajahrasa.datas.model.BaseResponse
import com.amier.jelajahrasa.datas.model.Food
import com.amier.jelajahrasa.utils.Resource

interface MainRepository {
    suspend fun getPagedFoods(page:Int,pageSize:Int): Resource<List<Food>?>?
}