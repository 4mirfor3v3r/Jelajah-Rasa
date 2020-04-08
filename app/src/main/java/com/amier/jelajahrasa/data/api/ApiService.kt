package com.amier.jelajahrasa.data.api

import com.amier.jelajahrasa.data.model.HighUser
import com.amier.jelajahrasa.data.model.HighItemMain
import io.reactivex.Single
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST

interface ApiService {
//    foods
    @GET("foods/list")
    fun getFoods():Single<HighItemMain>
//    users
    @FormUrlEncoded
    @POST("users/register")
    fun register(@Field("name") name:String,
                 @Field("email") email:String,
                 @Field("password") password:String):Single<HighUser>
    @FormUrlEncoded
    @POST("users/login")
    fun login(@Field("email") email:String,
              @Field("password") password:String):Single<HighUser>
}