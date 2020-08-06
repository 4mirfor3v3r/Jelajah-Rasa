package com.amier.jelajahrasa.datas.datasource.main

import com.amier.jelajahrasa.datas.model.Food
import com.amier.jelajahrasa.datas.source.local.entity.LocalFood
import com.amier.jelajahrasa.utils.Resource

interface MainDataSource {
    interface Remote {
        suspend fun getPagedFood(page: Int, pageSize: Int): Resource<List<Food>?>?
    }

    interface Local : Remote {
        suspend fun savePagedFood(list: List<LocalFood>)
    }
}