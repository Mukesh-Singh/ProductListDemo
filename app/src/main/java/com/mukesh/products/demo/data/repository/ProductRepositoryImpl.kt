package com.mukesh.products.demo.data.repository

import android.util.Log
import com.mukesh.products.demo.data.models.Product
import com.mukesh.products.demo.data.repository.datasource.local.LocalDataSource
import com.mukesh.products.demo.data.repository.datasource.remote.RemoteDataSource
import com.mukesh.products.demo.data.util.NetworkResult
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

/**
Created by Mukesh
 **/

class ProductRepositoryImpl(
    private val localDataSource: LocalDataSource,
    private val remoteDataSource: RemoteDataSource): ProductRepository {
    override suspend fun getProductsList(): NetworkResult<List<Product>>  = withContext(Dispatchers.IO) {
        val response = remoteDataSource.getProducts()
        try {
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

    override suspend fun getProductDetails(productId: Int): NetworkResult<Product?> = withContext(Dispatchers.IO) {
        val response = remoteDataSource.getProductDetails(productId)
        try {
            if (response.isSuccessful){
                NetworkResult.Success(response.body())
            }else{
                NetworkResult.Error(response.message(),null)
            }
        }catch (e: Exception){
            NetworkResult.Error(e.message,null)
        }
    }

    override suspend fun getFavoriteProductsList(): NetworkResult<List<Product>> = withContext(Dispatchers.IO){
        val response = remoteDataSource.getProducts()
        try {
            if (!response.isSuccessful || response.body().isNullOrEmpty()){
                NetworkResult.Success(emptyList())
            }else{
                val favIds = localDataSource.getFavoriteProductsIdList().map { it.productId }
                if (favIds.isEmpty()){
                    NetworkResult.Success(emptyList<Product>())
                }
                val listOfFavProduct = response.body()?.filter { favIds.contains(it.id?.toString()) }
                NetworkResult.Success(listOfFavProduct)

            }

        }catch (e: Exception){
            NetworkResult.Error(e.message,null)
        }
    }

    override suspend fun addToFavorite(id: Int) = withContext(Dispatchers.IO) {
        localDataSource.addToFavorite(id)
    }

    override suspend fun removeFromFavorite(id: Int) = withContext(Dispatchers.IO){
        localDataSource.removeFromFavorite(id)
    }

    override suspend fun isFavorite(id: Int): Boolean = withContext(Dispatchers.IO){
        localDataSource.isFavorite(id)
    }
}