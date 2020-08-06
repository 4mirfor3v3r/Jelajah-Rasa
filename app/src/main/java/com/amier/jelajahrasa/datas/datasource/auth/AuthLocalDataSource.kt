package com.amier.jelajahrasa.datas.datasource.auth

import com.amier.jelajahrasa.datas.model.BaseResponse
import com.amier.jelajahrasa.datas.model.Food
import com.amier.jelajahrasa.datas.model.User
import com.amier.jelajahrasa.datas.source.local.entity.LocalFood
import com.amier.jelajahrasa.datas.utils.Mapper
import com.amier.jelajahrasa.utils.Constants
import com.amier.jelajahrasa.utils.PrefHelper
import com.amier.jelajahrasa.utils.Resource
import javax.inject.Inject

class AuthLocalDataSource @Inject constructor(private val helper: PrefHelper?) : AuthDataSource.Local {
    override suspend fun saveUserPreferences(user: BaseResponse<User>) {
        user.response?._id?.let {
            helper?.setString(Constants.USER_ID, it)
        }
        user.response?.likedFoodId?.let { helper?.setArray(Constants.LIKED_FOODS_ARRAY, it) }
    }
}