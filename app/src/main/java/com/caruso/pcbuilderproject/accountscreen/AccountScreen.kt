package com.caruso.pcbuilderproject.accountscreen

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
import androidx.navigation.NavHostController
import com.caruso.pcbuilderproject.R.string.*
import com.caruso.pcbuilderproject.classes.GlobalData
import com.caruso.pcbuilderproject.dialogs.ServerSettingsDialog
import com.caruso.pcbuilderproject.navigation.BottomBarScreen
import com.caruso.pcbuilderproject.ui.theme.PCBuilderProjectTheme


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AccountScreen(
    snackbarHostState: SnackbarHostState? = null,
    navController: NavHostController? = null
) {
    val context = LocalContext.current
    val scope = rememberCoroutineScope()

    val scrollBehavior = TopAppBarDefaults.pinnedScrollBehavior()

    val serverSettingDialogOpen = remember { mutableStateOf(false) }

    Scaffold(
        modifier = Modifier
            .nestedScroll(scrollBehavior.nestedScrollConnection)
            .padding(bottom = 80.dp),
        topBar = {
            TopAppBar(
                title = {
                    if (GlobalData.loggedInUser.username == null)
                        Text(text = stringResource(account_NavBarItem))
                    else
                        Text(text = stringResource(welcomeUser_Text) + GlobalData.loggedInUser + "!")
                },
                scrollBehavior = scrollBehavior,
                navigationIcon = {
                    Icon(
                        imageVector = BottomBarScreen.AccountScreen.filledIcon,
                        contentDescription = null,
                        modifier = Modifier.padding(start = 16.dp, end = 16.dp)
                    )
                },
                actions = {
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
        if (GlobalData.loggedInUser.username == null) {
            LoginScreen(
                paddingValues = paddingValues,
                navController = navController,
                snackbarHostState = snackbarHostState,
                context = context,
                scope = scope
            )
        } else {
            ProfileScreen(
                paddingValues = paddingValues,
                navController = navController,
                snackbarHostState = snackbarHostState,
                context = context,
                scope = scope
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
            AccountScreen()
        }
    }
}