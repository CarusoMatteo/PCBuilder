package com.caruso.pcbuilderproject.accountscreen

import android.util.Log
import androidx.compose.animation.*
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.*
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import com.caruso.pcbuilderproject.R.string.*
import com.caruso.pcbuilderproject.dialogs.ServerSettingsDialog
import com.caruso.pcbuilderproject.navigation.BottomBarScreen
import com.caruso.pcbuilderproject.ui.theme.PCBuilderProjectTheme
import com.caruso.pcbuilderproject.utilities.GlobalData


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AccountScreen(
    snackbarHostState: SnackbarHostState?,
    navController: NavHostController?
) {


    val context = LocalContext.current
    val scope = rememberCoroutineScope()

    val scrollBehavior = TopAppBarDefaults.pinnedScrollBehavior()

    val serverSettingDialogOpen = remember { mutableStateOf(false) }
    val exitButtonVisible: MutableState<Boolean> = remember {
        mutableStateOf(GlobalData.loggedInUser != null)
    }

    val profileText = stringResource(account_NavBarItem)
    val welcomeText = stringResource(welcomeUser_Text)

    if (GlobalData.reloadAccountScreenForXTimes > 0) {
        Log.d("Reload Account Screen", "Reloading Account Screen for another time.")

        GlobalData.reloadAccountScreenForXTimes--
        navController?.navigate(BottomBarScreen.AccountScreen.route) {
            popUpTo(id = navController.graph.findStartDestination().id)
            launchSingleTop = true
        }
    }

    val topAppBarTitle by remember {
        mutableStateOf(
            if (GlobalData.loggedInUser != null) {
                if (GlobalData.loggedInUser!!.username.isNotEmpty()) {
                    "$welcomeText ${GlobalData.loggedInUser!!.username}!"
                } else {
                    "$welcomeText!"
                }
            } else {
                profileText
            }
        )
    }

    Scaffold(
        modifier = Modifier
            .nestedScroll(scrollBehavior.nestedScrollConnection)
            .padding(bottom = 80.dp),
        topBar = {
            TopAppBar(
                title = { Text(text = topAppBarTitle) },
                scrollBehavior = scrollBehavior,
                navigationIcon = {
                    Icon(
                        imageVector = BottomBarScreen.AccountScreen.filledIcon,
                        contentDescription = null,
                        modifier = Modifier.padding(start = 16.dp, end = 16.dp)
                    )
                },
                actions = {
                    AnimatedVisibility(visible = exitButtonVisible.value) {
                        IconButton(
                            onClick = {
                                exitButtonVisible.value = false

                                GlobalData.logout(context = context)

                                navController?.navigate(BottomBarScreen.AccountScreen.route) {
                                    popUpTo(id = navController.graph.findStartDestination().id)
                                    launchSingleTop = true
                                }
                            }
                        ) {
                            Icon(
                                imageVector = Icons.Filled.Logout,
                                contentDescription = "Logout"
                            )
                        }
                    }

                    IconButton(
                        onClick = { serverSettingDialogOpen.value = true }
                    ) {
                        Icon(
                            imageVector = Icons.Filled.Settings,
                            contentDescription = "View server settings"
                        )
                    }
                }
            )
        }
    ) { paddingValues ->
        if (GlobalData.loggedInUser == null) {
            LoginScreen(
                exitButtonVisible = remember { mutableStateOf(false) },
                paddingValues = paddingValues,
                navController = navController,
                snackbarHostState = snackbarHostState,
                context = context,
                scope = scope
            )
        } else {
            ProfileScreen(
                paddingValues = paddingValues,
            )
        }
    }

    if (serverSettingDialogOpen.value) {
        ServerSettingsDialog(
            serverSettingDialogOpen = serverSettingDialogOpen
        )
    }
}


@Preview(showBackground = true)
@Composable
fun AccountScreenPreview() {
    PCBuilderProjectTheme(darkTheme = true) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.background)
        ) {
            AccountScreen(
                snackbarHostState = null,
                navController = null
            )
        }
    }
}