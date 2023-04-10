package com.mukesh.products.demo.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.mukesh.products.demo.R
import com.mukesh.products.demo.ui.theme.ContentColor
import com.mukesh.products.demo.ui.theme.ProductListDemoTheme
import com.mukesh.products.demo.ui.theme.Shapes
import com.mukesh.products.demo.ui.util.Constants
import com.valentinilk.shimmer.shimmer

/**
Created by Mukesh
 **/


@Composable
fun ProductItemShimmer(
    modifier: Modifier = Modifier,
) {
    val shimmerColor = ContentColor.ShimmerColor
    Surface(
        modifier = modifier
            .fillMaxWidth()
            .background(Color.LightGray),
        //shape = RoundedCornerShape(CornerSize(12.dp))
    ) {
        Card(shape = Shapes.medium) {
            Column(
                modifier = modifier.padding(bottom = 8.dp),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Image(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(height = Constants.NEWS_ITEM_IMAGE_HEIGHT.dp)
                        .clip(RoundedCornerShape(8.dp))
                        .background(shimmerColor)
                        .shimmer(),
                    painter = painterResource(id = R.drawable.placeholder),
                    contentDescription = "Place Holder",
                    contentScale = ContentScale.Crop,
                )
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 8.dp, start = 8.dp, end = 8.dp)
                        .height(16.dp)
                        .shimmer()
                        .background(shimmerColor)
                )

                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 8.dp, start = 8.dp, end = 8.dp)
                        .height(24.dp)
                        .shimmer()
                        .background(shimmerColor)
                )
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 8.dp, start = 8.dp, end = 8.dp)
                        .height(16.dp)
                        .shimmer()
                        .background(shimmerColor)
                )

            }
        }
    }

}

@Preview(showBackground = true)
@Composable
fun Preview_ProductProductItemShimmer() {
    ProductListDemoTheme {
        ProductItemShimmer()
    }
}