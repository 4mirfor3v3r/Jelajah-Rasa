package com.amier.jelajahrasa.di.core.module

import com.amier.jelajahrasa.App
import com.amier.jelajahrasa.datas.datasource.auth.AuthDataSource
import com.amier.jelajahrasa.datas.datasource.auth.AuthLocalDataSource
import com.amier.jelajahrasa.datas.datasource.auth.AuthRemoteDataSource
import com.amier.jelajahrasa.datas.repository.AuthRepositoryImpl
import com.amier.jelajahrasa.datas.source.remote.ApiServiceImpl
import com.amier.jelajahrasa.domain.repository.AuthRepository
import com.amier.jelajahrasa.domain.usecase.AuthUseCase
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
    fun provideGetAuthUseCase(repository: AuthRepository): AuthUseCase {
        return AuthUseCase(repository)
    }
}