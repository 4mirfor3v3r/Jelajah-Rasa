package com.acemirr.jelajah_rasa_data.datasource.main

import com.acemirr.jelajah_rasa_data.model.Food
import com.acemirr.jelajah_rasa_data.source.local.entity.LocalFood
import com.acemirr.jelajah_rasa_data.utils.Resource

interface MainDataSource {
    interface Remote {
        suspend fun getPagedFood(page: Int, pageSize: Int): Resource<List<Food>?>?
    }

    interface Local : Remote {
        suspend fun savePagedFood(list: List<LocalFood>)
    }
}