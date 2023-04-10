package com.mukesh.products.demo.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.TextUnitType
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.mukesh.products.demo.data.models.Product
import com.mukesh.products.demo.ui.theme.ProductListDemoTheme
import com.mukesh.products.demo.ui.util.Constants
import com.mukesh.products.demo.R
import com.mukesh.products.demo.ui.theme.Shapes

/**
Created by Mukesh
 **/

@Composable
fun ProductItem(
    modifier: Modifier = Modifier,
    product: Product,
    onItemClick: ((Product) -> Unit)?= null
) {
    var hasErrorInImageLoading by remember { mutableStateOf(false) }
    if (product.image.isNullOrBlank()) {
        hasErrorInImageLoading = true
    }
    Card(
        modifier = modifier.clickable {
            onItemClick?.invoke(product)
        },
        shape = Shapes.medium,
    ) {
        Column(
            modifier = Modifier.padding(bottom = 8.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            if (!hasErrorInImageLoading) {
                Image(
                    modifier = androidx.compose.ui.Modifier
                        .fillMaxWidth()
                        .height(height = Constants.NEWS_ITEM_IMAGE_HEIGHT.dp)
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
                    .padding(top = 8.dp, start = 8.dp, end = 8.dp)
                    .fillMaxWidth(),
                text = product.title ?: "",
                textAlign = TextAlign.Center,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis,

                )
            product.rating?.rate?.let {
                RatingBar(
                    modifier = Modifier.padding(top = 8.dp),
                    rating = it
                )
                Text(
                    modifier = Modifier
                        .padding(start = 4.dp, end = 4.dp),
                    text = "(${product.rating?.count ?: ""})",
                    textAlign = TextAlign.Center,
                )
            }
            Text(
                modifier = Modifier
                    .padding(top = 8.dp, start = 8.dp, end = 8.dp)
                    .fillMaxWidth(),
                text = stringResource(id = R.string.price, (product.price ?: 0).toString()),
                textAlign = TextAlign.Center,
            )

        }
    }

}

@Preview(showBackground = true)
@Composable
fun Preview_ProductItem() {
    ProductListDemoTheme {
        ProductItem(product = Product.getSampleItem())
    }
}