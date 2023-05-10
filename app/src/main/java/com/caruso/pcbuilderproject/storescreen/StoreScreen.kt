package com.caruso.pcbuilderproject.storescreen

import android.util.Log
import androidx.compose.animation.*
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.caruso.pcbuilderproject.R.string.*
import com.caruso.pcbuilderproject.componentsclasses.*
import com.caruso.pcbuilderproject.componentsclasses.ComponentType.Companion.CPU
import com.caruso.pcbuilderproject.componentsclasses.ComponentType.Companion.GPU
import com.caruso.pcbuilderproject.componentsclasses.ComponentType.Companion.MOTHERBOARD
import com.caruso.pcbuilderproject.componentsclasses.ComponentType.Companion.PSU
import com.caruso.pcbuilderproject.componentsclasses.ComponentType.Companion.RAM
import com.caruso.pcbuilderproject.componentsclasses.ComponentType.Companion.STORAGE
import com.caruso.pcbuilderproject.dialogs.ServerSettingsDialog
import com.caruso.pcbuilderproject.filters.componentfilter.CPUFilterDialog
import com.caruso.pcbuilderproject.filters.componentfilter.MotherboardFilterDialog
import com.caruso.pcbuilderproject.navigation.BottomBarScreen
import com.caruso.pcbuilderproject.ui.theme.PCBuilderProjectTheme
import com.caruso.pcbuilderproject.utilities.*

@Suppress("UNCHECKED_CAST")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun StoreScreen(
    snackbarHostState: SnackbarHostState?,
    navController: NavHostController?,
    componentType: Int = GlobalData.getStoreProductTypeSelected()
) {
    Log.d("StoreScreen Loading", "----------------------------")
    Log.d("StoreScreen Loading", "Currently loading StoreScreen.")
    Log.d("StoreScreen Loading", "----------------------------")

    val context = LocalContext.current

    val storeNavBarItem = stringResource(store_NavBarItem)

    val serverSettingDialogOpen = remember { mutableStateOf(false) }
    val topAppBarTitle = remember {
        mutableStateOf(
            "$storeNavBarItem — " + ComponentType.toStringPlural(
                componentType = GlobalData.getStoreProductTypeSelected(),
                context = context
            )
        )
    }
    val filterCardHidden = remember { mutableStateOf(false) }
    val filterDialogOpen = remember { mutableStateOf(false) }
    val scrollBehavior = TopAppBarDefaults.pinnedScrollBehavior()

    Scaffold(
        modifier = Modifier.nestedScroll(scrollBehavior.nestedScrollConnection),

        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = topAppBarTitle.value
                    )
                },
                scrollBehavior = scrollBehavior,
                navigationIcon = {
                    Icon(
                        imageVector = BottomBarScreen.StoreScreen.filledIcon,
                        contentDescription = null,
                        modifier = Modifier.padding(start = 16.dp, end = 16.dp)
                    )
                },
                actions = {
                    AnimatedVisibility(
                        visible = filterCardHidden.value,
                        enter = fadeIn(),
                        exit = fadeOut()
                    ) {
                        IconButton(
                            onClick = { filterDialogOpen.value = true }
                        ) {
                            Icon(
                                imageVector = Icons.Default.FilterList,
                                contentDescription = "Open filter list"
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

        if (ServerFunctions.askingToReloadStore && navController != null) {
            ServerFunctions.getComponents(
                componentType = componentType,
                context = LocalContext.current,
                navController = navController
            )

            ServerFunctions.askingToReloadStore = false
        }

        if (ComponentType.isValid(componentType = componentType)) {
            ComponentStoreScreen(
                paddingValues = paddingValues,
                filterCardHidden = filterCardHidden,
                filterDialogOpen = filterDialogOpen,
                navController = navController,
                snackbarHostState = snackbarHostState,
                components = when (componentType) {
                    CPU -> ComponentsList.cpus as MutableList<Component>
                    MOTHERBOARD -> ComponentsList.motherboards as MutableList<Component>
                    RAM -> ComponentsList.rams as MutableList<Component>
                    GPU -> ComponentsList.gpus as MutableList<Component>
                    STORAGE -> ComponentsList.storages as MutableList<Component>
                    PSU -> ComponentsList.psus as MutableList<Component>
                    else -> ComponentsList.cpus as MutableList<Component>
                },
                componentsType = componentType,
                topAppBarTitle = topAppBarTitle
            )
        } else {
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

    if (serverSettingDialogOpen.value) {
        ServerSettingsDialog(
            serverSettingDialogOpen = serverSettingDialogOpen
        )
    }

    if (filterDialogOpen.value) {
        when (GlobalData.getStoreProductTypeSelected()) {
            CPU -> CPUFilterDialog(
                filterDialogOpen = filterDialogOpen,
                navController = navController
            )

            MOTHERBOARD -> MotherboardFilterDialog(
                filterDialogOpen = filterDialogOpen,
                navController = navController
            )
            // ComponentType.RAM -> TODO: RAMFilterDialog(
            //  filterDialogOpen = filterDialogOpen,
            //  navController = navController)
            // ComponentType.GPU -> TODO: GPUFilterDialog(
            //  filterDialogOpen = filterDialogOpen,
            //  navController = navController)
            // ComponentType.Storage -> TODO: StorageFilterDialog(
            //  filterDialogOpen = filterDialogOpen,
            //  navController = navController)
            // ComponentType.PSU -> TODO: PSUFilterDialog(
            //  filterDialogOpen = filterDialogOpen,
            //  navController = navController)
        }
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
            StoreScreen(
                snackbarHostState = null,
                navController = null
            )
        }
    }
}