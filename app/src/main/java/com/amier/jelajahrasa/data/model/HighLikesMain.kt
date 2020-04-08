package com.amier.jelajahrasa.data.model


import com.google.gson.annotations.SerializedName

data class HighLikesMain(
    @SerializedName("food")    val food: Food,
    @SerializedName("msg")      val msg: String?,
    @SerializedName("status")   val status: String
)