package com.amier.jelajahrasa.domain.usecase

import com.amier.jelajahrasa.datas.model.Food
import com.amier.jelajahrasa.domain.repository.MainRepository
import com.amier.jelajahrasa.external.events.Resource
import javax.inject.Inject

class MainUseCase @Inject constructor(private val repository: MainRepository) {
    suspend fun getPagedNews(page:Int,pageSize:Int) : Resource<List<Food>?>?{
        return repository.getPagedFoods(page, pageSize)
    }
}