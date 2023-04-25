package com.caruso.pcbuilderproject.storescreen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
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
fun StoreScreen(
    snackbarHostState: SnackbarHostState? = null,
    navController: NavHostController? = null,
    storeProductTypeSelected: Int = GlobalData.getStoreProductTypeSelected()
) {

    val serverSettingDialogOpen = remember { mutableStateOf(false) }
    val scrollBehavior = TopAppBarDefaults.pinnedScrollBehavior()

    Scaffold(
        modifier = Modifier.nestedScroll(scrollBehavior.nestedScrollConnection),

        topBar = {
            TopAppBar(
                title = {
                    Text(text = stringResource(store_NavBarItem))
                },
                scrollBehavior = scrollBehavior,
                navigationIcon = {
                    Icon(
                        imageVector = BottomBarScreen.StoreScreen.filledIcon,
                        contentDescription = "Menu",
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

        when (storeProductTypeSelected) {
            1 -> CPUScoreScreen(paddingValues = paddingValues)
            2 -> MotherboardStoreScreen(paddingValues = paddingValues)

            else -> {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(MaterialTheme.colorScheme.background),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = stringResource(incorrect_store_index_Error),
                        fontSize = MaterialTheme.typography.headlineLarge.fontSize,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.onBackground
                    )
                }
            }
        }
    }

    if (serverSettingDialogOpen.value) {
        ServerSettingsDialog(
            serverSettingDialogOpen = serverSettingDialogOpen
        )
    }
}

@Preview
@Composable
fun StoreScreenPreview() {
    PCBuilderProjectTheme(darkTheme = true) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.background)
        ) {
            StoreScreen()
        }
    }
}