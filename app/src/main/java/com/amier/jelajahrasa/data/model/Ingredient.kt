package com.amier.jelajahrasa.data.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Ingredient(
    var id:Int,
    var value:String
):Parcelable