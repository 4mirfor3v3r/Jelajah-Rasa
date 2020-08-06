package com.amier.jelajahrasa.di.core.module

import android.app.Application
import android.content.Context
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module(includes = [DaggerViewModelFactoryModule::class])
class AppModule {
    @Singleton
    @Provides
    fun provideContext(application: Application): Context = application.applicationContext
}