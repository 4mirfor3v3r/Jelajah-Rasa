package com.amier.jelajahrasa.data.api

class ApiHelper(private val apiService: ApiService) {
    fun login(email:String,password:String) = apiService.login(email,password)
    fun register(name:String,email:String,password:String) = apiService.register(name,email,password)
}