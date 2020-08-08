package com.amier.jelajahrasa.datas.repository

import com.amier.jelajahrasa.datas.datasource.auth.AuthDataSource
import com.amier.jelajahrasa.datas.model.BaseResponse
import com.amier.jelajahrasa.datas.model.User
import com.amier.jelajahrasa.domain.repository.AuthRepository
import com.amier.jelajahrasa.external.extensions.logDebug
import com.amier.jelajahrasa.external.events.Resource
import com.amier.jelajahrasa.external.events.Status
import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor(private val local: AuthDataSource.Local, private val remote: AuthDataSource.Remote):AuthRepository {
    override suspend fun performLogin(email: String, password: String): Resource<BaseResponse<User>> {
        val res = remote.performLogin(email, password)
        return when (res.status){
            Status.SUCCESS -> {
                logDebug("STATUS Success")
                res.data?.let {
                    local.saveUserPreferences(it)
                }
                Resource.success(res.data)
            }
            Status.ERROR ->  {
                logDebug("STATUS Error")
                Resource.error(res.message,null)
            }
            else -> {
                logDebug("STATUS LOADING")
                Resource.loading(null)
            }
        }
    }

    override suspend fun performRegister(name: String, email: String, password: String): Resource<BaseResponse<User>> {
        val res = remote.performRegister(name, email, password)
        return when (res.status){
            Status.SUCCESS -> {
                logDebug("STATUS Success")
                res.data?.let { local.saveUserPreferences(it)
                    logDebug("Saved to local $it")
                }
                Resource.success(res.data)
            }
            Status.ERROR ->  {
                logDebug("STATUS Error")
                Resource.error(res.message,null)
            }
            else -> {
                logDebug("STATUS LOADING")
                Resource.loading(null)
            }
        }
    }

}