package com.amier.jelajahrasa.utils

import android.content.Context
import android.content.SharedPreferences
import android.util.Log
import java.lang.NullPointerException

class PrefHelper(context: Context) {

    private val prefHelper: SharedPreferences = context.getSharedPreferences(Constants.PREFERENCE_NAME, Context.MODE_PRIVATE)

    fun setString(key: String, value: String){
        prefHelper.edit().putString(key, value).apply()
    }

    fun getString(key: String): String?{
        return try {
            prefHelper.getString(key, "")!!
        }catch (e:NullPointerException){
            null
        }
    }
    fun setArray(key:String, array:ArrayList<Int>){
        prefHelper.edit().putString(key, array.toString()).apply()
    }
    fun getArray(key: String):ArrayList<Int>? {
        return try {
            val str = prefHelper.getString(key, "")
            if (str != null && str != "" && str != "[]") {
                Log.e("STR",str)
                arrayStringToIntegerArrayList(str)
            } else {
                arrayListOf()
            }
        }catch (e:NullPointerException) {
            null
        }
    }

    private fun arrayStringToIntegerArrayList(stringArray: String): ArrayList<Int> {
        val removedBrackets = stringArray.substring(1, stringArray.length - 1)
        val individualNumbers = removedBrackets.split(",").toTypedArray()
        val integerArrayList: ArrayList<Int> = ArrayList()
        for (numberString in individualNumbers) {
            integerArrayList.add(numberString.trim { it <= ' ' }.toInt())
        }
        return integerArrayList
    }

}