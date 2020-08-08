package com.amier.jelajahrasa

import android.content.Context
import androidx.multidex.MultiDex
import androidx.multidex.MultiDexApplication
import com.amier.jelajahrasa.di.core.component.DaggerApplicationComponent
import com.amier.jelajahrasa.external.helper.PrefHelper
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasAndroidInjector
import javax.inject.Inject

class App:MultiDexApplication(), HasAndroidInjector {
    companion object{
        var prefHelper: PrefHelper?=null
    }

    @Inject
    lateinit var activityInjector: DispatchingAndroidInjector<Any>

    override fun androidInjector(): AndroidInjector<Any> {
        return activityInjector
    }

    override fun attachBaseContext(base: Context?) {
        super.attachBaseContext(base)
        MultiDex.install(this)
    }

    override fun onCreate() {
        super.onCreate()
        DaggerApplicationComponent.builder()
            .application(this)
            .build().apply {
                inject(this@App)
            }
        prefHelper = PrefHelper(this)
    }
    fun getPreferences(): PrefHelper? {
        return prefHelper
    }


}