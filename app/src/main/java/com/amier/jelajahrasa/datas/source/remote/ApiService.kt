package com.amier.jelajahrasa.datas.source.remote

import com.amier.jelajahrasa.datas.model.BaseResponse
import com.amier.jelajahrasa.datas.model.Food
import com.amier.jelajahrasa.datas.model.PagedFoods
import com.amier.jelajahrasa.datas.model.User
import retrofit2.Response
import retrofit2.http.*

interface ApiService {
    //    foods
    @GET("foods/list")
    suspend fun getFoods(
        @Query("page") page: Int,
        @Query("pageSize") pageSize: Int
    ): Response<BaseResponse<PagedFoods>>

    //    users
    @FormUrlEncoded
    @POST("users/register")
    suspend fun register(
        @Field("name") name: String,
        @Field("email") email: String,
        @Field("password") password: String
    ): Response<BaseResponse<User>>

    @FormUrlEncoded
    @POST("users/login")
    suspend fun login(
        @Field("email") email: String,
        @Field("password") password: String
    ): Response<BaseResponse<User>>

    @FormUrlEncoded
    @PUT("foods/listAddLikes/{userID}")
    suspend fun addLikes(
        @Path("userID") userID: String?,
        @Field("id") foodID: Int?
    ): Response<BaseResponse<Food>>

    @FormUrlEncoded
    @PUT("foods/listRemoveLikes/{userID}")
    suspend fun removeLikes(
        @Path("userID") userID: String?,
        @Field("id") foodID: Int?
    ): Response<BaseResponse<Food>>
}