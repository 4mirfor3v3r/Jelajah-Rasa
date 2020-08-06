package com.acemirr.jelajah_rasa_data.source.local.entity

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.acemirr.jelajah_rasa_data.utils.FactTypeConverter
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity(tableName = "food")
data class LocalFood(
    @PrimaryKey(autoGenerate = false)
    @SerializedName("id")           val id: Int,
    @SerializedName("_id")          val _id: String,
    @SerializedName("name")         val name: String,
    @SerializedName("imgUrl")       val imgUrl: String,
    @SerializedName("likes")        var likes: Int,
    @SerializedName("time")         val time: Int,

    @TypeConverters(FactTypeConverter::class)
    @SerializedName("fact")         val fact: LocalFact,

    @SerializedName("ingredients")  val ingredients: List<String>,
    @SerializedName("steps")        val steps: List<String>,
    @SerializedName("__v")          val v: Int
): Parcelable