package com.amier.jelajahrasa.presentation.ui.auth.viewmodel

import android.content.Context
import androidx.databinding.ObservableField
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.amier.jelajahrasa.datas.model.User
import com.amier.jelajahrasa.domain.usecase.AuthUseCase
import com.amier.jelajahrasa.external.validateEmail
import com.amier.jelajahrasa.external.validatePassword
import com.amier.jelajahrasa.utils.SingleLiveData
import com.amier.jelajahrasa.utils.Status
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

class LoginViewModel @Inject constructor(private val useCase: AuthUseCase) : ViewModel() {
    val uiEventData = SingleLiveData<Int>()

    var email: MutableLiveData<String> = MutableLiveData()
    var password: MutableLiveData<String> = MutableLiveData()
    var errorEmail: MutableLiveData<String> = MutableLiveData()
    var errorPassword: MutableLiveData<String> = MutableLiveData()

    private var isEmailValid: Boolean = false
    private var isPasswordValid: Boolean = false
    var isValid: MutableLiveData<Boolean> = MutableLiveData()

    var isLoading = ObservableField<Boolean>()
    var errorMessage = ""

    fun verify() {
        isLoading.set(true)
        viewModelScope.launch {
            delay(1000)
            val res = withContext(Dispatchers.IO) { useCase.performLogin(email.value!!, password.value!!) }
            when (res.status) {
                Status.SUCCESS -> {
                    isLoading.set(false)
                    if (res.data?.response != null)
                        onClickEvent(2)
                    else {
                        errorMessage = res.data?.msg.toString()
                        onClickEvent(3)
                    }
                }
                Status.ERROR -> {
                    errorMessage = res.data?.msg.toString()
                    isLoading.set(false)
                }
                Status.LOADING -> {
                    isLoading.set(true)
                }
            }
        }
    }
    fun setup(lifecycleOwner: LifecycleOwner,context: Context){
        email.observe(lifecycleOwner, Observer { email ->
            val validationModel = email.validateEmail(context)
            isEmailValid = validationModel.isValid
            validateInput(isEmailValid, isPasswordValid)
            errorEmail.postValue(validationModel.message)
        })
        password.observe(lifecycleOwner, Observer { password ->
            val validationModel = password.validatePassword(context)
            isPasswordValid = validationModel.isValid
            validateInput(isEmailValid, isPasswordValid)
            errorPassword.postValue(validationModel.message)
        })
    }
    private fun validateInput(email: Boolean, password: Boolean) {
        isValid.postValue(email && password)
    }

    //    SINGLE LIVE EVENT
    fun onClickEvent(value: Int) {
        uiEventData.setValue(value)
    }

}