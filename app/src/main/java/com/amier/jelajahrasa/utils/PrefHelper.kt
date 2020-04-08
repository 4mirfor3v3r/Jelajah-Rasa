package com.amier.jelajahrasa.utils

import android.content.Context
import android.content.SharedPreferences

class PrefHelper(context: Context) {

    private val prefHelper: SharedPreferences = context.getSharedPreferences(Constants.PREFERENCE_NAME, Context.MODE_PRIVATE)

    fun setString(key: String, value: String){
        prefHelper.edit().putString(key, value).apply()
    }

    fun getString(key: String): String{
        return prefHelper.getString(key, "")!!
    }
}