package com.mukesh.products.demo.data.repository.datasource.remote.interceptors

import android.util.Log
import com.mukesh.products.demo.data.repository.datasource.remote.exceptions.APIConnectivityException
import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response

/**
Created by Mukesh
 **/

class RequestInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val builder: Request.Builder = chain.request().newBuilder()
        try {
            return chain.proceed(builder.build())
        } catch (e: Exception) {
            Log.e("Interceptor", e.message+"")
            e.printStackTrace()
            throw APIConnectivityException("Api service connection error!", e)
        }
    }
}