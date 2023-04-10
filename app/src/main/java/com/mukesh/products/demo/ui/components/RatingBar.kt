package com.mukesh.products.demo.ui.components

import androidx.compose.foundation.layout.Row
import androidx.compose.material.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import java.lang.Math.ceil
import java.lang.Math.floor
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import com.mukesh.products.demo.R

/**
Created by Mukesh
 **/

@Composable
fun RatingBar(
    modifier: Modifier = Modifier,
    rating: Double = 0.0,
    stars: Int = 5,
    starsColor: Color = Color.Red,
) {
    val filledStars = floor(rating).toInt()
    val unfilledStars = (stars - ceil(rating)).toInt()
    val halfStar = !(rating.rem(1).equals(0.0))
    Row(modifier = modifier) {
        repeat(filledStars) {
            Icon(
               painter = painterResource(id = R.drawable.baseline_star_24),
                contentDescription = null,
                tint = starsColor)
        }
        if (halfStar) {
            Icon(
                painter = painterResource(id = R.drawable.baseline_star_half_24),
                contentDescription = null,
                tint = starsColor
            )
        }
        repeat(unfilledStars) {
            Icon(
                painter = painterResource(id = R.drawable.baseline_star_outline_24),
                contentDescription = null,
                //tint = starsColor
            )
        }
    }
}

@Preview
@Composable
fun RatingPreview() {
    RatingBar(rating = 2.5)
}
