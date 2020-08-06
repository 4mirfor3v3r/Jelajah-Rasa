package com.acemirr.jelajah_rasa_domain.repository

import com.acemirr.jelajah_rasa_domain.utils.Resource

interface MainRepository {
    suspend fun getPagedFoods(page:Int,pageSize:Int): Resource<>
}