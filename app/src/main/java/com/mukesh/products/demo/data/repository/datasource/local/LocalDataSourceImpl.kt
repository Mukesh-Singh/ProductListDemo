package com.mukesh.products.demo.data.repository.datasource.local

import com.mukesh.products.demo.data.models.FavoriteEntity
import com.mukesh.products.demo.data.repository.datasource.local.db.AppDatabase

/**
Created by Mukesh
 **/

class LocalDataSourceImpl(private val db: AppDatabase): LocalDataSource {
    override suspend fun getFavoriteProductsIdList(): List<FavoriteEntity> {
        return db.favoriteDao().getAllFavoriteIds()
    }

    override suspend fun addToFavorite(id: Int) {
        db.favoriteDao().insert(FavoriteEntity(id.toString()))
    }

    override suspend fun removeFromFavorite(id: Int) {
        db.favoriteDao().delete(FavoriteEntity(id.toString()))
    }

    override suspend fun isFavorite(id: Int): Boolean {
        val count = db.favoriteDao().getCount(id.toString())
        return (count>0)
    }
}