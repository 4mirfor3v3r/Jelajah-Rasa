package com.amier.jelajahrasa.di.core.module

import android.app.Application
import com.amier.jelajahrasa.App
import com.amier.jelajahrasa.presentation.main.view.MainActivity
import com.amier.jelajahrasa.presentation.ui.auth.view.LoginActivity
import com.amier.jelajahrasa.presentation.ui.auth.view.RegisterActivity
import dagger.Binds
import dagger.Module
import dagger.android.ContributesAndroidInjector
import dagger.android.support.AndroidSupportInjectionModule

@Module(includes = [AndroidSupportInjectionModule::class])
interface ActivityModule {

    @ContributesAndroidInjector(modules = [ViewModelModule.MainActivity::class])
     fun mainActivityInjector(): MainActivity

    @ContributesAndroidInjector(modules = [ViewModelModule.LoginActivity::class])
    fun loginActivityInjector(): LoginActivity

    @ContributesAndroidInjector(modules = [ViewModelModule.RegisterActivity::class])
    fun registerActivityInjector(): RegisterActivity

    @Binds
     fun bindApplication(app:App): Application
}