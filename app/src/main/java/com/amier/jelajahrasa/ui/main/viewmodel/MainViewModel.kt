package com.amier.jelajahrasa.ui.main.viewmodel

import android.os.Parcelable
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.amier.jelajahrasa.App
import com.amier.jelajahrasa.data.model.HighItemMain
import com.amier.jelajahrasa.data.model.HighLikesMain
import com.amier.jelajahrasa.data.repository.MainRepo
import com.amier.jelajahrasa.utils.Resource
import com.amier.jelajahrasa.utils.SingleLiveData
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class MainViewModel (private val mainRepo: MainRepo):ViewModel(){
    private val list = MutableLiveData<Resource<HighItemMain>>()
    private val likedId = MutableLiveData<Resource<HighLikesMain>>()
    val likedArray = MutableLiveData<ArrayList<Int>>()
    private val compositeDisposable = CompositeDisposable()

    val uiEventData = SingleLiveData<Int>()
    var uiItemData = MutableLiveData<Parcelable>()


init {
    getFoods()
}

    private fun getLikedArray(){
//        App.prefHelper.getArray("")
    }

    private fun getFoods(){
        list.postValue(Resource.loading(null))
        compositeDisposable.add(
            mainRepo.getFoods()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    list.postValue(Resource.success(it))
                },{
                    list.value = Resource.error("Something Wen't Wrong : ${it.message}",null)
                })
        )
    }

    fun onClickEvent(value:Int){
        uiEventData.setValue(value)
    }
    fun addToLikes(data: Int?){
        val userId =App.prefHelper?.getString("userId") ?: ""
        if (userId != ""){
            compositeDisposable.add(
                mainRepo.addLikes(userId,data)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe({
                        likedId.postValue(Resource.success(it))
                    },{
                        likedId.value = Resource.error("Something wen't Wrong : ${it.message}",null)
                    })
            )
        }
    }
    fun removeFromLikes(data: Int?){
        val userId =App.prefHelper?.getString("userId") ?: ""
        if (userId != ""){
            compositeDisposable.add(
                mainRepo.removeLikes(userId,data)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe({
                        likedId.postValue(Resource.success(it))
                    },{
                        likedId.value = Resource.error("Something wen't Wrong : ${it.message}",null)
                    })
            )
        }
    }

    fun onRefresh(){
        getFoods()
    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.dispose()
    }
    fun getList():MutableLiveData<Resource<HighItemMain>>{
        return list
    }
}