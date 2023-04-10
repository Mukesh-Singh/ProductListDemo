package com.mukesh.products.demo.ui.screens.products.list

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mukesh.products.demo.data.models.Product
import com.mukesh.products.demo.data.repository.ProductRepository
import com.mukesh.products.demo.data.util.NetworkResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
Created by Mukesh
 **/

@HiltViewModel
class ProductListViewModel @Inject constructor(
    private val productRepository: ProductRepository
): ViewModel() {
    private val _productListState = mutableStateOf<NetworkResult<List<Product>>>(NetworkResult.Loading())
    val productListState: State<NetworkResult<List<Product>>> = _productListState

    init {
        getProductList()
    }


    private fun getProductList() {
        viewModelScope.launch {
            _productListState.value = NetworkResult.Loading()
            _productListState.value =  kotlin.runCatching {productRepository.getProductsList()}.getOrElse { NetworkResult.Error(message = it.message) }

        }
    }

    fun tryAgain() {
        getProductList()
    }

}