package com.mukesh.products.demo.data.repository.datasource.local

import com.mukesh.products.demo.data.models.FavoriteEntity


/**
Created by Mukesh
 **/

interface LocalDataSource {
    suspend fun getFavoriteProductsIdList(): List<FavoriteEntity>
    suspend fun addToFavorite(id: Int)
    suspend fun removeFromFavorite(id: Int)
    suspend fun isFavorite(id: Int): Boolean
}