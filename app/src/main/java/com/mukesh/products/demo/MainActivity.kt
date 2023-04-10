package com.mukesh.products.demo

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.mukesh.products.demo.ui.components.SetupNavigationSystem
import com.mukesh.products.demo.ui.theme.ContentColor
import com.mukesh.products.demo.ui.theme.ProductListDemoTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ProductListApp()
        }
    }
}

@Composable
fun ProductListApp(
    modifier: Modifier = Modifier,
) {
    val thempTheme = isSystemInDarkTheme()
    val isDarkTheme by remember{ mutableStateOf(thempTheme) }

    val systemUiController = rememberSystemUiController()
    val statusBarColor = if (isDarkTheme) ContentColor.StatusBarColorDark else ContentColor.StatusBarColorLight
    systemUiController.setSystemBarsColor(
        color = statusBarColor,
        darkIcons = false
    )

    ProductListDemoTheme(darkTheme = isDarkTheme) {
        SetupNavigationSystem()
    }

}