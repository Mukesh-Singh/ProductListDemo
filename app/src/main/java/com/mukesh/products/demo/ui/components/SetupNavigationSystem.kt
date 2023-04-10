package com.mukesh.products.demo.ui.components

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.mukesh.products.demo.ui.rout.Rout
import com.mukesh.products.demo.ui.screens.products.details.DetailsScreen
import com.mukesh.products.demo.ui.screens.products.favorites.FavoriteScreen
import com.mukesh.products.demo.ui.screens.products.list.ProductListScreen

/**
Created by Mukesh
 **/

@Composable
fun SetupNavigationSystem(
    modifier: Modifier = Modifier,
) {
    val navController = rememberNavController()
    NavHost(
        modifier = modifier,
        navController = navController,
        startDestination = Rout.PRODUCT_LIST.rout
    ) {
        composable(route = Rout.PRODUCT_LIST.rout) {
            ProductListScreen(
                modifier= Modifier.fillMaxSize(),
                navController = navController,
            )
        }
        composable(
            Rout.PRODUCT_DETAILS.rout,
            arguments = listOf(navArgument("product_id") { type = NavType.StringType })
        ) {
            val id = it.arguments?.getString("product_id")
            id?.let { it1 -> DetailsScreen(modifier= Modifier.fillMaxSize(), productId = it1, navController = navController) }
        }
        composable(route = Rout.FAVORITE.rout) {
            FavoriteScreen(
                modifier= Modifier.fillMaxSize(),
                navController = navController,
            )
        }

    }

}