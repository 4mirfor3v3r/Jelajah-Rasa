package com.amier.jelajahrasa.external.extensions

import android.content.Context
import android.util.Log
import android.widget.Toast
import com.amier.jelajahrasa.BuildConfig
import com.amier.jelajahrasa.external.Constants

fun logDebug(message: String) {
    if (BuildConfig.DEBUG) Log.d(Constants.TAG_DEBUG, message)
}

fun logError(message: String) {
    if (BuildConfig.DEBUG) Log.e(Constants.TAG_ERROR, message)
}

fun Context.showToast(message: String){
    Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
}