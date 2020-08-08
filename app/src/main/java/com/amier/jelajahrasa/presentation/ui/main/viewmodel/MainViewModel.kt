package com.amier.jelajahrasa.presentation.ui.main.viewmodel

import android.util.Log
import androidx.databinding.ObservableField
import androidx.lifecycle.*
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.amier.jelajahrasa.datas.model.Food
import com.amier.jelajahrasa.domain.usecase.MainUseCase
import com.amier.jelajahrasa.external.extensions.logDebug
import com.amier.jelajahrasa.presentation.ui.main.datasource.FoodsDataSource
import com.amier.jelajahrasa.presentation.ui.main.datasource.FoodsDataSourceFactory
import com.amier.jelajahrasa.external.events.SingleLiveData
import com.amier.jelajahrasa.external.events.Status
import javax.inject.Inject

class MainViewModel @Inject constructor(private val useCase: MainUseCase) : ViewModel() {
    val likedArray = MutableLiveData<ArrayList<Int>>()
    val uiEventData = SingleLiveData<Int>()

    var isLoading = ObservableField<Boolean>()
    var foodsDataSourceFactory :FoodsDataSourceFactory ?=null
    var pagingList: LiveData<PagedList<Food>>? =null


    init {
//        getLikedArray()
        Log.e("VIEWMODEL LIKEDARRAY", likedArray.value.toString())
//        getFoods()
        setupPagedFoods()
    }

//    private fun getLikedArray() {
//        val data = App.prefHelper?.getArray(Constants.LIKED_FOODS_ARRAY) ?: arrayListOf()
//        likedArray.value = data
////        likedArray.postValue(App.prefHelper?.getArray(Constants.LIKED_FOODS_ARRAY)?: arrayListOf())
//    }

    fun setupPagedFoods(){
        val config = PagedList.Config.Builder()
            .setPageSize(5)
            .setInitialLoadSizeHint(10)
            .setEnablePlaceholders(false)
            .build()
        foodsDataSourceFactory = FoodsDataSourceFactory(viewModelScope,useCase)

        if (foodsDataSourceFactory != null) {
            pagingList = LivePagedListBuilder(foodsDataSourceFactory!!, config)
                .setInitialLoadKey(1)
                .build()
            logDebug("SET PAGING LIST")
        }
    }
    fun setRefresh(){
        isLoading.set(true)
        this.foodsDataSourceFactory?.foodsDataSourceLiveData?.value?.invalidate()
        logDebug("REFRESH")
    }
    fun getLoadingState(): LiveData<Status>? {
        return if (foodsDataSourceFactory != null)
            Transformations.switchMap(foodsDataSourceFactory!!.foodsDataSourceLiveData, FoodsDataSource::state)
        else
            null
    }
    fun getMessageValue(): LiveData<String>? {
        return if (foodsDataSourceFactory != null)
            Transformations.switchMap(foodsDataSourceFactory!!.foodsDataSourceLiveData, FoodsDataSource::message)
        else
            null
    }
    //    done
    fun onClickEvent(value: Int) {
        uiEventData.setValue(value)
    }

    //    error
//    fun addToLikes(data: Int?) {
//        val userId = App.prefHelper?.getString(Constants.USER_ID) ?: ""
//        if (userId != "") {
//            compositeDisposable.add(
//                useCase.addLikes(userId, data)
//                    .subscribeOn(Schedulers.io())
//                    .observeOn(AndroidSchedulers.mainThread())
//                    .subscribe({
//                        likedId.postValue(Resource.success(it))
//                        likedArray.value?.add(it.food.id)
//                    }, {
//                        likedId.value =
//                            Resource.error("Something wen't Wrong : ${it.message}", null)
//                    })
//            )
//        }
//    }
//
//    fun removeFromLikes(data: Int?) {
//        val userId = App.prefHelper?.getString("userId") ?: ""
//        if (userId != "") {
//            compositeDisposable.add(
//                useCase.removeLikes(userId, data)
//                    .subscribeOn(Schedulers.io())
//                    .observeOn(AndroidSchedulers.mainThread())
//                    .subscribe({
//                        likedId.postValue(Resource.success(it))
//                        likedArray.value?.remove(it.food.id)
//                    }, {
//                        likedId.value =
//                            Resource.error("Something wen't Wrong : ${it.message}", null)
//                    })
//            )
//        }
//    }
}