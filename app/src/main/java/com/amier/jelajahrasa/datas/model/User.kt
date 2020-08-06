package com.amier.jelajahrasa.datas.model

import com.google.gson.annotations.SerializedName

data class User(
    @SerializedName("_id")
    var _id:String,
    @SerializedName("name")
    var name:String,
    @SerializedName("email")
    var email:String,
    @SerializedName("password")
    var password:String,
    @SerializedName("likedFoodId")
    var likedFoodId:ArrayList<Int>
)