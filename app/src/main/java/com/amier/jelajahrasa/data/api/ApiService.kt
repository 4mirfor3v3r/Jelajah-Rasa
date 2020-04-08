package com.amier.jelajahrasa.data.api

import com.amier.jelajahrasa.data.model.HighUser
import com.amier.jelajahrasa.data.model.HighItemMain
import com.amier.jelajahrasa.data.model.HighLikesMain
import io.reactivex.Single
import retrofit2.http.*

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

    @FormUrlEncoded
    @PUT("listAddLikes/{userID}")
    fun addLikes(@Path("userID") userID:String?,
                 @Field("id") foodID:Int? ):Single<HighLikesMain>

    @FormUrlEncoded
    @PUT("listRemoveLikes/{userID}")
    fun removeLikes(@Path("userID") userID:String?,
                    @Field("id") foodID:Int?):Single<HighLikesMain>
}