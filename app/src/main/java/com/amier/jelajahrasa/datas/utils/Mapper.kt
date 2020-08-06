package com.amier.jelajahrasa.datas.utils

import com.amier.jelajahrasa.datas.model.Fact
import com.amier.jelajahrasa.datas.model.Food
import com.amier.jelajahrasa.datas.source.local.entity.LocalFact
import com.amier.jelajahrasa.datas.source.local.entity.LocalFood

object Mapper {
    fun toRemoteList(list: List<LocalFood>): List<Food> {
        return list.map {
            return@map Food(
                localFactToFact(it.fact),
                it.id,
                it._id,
                it.imgUrl,
                it.ingredients,
                it.likes,
                it.name,
                it.steps,
                it.time,
                it.v
            )
        }
    }

    private fun localFactToFact(localFact: LocalFact): Fact {
        return Fact(localFact.calori, localFact.carbohidrat, localFact.protein, localFact.vitamin)
    }

    private fun factToLocalFact(localFact: Fact): LocalFact {
        return LocalFact(
            localFact.calori,
            localFact.carbohidrat,
            localFact.protein,
            localFact.vitamin
        )
    }

    fun toLocalList(list: List<Food>?): List<LocalFood> {
        if (list != null) {
            return list.map {
                return@map LocalFood(
                    it.id,
                    it._id,
                    it.name,
                    it.imgUrl,
                    it.likes,
                    it.time,
                    factToLocalFact(it.fact),
                    it.ingredients,
                    it.steps,
                    it.v
                )
            }
        } else return emptyList()
    }
}