package com.amier.jelajahrasa.presentation.base

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.amier.jelajahrasa.data.api.ApiHelper
import com.amier.jelajahrasa.data.repository.DetailRepo
import com.amier.jelajahrasa.data.repository.LoginRegisterRepo
import com.amier.jelajahrasa.data.repository.MainRepo
import com.amier.jelajahrasa.presentation.main.viewmodel.DetailViewModel
import com.amier.jelajahrasa.presentation.ui.auth.viewmodel.LoginViewModel
import com.amier.jelajahrasa.presentation.main.viewmodel.MainViewModel
import com.amier.jelajahrasa.presentation.ui.auth.viewmodel.RegisterViewModel

class ViewModelFactory(private val apiHelper: ApiHelper):ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return when {
//            modelClass.isAssignableFrom(LoginViewModel::class.java) -> LoginViewModel(
//                LoginRegisterRepo(apiHelper)
//            ) as T
            modelClass.isAssignableFrom(RegisterViewModel::class.java) -> RegisterViewModel(
                LoginRegisterRepo(apiHelper)
            ) as T
            modelClass.isAssignableFrom(MainViewModel::class.java) -> MainViewModel(MainRepo(apiHelper)) as T
            modelClass.isAssignableFrom(DetailViewModel::class.java) -> DetailViewModel(DetailRepo(apiHelper)) as T
            else -> throw IllegalArgumentException("Unknown Class Name")
        }
    }
}