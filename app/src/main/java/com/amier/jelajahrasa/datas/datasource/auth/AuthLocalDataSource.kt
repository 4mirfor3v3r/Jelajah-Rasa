package com.amier.jelajahrasa.datas.datasource.auth

import com.amier.jelajahrasa.datas.model.BaseResponse
import com.amier.jelajahrasa.datas.model.User
import com.amier.jelajahrasa.external.extensions.logDebug
import com.amier.jelajahrasa.external.Constants
import com.amier.jelajahrasa.external.helper.PrefHelper
import javax.inject.Inject

class AuthLocalDataSource @Inject constructor(private val helper: PrefHelper?) : AuthDataSource.Local {
    override suspend fun saveUserPreferences(user: BaseResponse<User>) {
        if (user.status == "ok") {
            logDebug("Saved to local $user")
            user.response?._id?.let {
                helper?.setString(Constants.USER_ID, it)
            }
            user.response?.likedFoodId?.let {
                helper?.setArray(Constants.LIKED_FOODS_ARRAY, it)
            }
            helper?.setBoolean(Constants.IS_LOGIN,true)
        }
        else{
            logDebug("Failed save to local $user")
        }
    }
}