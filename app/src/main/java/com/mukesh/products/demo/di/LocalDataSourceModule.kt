package com.mukesh.products.demo.di

import android.content.Context
import androidx.room.Room
import com.mukesh.products.demo.data.repository.datasource.local.LocalDataSource
import com.mukesh.products.demo.data.repository.datasource.local.LocalDataSourceImpl
import com.mukesh.products.demo.data.repository.datasource.local.db.AppDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

/**
Created by Mukesh
 **/

@Module
@InstallIn(SingletonComponent::class)
object LocalDataSourceModule {

    @Provides
    @Singleton
    fun provideAppDataBase(@ApplicationContext appContext: Context): AppDatabase{
       return Room.databaseBuilder(
            appContext,
            AppDatabase::class.java, AppDatabase.DATABASE_NAME
        ).fallbackToDestructiveMigration()
           .build()
    }

    @Provides
    @Singleton
    fun provideLocalDataSource(appDatabase: AppDatabase): LocalDataSource {
        return LocalDataSourceImpl(appDatabase)
    }
}