package com.amier.jelajahrasa.data.api

class ApiHelper(private val apiService: ApiService) {
    fun login(email:String,password:String) = apiService.login(email,password)
    fun register(name:String,email:String,password:String) = apiService.register(name,email,password)
    fun getFoods() = apiService.getFoods()
    fun addLikes(userId:String?,foodId:Int?) = apiService.addLikes(userId,foodId)
    fun removeLikes(userId:String?,foodId:Int?) = apiService.removeLikes(userId,foodId)
}