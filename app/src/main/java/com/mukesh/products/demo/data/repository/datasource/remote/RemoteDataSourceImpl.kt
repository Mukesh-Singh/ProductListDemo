package com.mukesh.products.demo.data.repository.datasource.remote

import com.mukesh.products.demo.data.models.Product
import com.mukesh.products.demo.data.repository.datasource.remote.api.ProductApiService
import retrofit2.Response

/**
Created by Mukesh
 **/

class RemoteDataSourceImpl(private val apis: ProductApiService): RemoteDataSource {
    override suspend fun getProducts(): Response<List<Product>> {
        return apis.getProducts()
    }

    override suspend fun getProductDetails(id: Int): Response<Product> {
        return apis.getProductDetails(id)
    }
}