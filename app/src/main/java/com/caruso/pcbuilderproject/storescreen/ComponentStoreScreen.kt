package com.caruso.pcbuilderproject.storescreen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.FilterList
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import com.caruso.pcbuilderproject.R.string.*
import com.caruso.pcbuilderproject.componentsclasses.*
import com.caruso.pcbuilderproject.componentsclasses.ComponentType.Companion.CPU
import com.caruso.pcbuilderproject.componentsclasses.ComponentType.Companion.MOTHERBOARD
import com.caruso.pcbuilderproject.filters.componentfilter.CPUFilterDialog
import com.caruso.pcbuilderproject.filters.componentfilter.MotherboardFilterDialog
import com.caruso.pcbuilderproject.navigation.BottomBarScreen
import com.caruso.pcbuilderproject.ui.theme.PCBuilderProjectTheme
import com.caruso.pcbuilderproject.utilities.*
import com.caruso.pcbuilderproject.utilities.GlobalData.Companion.noItemsFoundCardVisible

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ComponentStoreScreen(
    paddingValues: PaddingValues,
    topAppBarTitle: MutableState<String>,
    filterCardHidden: MutableState<Boolean>,
    filterDialogOpen: MutableState<Boolean>,
    navController: NavHostController?,
    snackbarHostState: SnackbarHostState?,
    components: MutableList<Component>,
    componentsType: Int
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(paddingValues)
            .padding(bottom = 80.dp)
    ) {
        val lazyColumnScrollState = rememberLazyListState()

        LazyColumn(
            state = lazyColumnScrollState,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.fillMaxSize()
        ) {
            item {
                Spacer(modifier = Modifier.height(10.dp))

                ElevatedCard(
                    modifier = Modifier
                        .fillMaxWidth(0.9f)
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                    ) {
                        IconButton(
                            onClick = {
                                filterDialogOpen.value = true
                            }
                        ) {
                            Icon(
                                imageVector = Icons.Default.FilterList,
                                contentDescription = "Open filter list"
                            )
                        }

                        LazyRow(
                            modifier = Modifier.fillMaxWidth(),
                        ) {
                            items(items = GlobalData.getActiveFilters(), itemContent = { item ->
                                if (item.component == componentsType) {
                                    FilterChip(
                                        selected = true,
                                        label = {
                                            Text(
                                                text = item.name + ": " + item.value
                                            )
                                        },
                                        leadingIcon = {
                                            Icon(
                                                imageVector = Icons.Default.Check,
                                                contentDescription = null
                                            )
                                        },
                                        onClick = {
                                            item.active = false

                                            ServerFunctions.askingToReloadStore = true

                                            navController?.navigate(BottomBarScreen.StoreScreen.route) {
                                                popUpTo(id = navController.graph.findStartDestination().id)
                                                launchSingleTop = true
                                            }
                                        },
                                        modifier = Modifier.padding(end = 5.dp)
                                    )
                                }
                            })
                        }
                    }
                }
            }

            item {
                Spacer(modifier = Modifier.height(10.dp))
            }

            items(items = components) { item ->
                ComponentProductCard(
                    modifier = Modifier.fillMaxWidth(0.9f),
                    nameSize = MaterialTheme.typography.titleMedium,
                    component = item,
                    navController = navController,
                    snackbarHostState = snackbarHostState
                )

                Spacer(modifier = Modifier.height(10.dp))
            }

            item {
                val serverIsReachable: MutableState<Boolean> = remember { mutableStateOf(true) }
                if (noItemsFoundCardVisible) {
                    Thread {
                        serverIsReachable.value = ServerFunctions.isServerReachable()
                    }.start()

                    Card(
                        modifier = Modifier.fillMaxWidth(0.9f),
                    ) {
                        Column(
                            verticalArrangement = Arrangement.Center,
                            horizontalAlignment = Alignment.CenterHorizontally,
                            modifier = Modifier
                                .fillMaxSize()
                                .padding(all = 20.dp)
                        ) {
                            Text(
                                text = if (serverIsReachable.value)
                                    stringResource(noItemsFound_Text)
                                else
                                    stringResource(serverError_Message)
                            )

                            Spacer(modifier = Modifier.height(10.dp))

                            Button(onClick = {
                                if (serverIsReachable.value)
                                    GlobalData.getActiveFilters()
                                        .filter { it.component == componentsType }
                                        .forEach { it.active = false }

                                ServerFunctions.askingToReloadStore = true

                                navController?.navigate(BottomBarScreen.StoreScreen.route) {
                                    popUpTo(id = navController.graph.findStartDestination().id)
                                    launchSingleTop = true
                                }
                            }) {
                                Text(
                                    // If either the server is not reachable, or there are no active filters for this component type
                                    text = if ((!serverIsReachable.value) ||
                                        (GlobalData.getActiveFilters().none {
                                            it.component == componentsType
                                        })
                                    ) {
                                        stringResource(
                                            tryAgain_Button
                                        )
                                    } else {
                                        stringResource(clearFilters_Button)
                                    }
                                )
                            }
                        }
                    }

                    Spacer(modifier = Modifier.height(10.dp))
                }
            }
        }

        val firstVisibleItemIndex by remember { derivedStateOf { lazyColumnScrollState.firstVisibleItemIndex } }

        if (firstVisibleItemIndex > 0) {
            filterCardHidden.value = true

            topAppBarTitle.value = ComponentType.toStringPlural(
                componentType = componentsType,
                context = LocalContext.current
            )
        } else {
            filterCardHidden.value = false

            topAppBarTitle.value = stringResource(store_NavBarItem) +
                    " — " +
                    ComponentType.toStringPlural(
                        componentType = componentsType,
                        context = LocalContext.current
                    )
        }
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
fun ComponentStoreScreenPreview() {
    PCBuilderProjectTheme(darkTheme = true) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.background)
        ) {
            StoreScreen(
                componentType = CPU,
                snackbarHostState = null,
                navController = null
            )
        }
    }
}