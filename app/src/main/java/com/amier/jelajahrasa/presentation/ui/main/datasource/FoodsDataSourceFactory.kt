package com.amier.jelajahrasa.presentation.ui.main.datasource

import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import com.amier.jelajahrasa.datas.model.Food
import com.amier.jelajahrasa.domain.usecase.MainUseCase
import kotlinx.coroutines.CoroutineScope


class FoodsDataSourceFactory(private val scope:CoroutineScope, private val useCase: MainUseCase): DataSource.Factory<Int, Food> () {

    val foodsDataSourceLiveData: MutableLiveData<FoodsDataSource> = MutableLiveData()

    override fun create(): DataSource<Int, Food> {
        val pagingDataSource = FoodsDataSource(scope, useCase)
        foodsDataSourceLiveData.postValue(pagingDataSource)
        return pagingDataSource
    }
}