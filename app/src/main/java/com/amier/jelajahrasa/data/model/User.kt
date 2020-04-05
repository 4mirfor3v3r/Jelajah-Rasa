package com.amier.jelajahrasa.data.model

data class User(
    var _id:String,
    var name:String,
    var email:String,
    var password:String,
    var likedFoodId:List<Int>,
    var _likedFoodIdSignature:String
)