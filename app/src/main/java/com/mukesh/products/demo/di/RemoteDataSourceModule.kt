package com.mukesh.products.demo.di

import com.mukesh.products.demo.data.repository.datasource.local.LocalDataSource
import com.mukesh.products.demo.data.repository.datasource.local.LocalDataSourceImpl
import com.mukesh.products.demo.data.repository.datasource.local.db.AppDatabase
import com.mukesh.products.demo.data.repository.datasource.remote.RemoteDataSource
import com.mukesh.products.demo.data.repository.datasource.remote.RemoteDataSourceImpl
import com.mukesh.products.demo.data.repository.datasource.remote.api.ProductApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

/**
Created by Mukesh
 **/

@Module
@InstallIn(SingletonComponent::class)
object RemoteDataSourceModule {
    @Provides
    @Singleton
    fun provideRemoteDataSource(productApiService: ProductApiService): RemoteDataSource {
        return RemoteDataSourceImpl(productApiService)
    }
}