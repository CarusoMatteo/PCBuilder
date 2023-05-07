package com.caruso.pcbuilderproject.partslistscreen

import android.util.Log
import androidx.compose.animation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
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
import androidx.navigation.NavHostController
import com.caruso.pcbuilderproject.R.string.*
import com.caruso.pcbuilderproject.componentsclasses.ComponentType
import com.caruso.pcbuilderproject.dialogs.ServerSettingsDialog
import com.caruso.pcbuilderproject.incompatibilities.Incompatibility
import com.caruso.pcbuilderproject.incompatibilities.IncompatibilityDialog
import com.caruso.pcbuilderproject.navigation.BottomBarScreen
import com.caruso.pcbuilderproject.ui.theme.PCBuilderProjectTheme
import com.caruso.pcbuilderproject.utilities.*

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PartsListScreen(
    snackbarHostState: SnackbarHostState?,
    navController: NavHostController?
) {
    Log.d("PartsList Loading", "Currently loading PartsListScreen.")

    val serverSettingDialogOpen = remember { mutableStateOf(false) }
    val filterCardHidden = remember { mutableStateOf(false) }
    val filterDialogOpen = remember { mutableStateOf(false) }
    val scrollBehavior = TopAppBarDefaults.pinnedScrollBehavior()
    val incompatibilityDialogVisible = remember { mutableStateOf(false) }
    val currentIncompatibilityClicked = remember { mutableStateOf(Incompatibility("", "")) }

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
                .padding(bottom = 80.dp)
                .verticalScroll(rememberScrollState()),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.height(10.dp))

            ElevatedCard(
                modifier = Modifier
                    .fillMaxWidth(0.9f),
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
                LazyRow(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 10.dp, end = 10.dp)

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
                                    incompatibilityDialogVisible.value = true
                                    currentIncompatibilityClicked.value = item
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

            Spacer(modifier = Modifier.height(10.dp))

            ComponentPartsListItem(
                modifier = Modifier.fillMaxWidth(0.9f),
                navController = navController,
                component = GlobalData.loggedInUser.cpuSelected,
                componentType = ComponentType.CPU,
                snackbarHostState = snackbarHostState
            )

            Spacer(modifier = Modifier.height(10.dp))

            ComponentPartsListItem(
                modifier = Modifier.fillMaxWidth(0.9f),
                navController = navController,
                component = GlobalData.loggedInUser.motherboardSelected,
                componentType = ComponentType.MOTHERBOARD,
                snackbarHostState = snackbarHostState
            )

            Spacer(modifier = Modifier.height(10.dp))
        }
    }

    if (serverSettingDialogOpen.value) {
        ServerSettingsDialog(
            serverSettingDialogOpen = serverSettingDialogOpen
        )
    }

    if (incompatibilityDialogVisible.value) {
        IncompatibilityDialog(
            incompatibility = currentIncompatibilityClicked.value,
            incompatibilityDialogVisible = incompatibilityDialogVisible
        )
    }
}

@Preview(showBackground = true)
@Composable
fun PartsListScreenPreview() {
    PCBuilderProjectTheme(darkTheme = true) {
        PartsListScreen(
            navController = null,
            snackbarHostState = null
        )
    }
}