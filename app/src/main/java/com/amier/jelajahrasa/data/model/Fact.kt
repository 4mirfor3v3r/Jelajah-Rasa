package com.amier.jelajahrasa.data.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Fact(
    var calori:Double,
    var carbohidrat:Double,
    var protein:Double,
    var vitamin:Double
):Parcelable