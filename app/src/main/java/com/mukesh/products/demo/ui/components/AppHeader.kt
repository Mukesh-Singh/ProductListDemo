package com.mukesh.products.demo.ui.components

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.mukesh.products.demo.ui.models.OptionMenuItem
import com.mukesh.products.demo.ui.theme.ProductListDemoTheme

/**
Created by Mukesh
 **/


@Composable
fun AppHeader(
    modifier: Modifier = Modifier,
    showBack: Boolean = false,
    @DrawableRes navigationIcon: Int? = null,
    title: String? = null,
    optionMenuList: List<OptionMenuItem>? = null,
    onMenuItemSelect: ((item: OptionMenuItem, label: String?) -> Unit)? = null,
    onBackPress: () -> Unit = {},
    navController: NavController? = null,
) {
    if (showBack || (navigationIcon != null && navigationIcon != 0)) {
        TopAppBar(
            modifier = modifier,
            title = {
                Text(
                    //modifier = modifier.padding(horizontal = 8.dp),
                    text = title ?: ""
                )
            },
            elevation = 0.dp,
            navigationIcon = {
                NavigationIconWithBackBackButton(
                    showBack = showBack,
                    navigationIcon = navigationIcon,
                    navController = navController
                )
            },
            actions = {HeaderActions(optionMenuList = optionMenuList, onMenuItemSelect = onMenuItemSelect)}

        )
    } else {
        TopAppBar(
            modifier = modifier,
            title = {
                Text(
                    //modifier = modifier.padding(horizontal = 8.dp),
                    text = title ?: ""
                )
            },
            elevation = 0.dp,
            actions = {HeaderActions(optionMenuList = optionMenuList, onMenuItemSelect = onMenuItemSelect)}

        )
    }
}

@Composable
fun NavigationIconWithBackBackButton(
    showBack: Boolean = false,
    @DrawableRes navigationIcon: Int? = null,
    navController: NavController? = null,
) {
    Box(modifier = Modifier.padding(start = 8.dp)) {
        if (showBack) {
            if (navigationIcon != null && navigationIcon != 0) {
                Icon(
                    painter = painterResource(id = navigationIcon),
                    contentDescription = null,
                    Modifier.clickable(
                        interactionSource = remember { MutableInteractionSource() },
                        indication = rememberRipple(
                            bounded = false,
                        )
                    ) {
                        navController?.popBackStack()
                    }
                )
            } else {
                Icon(
                    imageVector = Icons.Default.ArrowBack,
                    contentDescription = "Back",
                    Modifier.clickable(
                        interactionSource = remember { MutableInteractionSource() },
                        indication = rememberRipple(
                            bounded = false,
                        )
                    ) {
                        navController?.popBackStack()
                    })
            }
        } else {
            if (navigationIcon != null && navigationIcon != 0) {
                Image(
                    painter = painterResource(id = navigationIcon),
                    contentDescription = null
                )
            }
        }
    }
}

@Composable
fun HeaderActions(
    optionMenuList: List<OptionMenuItem>? = null,
    onMenuItemSelect: ((item: OptionMenuItem, label: String?) -> Unit)? = null,
) {
    val mContext = LocalContext.current
    var mDisplayOverFlowMenu by rememberSaveable { mutableStateOf(false) }
    optionMenuList?.let {
        if (it.isNotEmpty()) {
            val hasOverFlowMenu = it.size > 2
            val menuList = mutableListOf<OptionMenuItem>()
            val overFlowMenu = mutableListOf<OptionMenuItem>()
            if (hasOverFlowMenu) {
                menuList.addAll(optionMenuList.subList(0, 2))
                overFlowMenu.addAll(optionMenuList.subList(2, optionMenuList.size))
            } else {
                menuList.addAll(optionMenuList)
            }
            menuList.forEach {
                val string = stringResource(id = it.resourceId)
                IconButton(onClick = {
                    //Toast.makeText(mContext, string, Toast.LENGTH_SHORT).show()
                    onMenuItemSelect?.invoke(it, string)
                }) {
                    Icon(
                        imageVector = it.imageVector,
                        contentDescription = string
                    )
                }
            }
            if (hasOverFlowMenu) {
                // Creating Icon button for dropdown menu
                IconButton(onClick = { mDisplayOverFlowMenu = !mDisplayOverFlowMenu }) {
                    Icon(Icons.Default.MoreVert, "")
                }
            }

            // Creating a dropdown menu
            DropdownMenu(
                modifier = Modifier.background(MaterialTheme.colors.background),
                expanded = mDisplayOverFlowMenu,
                onDismissRequest = { mDisplayOverFlowMenu = false },


                ) {
                // Creating dropdown menu item, on click
                // would create a Toast message

                menuList.forEach {
                    val string = stringResource(id = it.resourceId)
                    DropdownMenuItem(modifier = Modifier.padding(8.dp), onClick = {
                        //Toast.makeText(mContext, string, Toast.LENGTH_SHORT).show()
                        onMenuItemSelect?.invoke(it, string)
                    }) {

                        Icon(
                            modifier = Modifier.padding(end = 16.dp),
                            imageVector = it.imageVector,
                            contentDescription = string
                        )
                        Text(text = string)


                    }
                }
            }
        }
    }


}

@Preview
@Composable
fun Preview_AppHeader() {
    ProductListDemoTheme() {
        AppHeader()
    }
}