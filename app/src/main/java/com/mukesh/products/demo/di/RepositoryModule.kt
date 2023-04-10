package com.mukesh.products.demo.di

import com.mukesh.products.demo.data.repository.ProductRepository
import com.mukesh.products.demo.data.repository.ProductRepositoryImpl
import com.mukesh.products.demo.data.repository.datasource.local.LocalDataSource
import com.mukesh.products.demo.data.repository.datasource.remote.RemoteDataSource
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
object RepositoryModule {
    @Provides
    @Singleton
    fun provideProductRepository(localDataSource: LocalDataSource,remoteDataSource: RemoteDataSource): ProductRepository {
        return ProductRepositoryImpl(localDataSource,remoteDataSource)
    }
}