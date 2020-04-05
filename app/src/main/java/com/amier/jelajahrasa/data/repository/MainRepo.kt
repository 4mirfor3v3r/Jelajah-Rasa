package com.amier.jelajahrasa.data.repository

import com.amier.jelajahrasa.data.api.ApiHelper
import com.amier.jelajahrasa.data.model.Food
import io.reactivex.Single

class MainRepo (private val apiHelper: ApiHelper){
    fun getFoods():Single<List<Food>>{
        return apiHelper.getFoods()
    }
}