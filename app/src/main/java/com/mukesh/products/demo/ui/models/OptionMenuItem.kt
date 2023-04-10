package com.mukesh.products.demo.ui.models

import androidx.annotation.StringRes
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.ui.graphics.vector.ImageVector
import com.mukesh.products.demo.R

/**
Created by Mukesh
 **/

sealed class OptionMenuItem(val id: Int = 0, @StringRes val resourceId : Int, val imageVector: ImageVector){
    companion object{
        const val ID_SETTING = 100
    }
    object Favorite: OptionMenuItem(ID_SETTING, R.string.favorite, Icons.Default.Favorite)
}