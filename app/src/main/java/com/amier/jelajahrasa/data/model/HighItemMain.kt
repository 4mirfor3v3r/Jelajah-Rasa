package com.amier.jelajahrasa.data.model


import com.google.gson.annotations.SerializedName

data class HighItemMain(
    @SerializedName("foods")    val foods: List<Food>,
    @SerializedName("msg")      val msg: String?,
    @SerializedName("status")   val status: String
)