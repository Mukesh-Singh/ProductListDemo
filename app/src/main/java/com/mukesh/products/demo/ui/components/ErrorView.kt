package com.mukesh.products.demo.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.mukesh.products.demo.R
import com.mukesh.products.demo.data.models.Product
import com.mukesh.products.demo.ui.theme.ProductListDemoTheme

/**
Created by Mukesh
 **/

@Composable
fun ErrorView(
    modifier: Modifier = Modifier,
    message: String? = null,
    onTryAgainClicked: (() -> Unit)? = null,
) {
    Column(
        modifier = modifier.padding(bottom = 8.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            modifier = Modifier
                .padding(top = 8.dp, start = 8.dp, end = 8.dp)
                .fillMaxWidth(),
            text = message?: stringResource(id = R.string.something_went_wrong_try_again),
            textAlign = TextAlign.Center,
            maxLines = 2,
            overflow = TextOverflow.Ellipsis,

            )
        Button(onClick = {
            onTryAgainClicked?.invoke()
        }) {
            Text(text = "Try Again")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun Preview_ErrorView() {
    ProductListDemoTheme {
        ErrorView()
    }
}