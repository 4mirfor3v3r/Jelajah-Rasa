package com.amier.jelajahrasa.datas.datasource.detail

import com.amier.jelajahrasa.datas.source.local.dao.HomeDao
import javax.inject.Inject

class DetailLocalDataSource @Inject constructor(private val dao: HomeDao) : DetailDataSource.Local {
}