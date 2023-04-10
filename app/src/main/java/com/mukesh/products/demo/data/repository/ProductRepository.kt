package com.mukesh.products.demo.data.repository

import com.mukesh.products.demo.data.models.Product
import com.mukesh.products.demo.data.util.NetworkResult

/**
Created by Mukesh
 **/

interface ProductRepository {
    suspend fun getProductsList(): NetworkResult<List<Product>>
    suspend fun getProductDetails(productId: Int): NetworkResult<Product?>
    suspend fun getFavoriteProductsList(): NetworkResult<List<Product>>
    suspend fun addToFavorite(id: Int)
    suspend fun removeFromFavorite(id: Int)
    suspend fun isFavorite(id: Int): Boolean
}