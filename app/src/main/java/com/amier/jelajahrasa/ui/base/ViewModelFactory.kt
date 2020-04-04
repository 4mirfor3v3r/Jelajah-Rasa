package com.amier.jelajahrasa.ui.base

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.amier.jelajahrasa.data.api.ApiHelper
import com.amier.jelajahrasa.data.repository.LoginRegisterRepo
import com.amier.jelajahrasa.ui.main.viewmodel.LoginViewModel
import com.amier.jelajahrasa.ui.main.viewmodel.RegisterViewModel

class ViewModelFactory(private val apiHelper: ApiHelper):ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(LoginViewModel::class.java))
            return LoginViewModel(LoginRegisterRepo(apiHelper)) as T
        else if (modelClass.isAssignableFrom(RegisterViewModel::class.java))
            return RegisterViewModel(LoginRegisterRepo(apiHelper)) as T
        throw IllegalArgumentException("Unknown Class Name")
    }
}