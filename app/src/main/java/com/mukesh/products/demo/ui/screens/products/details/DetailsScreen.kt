package com.mukesh.products.demo.ui.screens.products.details

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.ExperimentalUnitApi
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.TextUnitType
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import com.mukesh.products.demo.R
import com.mukesh.products.demo.data.models.Product
import com.mukesh.products.demo.data.util.NetworkResult
import com.mukesh.products.demo.ui.components.AppHeader
import com.mukesh.products.demo.ui.components.ErrorView
import com.mukesh.products.demo.ui.components.RatingBar
import com.mukesh.products.demo.ui.theme.ProductListDemoTheme

/**
Created by Mukesh
 **/

@Composable
fun DetailsScreen(
    modifier: Modifier = Modifier,
    productId: String,
    navController: NavController? = null,
    detailsViewModel: ProductDetailsViewModel = hiltViewModel()
) {
    val isFavorite by remember { mutableStateOf(detailsViewModel.isFavorite) }
    val productIdRem by remember { mutableStateOf(productId) }
    detailsViewModel.setProductId(productIdRem)
    val product by remember {
        detailsViewModel.productDetailState
    }
    Scaffold(modifier = modifier,
        topBar = {
            AppHeader(
                showBack = true,
                title = stringResource(id = R.string.details),
                navController = navController,
            )
        },
        floatingActionButton = {
            favoriteFloatingButton(isFavorite.value){
                detailsViewModel.toggleFavorite()
            }
        },

    ) { innerPadding ->
        Surface(
            modifier =
            Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            when (product) {
                is NetworkResult.Loading -> {
                    Column(
                        modifier = Modifier.fillMaxSize(),
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        CircularProgressIndicator()
                    }

                }
                is NetworkResult.Success -> {
                    product.data?.let {
                        ProductDetailsContent(product = it)
                    }

                }
                is NetworkResult.Error -> {
                    Surface(
                        modifier
                            .fillMaxWidth()
                            .fillMaxHeight()
                    ) {
                        ErrorView(
                            message = product.message,
                            onTryAgainClicked = {
                                detailsViewModel.tryAgain()
                            }
                        )
                    }
                }
            }
        }
    }
}

@OptIn(ExperimentalUnitApi::class)
@Composable
fun ProductDetailsContent(
    product: Product
) {
    var hasErrorInImageLoading by remember { mutableStateOf(false) }
    if (product.image.isNullOrBlank()) {
        hasErrorInImageLoading = true
    }
    val tagStyle : SpanStyle = MaterialTheme.typography.overline.toSpanStyle().copy(
    background = MaterialTheme.colors.secondary.copy(alpha = 1.0f),
    color = MaterialTheme.colors.onSecondary.copy(
        alpha = 1.0f
    )
    )
    val categoryTextWithStyle = buildAnnotatedString {
        product.category.let {nonNullSourceName ->
            withStyle(tagStyle) {
                append(" $nonNullSourceName ")
            }
        }
    }
    LazyColumn(
        modifier = Modifier.padding(horizontal = 16.dp),
        contentPadding = PaddingValues(vertical = 16.dp)
    ) {
        item { 
            Column() {
                if (!hasErrorInImageLoading) {
                    Image(
                        modifier = Modifier
                            .fillMaxWidth()
                            .clip(RoundedCornerShape(8.dp)),
                        painter = rememberAsyncImagePainter(
                            model = product.image,
                            placeholder = painterResource(id = R.drawable.placeholder),
                            onError = {
                                hasErrorInImageLoading = true
                            }
                        ),
                        contentDescription = "Place Holder",
                        contentScale = ContentScale.Crop,
                    )
                }

                Text(
                    modifier = Modifier
                        .padding(top = 8.dp)
                        .fillMaxWidth(),
                    text = product.title ?: "",
                    style = MaterialTheme.typography.subtitle1.copy(
                        fontSize = TextUnit(18f, TextUnitType.Sp),
                        fontWeight = FontWeight.Bold

                    )
                )
                Text(
                    modifier = Modifier
                        .padding(top = 8.dp),
                    text = categoryTextWithStyle,
                )
                product.rating?.rate?.let {
                    Row(
                        modifier = Modifier.padding(top = 8.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        RatingBar(
                            rating = it
                        )
                        Text(
                            modifier = Modifier
                                .padding(start = 4.dp, end = 4.dp),
                            text = "(${product.rating?.count ?: ""})",
                        )
                    }
                }
                Text(
                    modifier = Modifier
                        .padding(top = 8.dp)
                        .fillMaxWidth(),
                    text = stringResource(id = R.string.price, (product.price ?: 0).toString()),
                    style = MaterialTheme.typography.h4
                )
                Text(
                    modifier = Modifier
                        .padding(top = 8.dp)
                        .fillMaxWidth(),
                    text = product.description ?: "",
                    style = MaterialTheme.typography.subtitle2
                )
            }
        }

    }
}

@Composable
fun favoriteFloatingButton(
    isFavorite: Boolean,
    onFavClicked: (() ->Unit)?= null,
) {
    FloatingActionButton(
        onClick = {
            Log.d("click", "Favorite icon Clicked")
            onFavClicked?.invoke()
        },
        backgroundColor = MaterialTheme.colors.secondary,
        content = {
            Icon(
                painter = if(isFavorite)painterResource(id = R.drawable.baseline_favorite_24) else painterResource(id = R.drawable.baseline_favorite_border_24),
                contentDescription = null,
                tint = MaterialTheme.colors.primary

            )
        }
    )
}

@Preview(showBackground = true, widthDp = 480)
@Composable
fun Preview_ProductDetailsContent() {
    ProductListDemoTheme {
        ProductDetailsContent(product = Product.getSampleItem())
    }
}

@Preview(showBackground = true, widthDp = 480)
@Composable
fun Preview_ProductScreen() {
    ProductListDemoTheme {
        DetailsScreen(productId = "1")
    }
}


