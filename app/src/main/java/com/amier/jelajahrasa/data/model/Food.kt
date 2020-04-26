package com.amier.jelajahrasa.data.model


import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Food(
    @SerializedName("fact")         val fact: Fact,
    @SerializedName("id")           val id: Int,
    @SerializedName("_id")          val _id: String,
    @SerializedName("imgUrl")       val imgUrl: String,
    @SerializedName("ingredients")  val ingredients: List<String>,
    @SerializedName("likes")        var likes: Int,
    @SerializedName("name")         val name: String,
    @SerializedName("steps")        val steps: List<String>,
    @SerializedName("time")         val time: Int,
    @SerializedName("__v")          val v: Int
):Parcelable