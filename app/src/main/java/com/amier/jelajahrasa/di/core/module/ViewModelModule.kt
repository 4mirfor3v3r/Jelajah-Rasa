package com.amier.jelajahrasa.di.core.module

import androidx.lifecycle.ViewModel
import com.amier.jelajahrasa.di.key.ViewModelKey
import com.amier.jelajahrasa.presentation.main.viewmodel.MainViewModel
import com.amier.jelajahrasa.presentation.ui.auth.viewmodel.LoginViewModel
import com.amier.jelajahrasa.presentation.ui.auth.viewmodel.RegisterViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap


@Module
abstract class ViewModelModule {

    @Module
    abstract inner class MainActivity {
        @Binds
        @IntoMap
        @ViewModelKey(MainViewModel::class)
        abstract fun bindMainActivityViewModel(vm: MainViewModel): ViewModel
    }

    @Module
    abstract inner class LoginActivity {
        @Binds
        @IntoMap
        @ViewModelKey(LoginViewModel::class)
        abstract fun bindLoginActivityViewModel(vm: LoginViewModel): ViewModel
    }

    @Module
    abstract class RegisterActivity{
        @Binds
        @IntoMap
        @ViewModelKey(RegisterViewModel::class)
        abstract fun bindRegisterActivityViewModel(vm: RegisterViewModel): ViewModel
    }

}