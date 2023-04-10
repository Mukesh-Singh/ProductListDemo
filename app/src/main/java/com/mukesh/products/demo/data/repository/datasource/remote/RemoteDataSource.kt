package com.mukesh.products.demo.data.repository.datasource.remote

import com.mukesh.products.demo.data.models.Product
import retrofit2.Response

/**
Created by Mukesh
 **/

interface RemoteDataSource {
    suspend fun getProducts(): Response<List<Product>>
    suspend fun getProductDetails(id: Int): Response<Product>

}