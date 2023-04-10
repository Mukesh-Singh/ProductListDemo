package com.mukesh.products.demo.ui.screens.products.details

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mukesh.products.demo.data.models.Product
import com.mukesh.products.demo.data.repository.ProductRepository
import com.mukesh.products.demo.data.util.NetworkResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.*
import javax.inject.Inject

/**
Created by Mukesh
 **/

@HiltViewModel
class ProductDetailsViewModel @Inject constructor(
    private val productRepository: ProductRepository,

): ViewModel() {
    private val _productDetailState = mutableStateOf<NetworkResult<Product?>>(NetworkResult.Loading())
    val productDetailState: State<NetworkResult<Product?>> = _productDetailState
    private val _isFavorite = mutableStateOf<Boolean>(false)
    val isFavorite: State<Boolean> = _isFavorite
    private var productId: String? = null

    private fun getProductDetails() {
        viewModelScope.launch {
            productId?.let {
                _productDetailState.value = NetworkResult.Loading()
                _productDetailState.value =  kotlin.runCatching {productRepository.getProductDetails(productId = it.toInt())}.getOrElse { NetworkResult.Error(message = it.message) }
            }

        }
    }

    fun tryAgain() {
        getProductDetails()
        isFavorite()
    }

    fun setProductId(productIdRem: String) {
        if (productIdRem!= productId){
            productId = productIdRem
            getProductDetails()
            isFavorite()
        }
    }

    private fun isFavorite() {
        CoroutineScope(Dispatchers.IO).launch {
            productId?.let {
                val  fav = productRepository.isFavorite(it.toInt())
                withContext(Dispatchers.Main) {
                    _isFavorite.value = fav
                }
            }

        }
    }


    private fun addToMyFavorite(){
        CoroutineScope(Dispatchers.IO).launch {
            productId?.let {
                productRepository.addToFavorite(it.toInt())
            }
        }
    }

    private fun removeFromFavorite(){
        CoroutineScope(Dispatchers.IO).launch {
            productId?.let {
                productRepository.removeFromFavorite(it.toInt())
            }
        }
    }

    fun toggleFavorite() {
        if (isFavorite.value) removeFromFavorite() else addToMyFavorite()
        _isFavorite.value = !_isFavorite.value
    }
}