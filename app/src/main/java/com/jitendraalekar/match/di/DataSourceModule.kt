package com.jitendraalekar.match.di

import com.jitendraalekar.match.data.source.local.LocalDataSource
import com.jitendraalekar.match.data.source.local.LocalDataSourceImpl
import com.jitendraalekar.match.data.source.remote.RemoteDataSource
import com.jitendraalekar.match.data.source.remote.RemoteDataSourceImpl
import com.jitendraalekar.match.data.source.repository.Repository
import com.jitendraalekar.match.data.source.repository.RepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class DataSourceModule {

    @Binds
    abstract fun bindRepository(repository: RepositoryImpl): Repository

    @Binds
    abstract fun bindLocalDataSource(localDataSource: LocalDataSourceImpl): LocalDataSource

    @Binds
    abstract fun bindRemoteDataSource(remoteDataSource: RemoteDataSourceImpl): RemoteDataSource
}