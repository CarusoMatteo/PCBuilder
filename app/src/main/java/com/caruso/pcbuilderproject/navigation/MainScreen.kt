package com.caruso.pcbuilderproject.navigation

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.RowScope
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen() {
    val navController = rememberNavController()
    val snackbarHostState = remember { SnackbarHostState() }

    Scaffold(
        snackbarHost = { SnackbarHost(hostState = snackbarHostState) },
        bottomBar = { BottomBar(navController = navController) }
    ) {
        BottomNavGraph(
            navController = navController,
            snackbarHostState = snackbarHostState
        )
    }
}

@Composable
fun BottomBar(
    navController: NavHostController
) {
    val screens = listOf(
        BottomBarScreen.PartsListScreen,
        BottomBarScreen.StoreScreen,
        BottomBarScreen.AccountScreen
    )
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination

    NavigationBar {
        screens.forEach { screens ->
            AddItem(
                screen = screens,
                currentDestination = currentDestination,
                navController = navController
            )
        }
    }
}

@Composable
fun RowScope.AddItem(
    screen: BottomBarScreen,
    currentDestination: NavDestination?,
    navController: NavHostController
) {
    val selected = currentDestination?.hierarchy?.any {
        it.route == screen.route
    } == true

    NavigationBarItem(
        label = {
            Text(
                text = stringResource(id = screen.titleResourceId)
            )
        },
        icon = {
            Icon(
                imageVector = if (selected) screen.filledIcon else screen.outlinedIcon,
                contentDescription = "Navigation Icon"
            )
        },
        selected = selected,
        onClick = {
            navController.navigate(screen.route) {
                popUpTo(id = navController.graph.findStartDestination().id)
                launchSingleTop = true
            }
        }
    )
}