package com.amier.jelajahrasa.ui.main.viewmodel

import android.os.Parcelable
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.amier.jelajahrasa.data.model.HighItemMain
import com.amier.jelajahrasa.data.repository.MainRepo
import com.amier.jelajahrasa.utils.Resource
import com.amier.jelajahrasa.utils.SingleLiveData
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class MainViewModel (private val mainRepo: MainRepo):ViewModel(){
    private val list = MutableLiveData<Resource<HighItemMain>>()
    private val compositeDisposable = CompositeDisposable()

    val isNested = false
    val uiEventData = SingleLiveData<Int>()
    var uiItemData = MutableLiveData<Parcelable>()

init {
    getFoods()
}

    private fun getFoods(){
        Log.e("INITLIST:", list.value?.data?.foods.toString()+"PPP")
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
    private fun updateLikesDatabase(){

    }

    fun onClickEvent(value:Int){
        uiEventData.setValue(value)
    }
    fun setLikesOrNot(data: Int?){
        TODO("UPDATE LIKES")
//        if (data!=null && ){
//        }
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