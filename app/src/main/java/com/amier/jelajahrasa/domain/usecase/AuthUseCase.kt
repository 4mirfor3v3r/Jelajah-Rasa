package com.amier.jelajahrasa.domain.usecase

import com.amier.jelajahrasa.datas.model.BaseResponse
import com.amier.jelajahrasa.datas.model.User
import com.amier.jelajahrasa.domain.repository.AuthRepository
import com.amier.jelajahrasa.external.events.Resource
import javax.inject.Inject

class AuthUseCase @Inject constructor(private val repository: AuthRepository) {
    suspend fun performLogin(email: String, password: String): Resource<BaseResponse<User>> {
        return repository.performLogin(email, password)
    }

    suspend fun performRegister(name: String, email: String, password: String): Resource<BaseResponse<User>> {
        return repository.performRegister(name, email, password)
    }
}