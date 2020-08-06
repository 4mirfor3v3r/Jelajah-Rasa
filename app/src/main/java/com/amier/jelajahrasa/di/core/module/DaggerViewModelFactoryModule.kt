package com.amier.jelajahrasa.di.core.module

import androidx.lifecycle.ViewModelProvider
import com.amier.jelajahrasa.presentation.base.DaggerViewModelFactory
import dagger.Binds
import dagger.Module

@Module
abstract class DaggerViewModelFactoryModule {
    @Binds
    abstract fun bindViewModelFactory(viewModelFactory: DaggerViewModelFactory): ViewModelProvider.Factory
}