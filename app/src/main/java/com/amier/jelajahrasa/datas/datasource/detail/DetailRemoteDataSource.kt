package com.amier.jelajahrasa.datas.datasource.detail

import com.amier.jelajahrasa.datas.source.remote.ApiServiceImpl
import javax.inject.Inject

class DetailRemoteDataSource @Inject constructor(private val apiService: ApiServiceImpl) : DetailDataSource.Remote {

}