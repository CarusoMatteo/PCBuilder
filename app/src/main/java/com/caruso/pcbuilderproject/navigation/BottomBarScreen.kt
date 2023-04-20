package com.caruso.pcbuilderproject.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material.icons.outlined.*
import androidx.compose.ui.graphics.vector.ImageVector
import com.caruso.pcbuilderproject.R

sealed class BottomBarScreen(
    val route: String,
    val titleResourceId: Int,
    val filledIcon: ImageVector,
    val outlinedIcon: ImageVector
) {
    object PartsListScreen : BottomBarScreen(
        route = "partsListScreen",
        titleResourceId = R.string.partsList_NavBarItem,
        filledIcon = Icons.Filled.ViewList,
        outlinedIcon = Icons.Outlined.ViewList,
    )

    object StoreScreen : BottomBarScreen(
        route = "storeScreen",
        titleResourceId = R.string.store_NavBarItem,
        filledIcon = Icons.Filled.LocalMall,
        outlinedIcon = Icons.Outlined.LocalMall
    )

    object AccountScreen : BottomBarScreen(
        route = "accountListScreen",
        titleResourceId = R.string.account_NavBarItem,
        filledIcon = Icons.Filled.AccountCircle,
        outlinedIcon = Icons.Outlined.AccountCircle
    )
}
