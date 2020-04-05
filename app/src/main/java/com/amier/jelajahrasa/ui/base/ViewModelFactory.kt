package com.amier.jelajahrasa.ui.base

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.amier.jelajahrasa.data.api.ApiHelper
import com.amier.jelajahrasa.data.repository.LoginRegisterRepo
import com.amier.jelajahrasa.data.repository.MainRepo
import com.amier.jelajahrasa.ui.main.viewmodel.LoginViewModel
import com.amier.jelajahrasa.ui.main.viewmodel.MainViewModel
import com.amier.jelajahrasa.ui.main.viewmodel.RegisterViewModel

class ViewModelFactory(private val apiHelper: ApiHelper):ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return when {
            modelClass.isAssignableFrom(LoginViewModel::class.java) -> LoginViewModel(LoginRegisterRepo(apiHelper)) as T
            modelClass.isAssignableFrom(RegisterViewModel::class.java) -> RegisterViewModel(LoginRegisterRepo(apiHelper)) as T
            modelClass.isAssignableFrom(MainViewModel::class.java) -> MainViewModel(MainRepo(apiHelper)) as T
            else -> throw IllegalArgumentException("Unknown Class Name")
        }
    }
}