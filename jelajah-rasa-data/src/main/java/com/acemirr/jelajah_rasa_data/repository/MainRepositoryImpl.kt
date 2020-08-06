package com.acemirr.jelajah_rasa_data.repository

import com.acemirr.jelajah_rasa_data.datasource.main.MainDataSource
import javax.inject.Inject

class MainRepositoryImpl @Inject constructor(private val local:MainDataSource.Local, private val remote: MainDataSource.Remote) {
}