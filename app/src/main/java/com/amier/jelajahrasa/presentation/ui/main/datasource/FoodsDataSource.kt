package com.amier.jelajahrasa.presentation.ui.main.datasource

import androidx.lifecycle.MutableLiveData
import androidx.paging.PageKeyedDataSource
import com.amier.jelajahrasa.datas.model.Food
import com.amier.jelajahrasa.domain.usecase.MainUseCase
import com.amier.jelajahrasa.external.events.Status
import kotlinx.coroutines.*

class FoodsDataSource(private val scope: CoroutineScope, private val useCase: MainUseCase) :
    PageKeyedDataSource<Int, Food>() {

    var state: MutableLiveData<Status> = MutableLiveData()
    var message: MutableLiveData<String> = MutableLiveData()

    override fun loadInitial(
        params: LoadInitialParams<Int>,
        callback: LoadInitialCallback<Int, Food>
    ) {
        updateState(Status.LOADING)
        scope.launch(Dispatchers.IO) {
            delay(1000)
            val res = withContext(Dispatchers.IO) {
                useCase.getPagedNews(1, params.requestedLoadSize)
            }
            when (res?.status) {
                Status.SUCCESS -> {
                    res.data?.let { callback.onResult(it, null, 2) }
                    updateState(Status.SUCCESS)
                }
                Status.ERROR -> {
                    if (res.message != null)
                        updateMessage(res.message)
                    updateState(Status.ERROR)
                }
                Status.LOADING -> {
                    updateState(Status.LOADING)
                }
            }
        }
    }

    override fun loadAfter(params: LoadParams<Int>, callback: LoadCallback<Int, Food>) {
        updateState(Status.LOADING)
        scope.launch(Dispatchers.IO) {
            delay(1000)
            val res = withContext(Dispatchers.IO) {
                useCase.getPagedNews(params.key, params.requestedLoadSize)
            }
            when (res?.status) {
                Status.SUCCESS -> {
                    res.data?.let { callback.onResult(it, params.key + 1) }
                    updateState(Status.SUCCESS)
                }
                Status.ERROR -> {
                    if (res.message != null)
                        updateMessage(res.message)
                    updateState(Status.ERROR)
                }
                Status.LOADING -> {
                    updateState(Status.LOADING)
                }
            }
        }
    }

    override fun loadBefore(params: LoadParams<Int>, callback: LoadCallback<Int, Food>) {
    }

    private fun updateState(state: Status) {
        this.state.postValue(state)
    }

    private fun updateMessage(state: String) {
        this.message.postValue(state)
    }
}