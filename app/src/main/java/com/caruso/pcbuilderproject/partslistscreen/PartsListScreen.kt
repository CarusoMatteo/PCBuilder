package com.caruso.pcbuilderproject.partslistscreen

import androidx.compose.animation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import com.caruso.pcbuilderproject.R.string.*
import com.caruso.pcbuilderproject.classes.GlobalData
import com.caruso.pcbuilderproject.dialogs.ServerSettingsDialog
import com.caruso.pcbuilderproject.navigation.BottomBarScreen
import com.caruso.pcbuilderproject.ui.theme.PCBuilderProjectTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PartsListScreen(
    navController: NavHostController? = null
) {
    val serverSettingDialogOpen = remember { mutableStateOf(false) }
    val filterCardHidden = remember { mutableStateOf(false) }
    val filterDialogOpen = remember { mutableStateOf(false) }
    val scrollBehavior = TopAppBarDefaults.pinnedScrollBehavior()
    rememberCoroutineScope()

    Scaffold(
        modifier = Modifier.nestedScroll(scrollBehavior.nestedScrollConnection),

        topBar = {
            TopAppBar(
                title = {
                    Text(text = stringResource(partsList_NavBarItem))
                },
                scrollBehavior = scrollBehavior,
                navigationIcon = {
                    Icon(
                        imageVector = BottomBarScreen.PartsListScreen.filledIcon,
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
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(bottom = 80.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.height(10.dp))

            ElevatedCard(
                modifier = Modifier
                    .fillMaxWidth(0.9f),

                /*
                colors = CardDefaults.cardColors(
                    containerColor = MaterialTheme.colorScheme.errorContainer,
                    contentColor = MaterialTheme.colorScheme.onErrorContainer
                )
                 */

                colors = if (
                    GlobalData.getActiveIncompatibilities().isEmpty()
                ) {
                    CardDefaults.elevatedCardColors()
                } else {
                    CardDefaults.elevatedCardColors(
                        containerColor = MaterialTheme.colorScheme.errorContainer,
                        contentColor = MaterialTheme.colorScheme.onErrorContainer
                    )
                }

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
                        items(items = GlobalData.incompatibilityList, itemContent = { item ->
                            if (item.active) {
                                FilterChip(
                                    selected = true,
                                    label = {
                                        Text(
                                            text = item.name
                                        )
                                    },
                                    leadingIcon = {
                                        Icon(
                                            imageVector = Icons.Default.Error,
                                            contentDescription = null
                                        )
                                    },
                                    onClick = {
                                        // TODO: Clicked on an incompatibility
                                        //  This is only temporary
                                        item.active = false

                                        navController?.navigate(BottomBarScreen.PartsListScreen.route) {
                                            popUpTo(id = navController.graph.findStartDestination().id)
                                            launchSingleTop = true
                                        }
                                    },
                                    colors = FilterChipDefaults.filterChipColors(
                                        selectedContainerColor = MaterialTheme.colorScheme.error,
                                        selectedLabelColor = MaterialTheme.colorScheme.onError,
                                        selectedLeadingIconColor = MaterialTheme.colorScheme.onError
                                    ),
                                    modifier = Modifier.padding(end = 5.dp)
                                )
                            }
                        })
                    }
                }
            }

            Spacer(modifier = Modifier.height(10.dp))

            ComponentPartsListItem(
                modifier = Modifier.fillMaxWidth(0.9f),
                navController = navController,
                component = GlobalData.loggedInUser.cpuSelected,
                componentType = stringResource(cpu_Text)
            )

            Spacer(modifier = Modifier.height(10.dp))

            ComponentPartsListItem(
                modifier = Modifier.fillMaxWidth(0.9f),
                navController = navController,
                component = GlobalData.loggedInUser.motherboardSelected,
                componentType = stringResource(motherboard_Text)
            )

            /*
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(MaterialTheme.colorScheme.background),
                contentAlignment = Alignment.Center
            ) {
                Column(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {


                    Text(
                        text = "PARTS LIST",
                        fontSize = MaterialTheme.typography.headlineLarge.fontSize,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.onBackground
                    )

                    Row(
                        modifier = Modifier.fillMaxWidth(0.9f),
                        horizontalArrangement = Arrangement.SpaceEvenly
                    ) {
                        Button(onClick = {
                            if (navController != null) {
                                GlobalData.changeStoreProductTypeSelected(
                                    newValue = 1,
                                    navController = navController
                                )
                            }
                        }) {
                            Text(text = "Go to CPU")
                        }

                        Button(onClick = {
                            if (navController != null) {
                                GlobalData.changeStoreProductTypeSelected(
                                    newValue = 2,
                                    navController = navController
                                )
                            }
                        }) {
                            Text(text = "Go to Motherboard")
                        }
                    }
                }
            }
             */
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
fun PartsListScreenPreview() {
    PCBuilderProjectTheme(darkTheme = true) {
        PartsListScreen()
    }
}