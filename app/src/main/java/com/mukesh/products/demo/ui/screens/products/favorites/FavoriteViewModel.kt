package com.mukesh.products.demo.ui.screens.products.favorites

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
class FavoriteViewModel @Inject constructor(
    private val productRepository: ProductRepository
) : ViewModel() {
    private val _favoriteListState =
        mutableStateOf<NetworkResult<List<Product>>>(NetworkResult.Loading())
    val favoriteListState: State<NetworkResult<List<Product>>> = _favoriteListState


    fun getFavoriteList() {
        viewModelScope.launch {
            _favoriteListState.value = NetworkResult.Loading()
            _favoriteListState.value = kotlin.runCatching { productRepository.getFavoriteProductsList() }
                .getOrElse { NetworkResult.Error(message = it.message) }
        }
    }

    fun tryAgain() {
        getFavoriteList()
    }

}