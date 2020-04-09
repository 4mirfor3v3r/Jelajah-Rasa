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
    fun setArray(key:String, array:ArrayList<Int>){
        prefHelper.edit().putString(key, array.toString()).apply()
    }
    fun getArray(key: String):ArrayList<Int> {
        val str = prefHelper.getString(key,"")
        return if (str != null) {
            arrayStringToIntegerArrayList(str)
        }else{
            arrayListOf()
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