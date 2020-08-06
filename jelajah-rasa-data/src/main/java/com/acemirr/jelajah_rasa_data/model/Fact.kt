package com.acemirr.jelajah_rasa_data.model


import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Fact(
    @SerializedName("calori")       val calori: Int,
    @SerializedName("carbohidrat")  val carbohidrat: Int,
    @SerializedName("protein")      val protein: Int,
    @SerializedName("vitamin")      val vitamin: Int
):Parcelable