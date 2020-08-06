package com.amier.jelajahrasa.data.model

//sealed class Response<T>{
//    data class BaseResponse<T>(
//        val status: String?,
//        val msg: String?,
//        var response: T?
//    )
//}
data class BaseResponse<MODEL>(
    val status: String?,
    val msg: String?,
    var response: MODEL?
)