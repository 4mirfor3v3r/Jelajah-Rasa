package com.amier.jelajahrasa.data.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Food(
    var id:Int,
    var name:String,
    var imgUrl:String,
    var time:String,
    var likes:Int,
    var ingredients:List<Ingredient>,
    var fact:Fact,
    var steps:List<Step>
):Parcelable