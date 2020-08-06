package com.acemirr.jelajah_rasa_data.source.remote

import com.acemirr.jelajah_rasa_data.model.BaseResponse
import com.acemirr.jelajah_rasa_data.model.Food
import com.acemirr.jelajah_rasa_data.model.User
import retrofit2.Response
import retrofit2.http.*

interface ApiService {
    //    foods
    @GET("foods/list")
    fun getFoods(
        @Query("page") page: Int,
        @Query("pageSize") pageSize: Int
    ): Response<BaseResponse<List<Food>>>

    //    users
    @FormUrlEncoded
    @POST("users/register")
    fun register(
        @Field("name") name: String,
        @Field("email") email: String,
        @Field("password") password: String
    ): Response<BaseResponse<User>>

    @FormUrlEncoded
    @POST("users/login")
    fun login(
        @Field("email") email: String,
        @Field("password") password: String
    ): Response<BaseResponse<User>>

    @FormUrlEncoded
    @PUT("foods/listAddLikes/{userID}")
    fun addLikes(
        @Path("userID") userID: String?,
        @Field("id") foodID: Int?
    ): Response<BaseResponse<Food>>

    @FormUrlEncoded
    @PUT("foods/listRemoveLikes/{userID}")
    fun removeLikes(
        @Path("userID") userID: String?,
        @Field("id") foodID: Int?
    ): Response<BaseResponse<Food>>
}