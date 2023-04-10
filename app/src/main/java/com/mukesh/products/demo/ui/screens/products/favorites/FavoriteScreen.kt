package com.mukesh.products.demo.ui.screens.products.favorites

import android.util.Log
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.mukesh.products.demo.R
import com.mukesh.products.demo.data.util.NetworkResult
import com.mukesh.products.demo.ui.components.AppHeader
import com.mukesh.products.demo.ui.components.ErrorView
import com.mukesh.products.demo.ui.components.ProductItem
import com.mukesh.products.demo.ui.components.ProductItemShimmer
import com.mukesh.products.demo.ui.models.OptionMenuItem
import com.mukesh.products.demo.ui.rout.Rout

/**
Created by Mukesh
 **/

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun FavoriteScreen(
    modifier: Modifier = Modifier,
    navController: NavController? = null,
    viewModel: FavoriteViewModel = hiltViewModel()
) {
    LaunchedEffect(Unit) {
        viewModel.getFavoriteList()
    }
    Scaffold( modifier = modifier,
        topBar = {
            AppHeader(
                showBack = true,
                title = stringResource(id = R.string.favorite),
                navController = navController,
            )
        }
    ) {innerPadding->
        Surface(
            modifier = Modifier
                .padding(paddingValues = innerPadding)
                .fillMaxSize(),
        ) {
            val products by remember {
                viewModel.favoriteListState
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
                    if (products.data.isNullOrEmpty()){
                        Column(
                            modifier = modifier.padding(bottom = 8.dp),
                            verticalArrangement = Arrangement.Center,
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Text(
                                modifier = Modifier
                                    .padding(top = 8.dp, start = 8.dp, end = 8.dp)
                                    .fillMaxWidth(),
                                text = stringResource(id = R.string.no_favorite_product),
                                textAlign = TextAlign.Center,
                                maxLines = 2,
                                overflow = TextOverflow.Ellipsis,

                                )
                        }
                    }else{
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