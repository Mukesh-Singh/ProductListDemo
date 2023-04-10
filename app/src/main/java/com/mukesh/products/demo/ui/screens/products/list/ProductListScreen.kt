package com.mukesh.products.demo.ui.screens.products.list

import android.util.Log
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.mukesh.products.demo.data.util.NetworkResult
import com.mukesh.products.demo.ui.components.AppHeader
import com.mukesh.products.demo.ui.components.ProductItem
import com.mukesh.products.demo.R
import com.mukesh.products.demo.ui.components.ErrorView
import com.mukesh.products.demo.ui.components.ProductItemShimmer
import com.mukesh.products.demo.ui.models.OptionMenuItem
import com.mukesh.products.demo.ui.rout.Rout

/**
Created by Mukesh
 **/

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun ProductListScreen(
    modifier: Modifier = Modifier,
    navController: NavController? = null,
    viewModel: ProductListViewModel = hiltViewModel()
) {
    Scaffold( modifier = modifier,
        topBar = {
            AppHeader(
                title = stringResource(id = R.string.app_name),
                navController = navController,
                optionMenuList = listOf(OptionMenuItem.Favorite),
                onMenuItemSelect ={item, label ->
                    Log.e("Action", "Option item clicked ${label}")
                    navController?.navigate(Rout.FAVORITE.rout)
                }
            )
        }
    ) {innerPadding->
        Surface(
            modifier = Modifier
                .padding(paddingValues = innerPadding)
                .fillMaxSize(),
        ) {
            val products by remember {
                viewModel.productListState
            }
            when (products) {
                is NetworkResult.Loading -> {
                    LazyVerticalGrid(
                        cells = GridCells.Fixed(2),
                        contentPadding = PaddingValues(8.dp),
                        verticalArrangement = Arrangement.spacedBy(16.dp),
                        horizontalArrangement = Arrangement.spacedBy(16.dp)

                    ) {
                        items(10) {
                           ProductItemShimmer()
                        }
                    }
                }
                is NetworkResult.Success -> {
                    LazyVerticalGrid(
                        cells = GridCells.Fixed(2),
                        contentPadding = PaddingValues(8.dp),
                        verticalArrangement = Arrangement.spacedBy(16.dp),
                        horizontalArrangement = Arrangement.spacedBy(16.dp)

                    ) {
                        products.data?.let { list ->
                            items(items = list) {
                                ProductItem(product = it){
                                    navController?.navigate(Rout.PRODUCT_DETAILS.buildUrl(it.id.toString()))
                                }
                            }
                        }
                    }
                }
                is NetworkResult.Error -> {
                    Surface(
                        modifier
                            .fillMaxWidth()
                            .fillMaxHeight()
                    ) {
                        ErrorView(
                            message = products.message,
                            onTryAgainClicked = {
                                viewModel.tryAgain()
                            }
                        )
                    }
                }
            }
        }
    }
}