package com.amier.jelajahrasa.presentation.ui.auth.viewmodel

import android.content.Context
import androidx.databinding.ObservableField
import androidx.lifecycle.*
import com.amier.jelajahrasa.domain.usecase.AuthUseCase
import com.amier.jelajahrasa.external.extensions.validateEmail
import com.amier.jelajahrasa.external.extensions.validateName
import com.amier.jelajahrasa.external.extensions.validatePassword
import com.amier.jelajahrasa.external.events.SingleLiveData
import com.amier.jelajahrasa.external.events.Status
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

class RegisterViewModel @Inject constructor(private val useCase: AuthUseCase) : ViewModel() {
    val uiEventData = SingleLiveData<Int>()

    var name: MutableLiveData<String> = MutableLiveData()
    var email: MutableLiveData<String> = MutableLiveData()
    var password: MutableLiveData<String> = MutableLiveData()
    var errorName: MutableLiveData<String> = MutableLiveData()
    var errorEmail: MutableLiveData<String> = MutableLiveData()
    var errorPassword: MutableLiveData<String> = MutableLiveData()

    private var isNameValid: Boolean = false
    private var isEmailValid: Boolean = false
    private var isPasswordValid: Boolean = false
    var isValid: MutableLiveData<Boolean> = MutableLiveData()

    var isLoading = ObservableField<Boolean>()
    var errorMessage = ""

    fun register() {
        isLoading.set(true)
        viewModelScope.launch {
            try {
                val res = withContext(Dispatchers.IO) {
                    useCase.performRegister(name.value!!, email.value!!, password.value!!)
                }
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
            }catch (e:Exception) {
                e.printStackTrace()
            }
        }
    }

    fun setup(lifecycleOwner: LifecycleOwner, context: Context) {
        name.observe(lifecycleOwner, Observer { name ->
            val validationModel = name.validateName(context)
            isNameValid = validationModel.isValid
            validateInput(isNameValid, isEmailValid, isPasswordValid)
            errorName.postValue(validationModel.message)
        })
        email.observe(lifecycleOwner, Observer { email ->
            val validationModel = email.validateEmail(context)
            isEmailValid = validationModel.isValid
            validateInput(isNameValid, isEmailValid, isPasswordValid)
            errorEmail.postValue(validationModel.message)
        })
        password.observe(lifecycleOwner, Observer { password ->
            val validationModel = password.validatePassword(context)
            isPasswordValid = validationModel.isValid
            validateInput(isNameValid, isEmailValid, isPasswordValid)
            errorPassword.postValue(validationModel.message)
        })
    }

    private fun validateInput(name: Boolean, email: Boolean, password: Boolean) {
        isValid.postValue(name && email && password)
    }
    fun onClickEvent(value: Int) {
        uiEventData.setValue(value)
    }
}