package com.amier.jelajahrasa.data.model

data class Food(
    var id:Int,
    var name:String,
    var imgUrl:String,
    var time:String,
    var likes:Int,
    var ingredients:List<Ingredient>,
    var fact:Fact,
    var steps:List<Step>
)