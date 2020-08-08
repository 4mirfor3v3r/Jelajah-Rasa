package com.amier.jelajahrasa.datas.repository

import com.amier.jelajahrasa.datas.datasource.detail.DetailDataSource
import com.amier.jelajahrasa.domain.repository.DetailRepository
import javax.inject.Inject

class DetailRepositoryImpl @Inject constructor(private val local: DetailDataSource.Local, private val remote: DetailDataSource.Remote):DetailRepository {

}