package com.amier.jelajahrasa

import android.content.Context
import androidx.multidex.MultiDex
import androidx.multidex.MultiDexApplication
import com.amier.jelajahrasa.utils.PrefHelper

class App:MultiDexApplication() {
    companion object{
        var prefHelper:PrefHelper?=null
    }

    override fun attachBaseContext(base: Context?) {
        super.attachBaseContext(base)
        MultiDex.install(this)
    }

    override fun onCreate() {
        super.onCreate()
        prefHelper =PrefHelper(this)
    }
}