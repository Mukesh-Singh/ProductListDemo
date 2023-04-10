package com.mukesh.products.demo.data.repository

import android.util.Log
import com.mukesh.products.demo.data.models.Product
import com.mukesh.products.demo.data.repository.datasource.local.LocalDataSource
import com.mukesh.products.demo.data.repository.datasource.remote.RemoteDataSource
import com.mukesh.products.demo.data.util.NetworkResult

/**
Created by Mukesh
 **/

class ProductRepositoryImpl(
    private val localDataSource: LocalDataSource,
    private val remoteDataSource: RemoteDataSource): ProductRepository {
    override suspend fun getProductsList(): NetworkResult<List<Product>> {
        val response = remoteDataSource.getProducts()
        return try {
            if (response.isSuccessful){
                response.body()?.let {
                    NetworkResult.Success(it)
                }?: kotlin.run { NetworkResult.Success(emptyList()) }
            }else{
                NetworkResult.Error(response.message(),null)
            }
        }catch (e: Exception){
            Log.e("ProductRepositoryImpl", e.message+"")
            NetworkResult.Error(e.message,null)
        }

    }

    override suspend fun getProductDetails(productId: Int): NetworkResult<Product?> {
        val response = remoteDataSource.getProductDetails(productId)
        return try {
            if (response.isSuccessful){
                NetworkResult.Success(response.body())
            }else{
                NetworkResult.Error(response.message(),null)
            }
        }catch (e: Exception){
            NetworkResult.Error(e.message,null)
        }
    }

    override suspend fun getFavoriteProductsList(): NetworkResult<List<Product>> {
        val response = remoteDataSource.getProducts()
        try {
            if (!response.isSuccessful || response.body().isNullOrEmpty()){
                return NetworkResult.Success(emptyList())
            }else{
                val favIds = localDataSource.getFavoriteProductsIdList().map { it.productId }
                if (favIds.isEmpty()){
                    return NetworkResult.Success(emptyList())
                }
                val listOfFavProduct = response.body()?.filter { favIds.contains(it.id?.toString()) }
                return NetworkResult.Success(listOfFavProduct)

            }

        }catch (e: Exception){
            return NetworkResult.Error(e.message,null)
        }
    }

    override suspend fun addToFavorite(id: Int) {
        localDataSource.addToFavorite(id)
    }

    override suspend fun removeFromFavorite(id: Int) {
        localDataSource.removeFromFavorite(id)
    }

    override suspend fun isFavorite(id: Int): Boolean {
        return localDataSource.isFavorite(id)
    }
}