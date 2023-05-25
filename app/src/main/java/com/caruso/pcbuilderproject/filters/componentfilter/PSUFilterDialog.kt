package com.caruso.pcbuilderproject.filters.componentfilter

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import com.caruso.pcbuilderproject.R.string.*
import com.caruso.pcbuilderproject.componentsclasses.ComponentType.Companion.PSU
import com.caruso.pcbuilderproject.filters.FilterFlowRow
import com.caruso.pcbuilderproject.filters.FilterListHeader
import com.caruso.pcbuilderproject.navigation.BottomBarScreen
import com.caruso.pcbuilderproject.ui.theme.PCBuilderProjectTheme
import com.caruso.pcbuilderproject.utilities.ServerFunctions

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun PSUFilterDialog(
    filterDialogOpen: MutableState<Boolean>,
    navController: NavHostController?
) {
    val somethingWasChanged = remember { mutableStateOf(false) }

    Dialog(
        properties = DialogProperties(usePlatformDefaultWidth = false),
        onDismissRequest = {
            filterDialogOpen.value = false
            if (somethingWasChanged.value) {
                ServerFunctions.askingToReloadStore = true

                navController?.navigate(BottomBarScreen.StoreScreen.route) {
                    popUpTo(id = navController.graph.findStartDestination().id)
                    launchSingleTop = true
                }
            }
        },
    ) {
        val scrollBehavior = TopAppBarDefaults.pinnedScrollBehavior()

        Scaffold(
            modifier = Modifier
                .fillMaxSize()
                .nestedScroll(scrollBehavior.nestedScrollConnection),
            topBar = {
                TopAppBar(
                    navigationIcon = {
                        IconButton(onClick = {
                            filterDialogOpen.value = false
                            if (somethingWasChanged.value) {
                                ServerFunctions.askingToReloadStore = true

                                navController?.navigate(BottomBarScreen.StoreScreen.route) {
                                    popUpTo(id = navController.graph.findStartDestination().id)
                                    launchSingleTop = true
                                }
                            }
                        }) {
                            Icon(
                                imageVector = Icons.Default.ArrowBack,
                                contentDescription = "Close"
                            )
                        }
                    },
                    title = { Text(text = stringResource(psuFilters_Text)) },
                    scrollBehavior = scrollBehavior
                )
            }
        ) { paddingValues ->
            Column(
                modifier = Modifier
                    .padding(paddingValues)
                    .padding(start = 20.dp, end = 20.dp)
                    .verticalScroll(rememberScrollState())
            ) {
                FilterListHeader(
                    text = stringResource(brand_Text)
                )

                FilterFlowRow(
                    component = PSU,
                    name = stringResource(brand_Text),
                    somethingWasChanged = somethingWasChanged
                )

                Divider(modifier = Modifier.padding(top = 15.dp, bottom = 15.dp))

                FilterListHeader(
                    text = stringResource(wattage_Text)
                )

                FilterFlowRow(
                    component = PSU,
                    name = stringResource(wattage_Text),
                    somethingWasChanged = somethingWasChanged
                )

                Divider(modifier = Modifier.padding(top = 15.dp, bottom = 15.dp))

                FilterListHeader(
                    text = stringResource(formFactor_Text)
                )

                FilterFlowRow(
                    component = PSU,
                    name = stringResource(formFactor_Text),
                    somethingWasChanged = somethingWasChanged
                )

                Divider(modifier = Modifier.padding(top = 15.dp, bottom = 15.dp))

                FilterListHeader(
                    text = stringResource(length_Text)
                )

                FilterFlowRow(
                    component = PSU,
                    name = stringResource(length_Text),
                    somethingWasChanged = somethingWasChanged
                )

                Spacer(modifier = Modifier.height(15.dp))
            }
        }
    }
}

@Preview
@Composable
fun PSUFilterDialogPreview() {
    PCBuilderProjectTheme(darkTheme = true) {
        PSUFilterDialog(
            filterDialogOpen = remember { mutableStateOf(true) },
            navController = null
        )
    }
}