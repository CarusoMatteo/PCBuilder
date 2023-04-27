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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import com.caruso.pcbuilderproject.classes.CPU
import com.caruso.pcbuilderproject.classes.GlobalData
import com.caruso.pcbuilderproject.dialogs.CPUFilterDialog
import com.caruso.pcbuilderproject.navigation.BottomBarScreen
import com.caruso.pcbuilderproject.ui.theme.PCBuilderProjectTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CPUScoreScreen(
    paddingValues: PaddingValues,
    filterCardHidden: MutableState<Boolean>,
    filterDialogOpen: MutableState<Boolean>,
    navController: NavHostController? = null,
    cpus: MutableList<CPU>
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
                            items(items = GlobalData.filterList, itemContent = { item ->
                                if (item.component == "CPU" && item.active) {
                                    FilterChip(
                                        selected = true,
                                        label = {
                                            Text(
                                                text = item.value
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

            items(items = cpus) { item ->
                CPUProductCard(
                    modifier = Modifier.fillMaxWidth(0.9f),
                    nameSize = MaterialTheme.typography.titleMedium,
                    product = item
                )

                Spacer(modifier = Modifier.height(10.dp))
            }
        }

        val firstVisibleItemIndex by remember { derivedStateOf { lazyColumnScrollState.firstVisibleItemIndex } }
        filterCardHidden.value = firstVisibleItemIndex > 0
    }

    if (filterDialogOpen.value) {
        CPUFilterDialog(
            filterDialogOpen = filterDialogOpen
        )
    }
}

@Preview
@Composable
fun CPUStoreScreenPreview() {
    PCBuilderProjectTheme(darkTheme = true) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.background)
        ) {
            StoreScreen(storeProductTypeSelected = 1)
        }
    }
}