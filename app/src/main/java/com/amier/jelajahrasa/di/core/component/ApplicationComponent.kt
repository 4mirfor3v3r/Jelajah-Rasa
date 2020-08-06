package com.amier.jelajahrasa.di.core.component

import com.amier.jelajahrasa.App
import com.amier.jelajahrasa.di.core.module.ActivityModule
import com.amier.jelajahrasa.di.core.module.AppModule
import com.amier.jelajahrasa.di.core.module.DataModule
import com.amier.jelajahrasa.di.core.module.DatabaseModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import dagger.android.AndroidInjector
import dagger.android.DaggerApplication
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        AndroidInjectionModule::class,
        AppModule::class,
        ActivityModule::class,
        DatabaseModule::class,
        DataModule::class
    ]
)
interface ApplicationComponent : AndroidInjector<DaggerApplication> {

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun application(application: App):Builder
        fun build(): ApplicationComponent
    }

    override fun inject(instance: DaggerApplication?)

    fun inject(application: App)
}