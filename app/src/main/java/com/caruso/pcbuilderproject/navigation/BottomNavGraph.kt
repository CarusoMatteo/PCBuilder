package com.caruso.pcbuilderproject.navigation

import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.caruso.pcbuilderproject.accountscreen.AccountScreen
import com.caruso.pcbuilderproject.partslistscreen.PartsListScreen
import com.caruso.pcbuilderproject.storescreen.StoreScreen

@Composable
fun BottomNavGraph(
    navController: NavHostController,
    snackbarHostState: SnackbarHostState,
) {
    NavHost(
        navController = navController,
        startDestination = BottomBarScreen.PartsListScreen.route
    ) {
        composable(route = BottomBarScreen.PartsListScreen.route) {
            PartsListScreen(navController)
        }
        composable(route = BottomBarScreen.StoreScreen.route) {
            StoreScreen(snackbarHostState, navController)
        }
        composable(route = BottomBarScreen.AccountScreen.route) {
            AccountScreen(snackbarHostState, navController)
        }
    }
}