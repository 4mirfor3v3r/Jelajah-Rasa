package com.amier.jelajahrasa.datas.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class PagedFoods(
    @SerializedName("totalPages") var totalPages: Int?,
    @SerializedName("currentPage") var currentPage: Int?,
    @SerializedName("foods") var foods: List<Food>?
):Parcelable