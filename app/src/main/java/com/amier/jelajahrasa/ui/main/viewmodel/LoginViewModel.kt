package com.amier.jelajahrasa.ui.main.viewmodel

import android.content.Intent
import android.util.Log
import android.view.View
import androidx.databinding.ObservableField
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.amier.jelajahrasa.App
import com.amier.jelajahrasa.data.model.HighUser
import com.amier.jelajahrasa.data.model.User
import com.amier.jelajahrasa.data.repository.LoginRegisterRepo
import com.amier.jelajahrasa.ui.main.view.RegisterActivity
import com.amier.jelajahrasa.utils.Constants
import com.amier.jelajahrasa.utils.Resource
import com.amier.jelajahrasa.utils.SingleLiveData
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import okhttp3.Response
import okhttp3.ResponseBody

class LoginViewModel(private val loginRegisterRepo: LoginRegisterRepo):ViewModel() {
    private val user = MutableLiveData<Resource<HighUser>>()
    private val compositeDisposable = CompositeDisposable()
    val email = ObservableField<String>()
    val password = ObservableField<String>()
    val uiEventData = SingleLiveData<Int>()

    private fun verify(email:String, password:String){
        user.postValue(Resource.loading(null))
        compositeDisposable.add(
            loginRegisterRepo.login(email,password)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    user.postValue(Resource.success(it))
                },{
                    user.value = Resource.error("Something went Wrong ${it.message}",null)
                })
        )
    }
    fun performLogin(email:String?, password:String?){
        if (email != "" && password != "") {
            verify(email!!, password!!)
        }
    }
    fun saveToPreferences(dat: User){
        App.prefHelper?.setString(Constants.USER_ID,dat._id)
        App.prefHelper?.setArray(Constants.LIKED_FOODS_ARRAY,dat.likedFoodId)
    }

//    SINGLE LIVE EVENT
    fun onClickEvent(value:Int){
        uiEventData.setValue(value)
    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.dispose()
    }
    fun getUser():MutableLiveData<Resource<HighUser>>{
        return user
    }
}