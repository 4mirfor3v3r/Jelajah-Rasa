package com.amier.jelajahrasa.datas.source.local.entity

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class LocalFact(
    @SerializedName("calori")       val calori: Int,
    @SerializedName("carbohidrat")  val carbohidrat: Int,
    @SerializedName("protein")      val protein: Int,
    @SerializedName("vitamin")      val vitamin: Int
):Parcelable