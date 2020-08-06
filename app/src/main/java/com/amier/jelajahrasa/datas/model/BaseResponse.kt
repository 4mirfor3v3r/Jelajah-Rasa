package com.amier.jelajahrasa.datas.model

import com.google.gson.annotations.SerializedName

//sealed class Response<T>{
//    data class BaseResponse<T>(
//        val status: String?,
//        val msg: String?,
//        var response: T?
//    )
//}
data class BaseResponse<MODEL>(
    @SerializedName("status") val status: String?,
    @SerializedName("msg") val msg: String?,
    @SerializedName("response") var response: MODEL?
)