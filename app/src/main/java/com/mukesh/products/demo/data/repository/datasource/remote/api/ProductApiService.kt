package com.mukesh.products.demo.data.repository.datasource.remote.api

import com.mukesh.products.demo.data.models.Product
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

/**
Created by Mukesh
 **/

interface ProductApiService {
    @GET("products")
    suspend fun getProducts(): Response<List<Product>>

    @GET("products/{id}")
    suspend fun getProductDetails(@Path(value="id") id: Int): Response<Product>


}