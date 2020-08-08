package com.amier.jelajahrasa.di.core.module

import com.amier.jelajahrasa.App
import com.amier.jelajahrasa.datas.datasource.auth.AuthDataSource
import com.amier.jelajahrasa.datas.datasource.auth.AuthLocalDataSource
import com.amier.jelajahrasa.datas.datasource.auth.AuthRemoteDataSource
import com.amier.jelajahrasa.datas.datasource.detail.DetailDataSource
import com.amier.jelajahrasa.datas.datasource.detail.DetailLocalDataSource
import com.amier.jelajahrasa.datas.datasource.detail.DetailRemoteDataSource
import com.amier.jelajahrasa.datas.datasource.main.MainDataSource
import com.amier.jelajahrasa.datas.datasource.main.MainLocalDataSource
import com.amier.jelajahrasa.datas.datasource.main.MainRemoteDataSource
import com.amier.jelajahrasa.datas.repository.AuthRepositoryImpl
import com.amier.jelajahrasa.datas.repository.DetailRepositoryImpl
import com.amier.jelajahrasa.datas.repository.MainRepositoryImpl
import com.amier.jelajahrasa.datas.source.local.dao.HomeDao
import com.amier.jelajahrasa.datas.source.remote.ApiServiceImpl
import com.amier.jelajahrasa.domain.repository.AuthRepository
import com.amier.jelajahrasa.domain.repository.DetailRepository
import com.amier.jelajahrasa.domain.repository.MainRepository
import com.amier.jelajahrasa.domain.usecase.AuthUseCase
import com.amier.jelajahrasa.domain.usecase.DetailUseCase
import com.amier.jelajahrasa.domain.usecase.MainUseCase
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class DataModule {

    @Provides
    @Singleton
    fun provideApiServiceImpl(): ApiServiceImpl {
        return ApiServiceImpl()
    }

    @Provides
    @Singleton
    fun provideAuthRepository(remote: AuthDataSource.Remote, local: AuthDataSource.Local): AuthRepository {
        return AuthRepositoryImpl(local, remote)
    }

    @Provides
    @Singleton
    fun provideAuthLocalDataSource(application: App): AuthDataSource.Local {
        return AuthLocalDataSource(application.getPreferences())
    }

    @Provides
    @Singleton
    fun provideAuthRemoteDataSource(api: ApiServiceImpl): AuthDataSource.Remote {
        return AuthRemoteDataSource(api)
    }

    @Provides
    @Singleton
    fun provideGetAuthUseCase(repository: AuthRepository): AuthUseCase {
        return AuthUseCase(repository)
    }

    @Provides
    @Singleton
    fun provideMainRepository(remote: MainDataSource.Remote, local: MainDataSource.Local): MainRepository {
        return MainRepositoryImpl(local, remote)
    }

    @Provides
    @Singleton
    fun provideMainLocalDataSource(dao: HomeDao): MainDataSource.Local {
        return MainLocalDataSource(dao)
    }

    @Provides
    @Singleton
    fun provideMainRemoteDataSource(api: ApiServiceImpl): MainDataSource.Remote {
        return MainRemoteDataSource(api)
    }
    @Provides
    @Singleton
    fun provideGetMainUseCase(repository: MainRepository): MainUseCase {
        return MainUseCase(repository)
    }



    @Provides
    @Singleton
    fun provideDetailRepository(remote: DetailDataSource.Remote, local: DetailDataSource.Local): DetailRepository {
        return DetailRepositoryImpl(local, remote)
    }

    @Provides
    @Singleton
    fun provideDetailLocalDataSource(dao: HomeDao): DetailDataSource.Local {
        return DetailLocalDataSource(dao)
    }

    @Provides
    @Singleton
    fun provideDetailRemoteDataSource(api: ApiServiceImpl): DetailDataSource.Remote {
        return DetailRemoteDataSource(api)
    }
    @Provides
    @Singleton
    fun provideGetDetailUseCase(repository: DetailRepository): DetailUseCase {
        return DetailUseCase(repository)
    }
}