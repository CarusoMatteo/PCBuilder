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
import com.caruso.pcbuilderproject.componentsclasses.ComponentType.Companion.MOTHERBOARD
import com.caruso.pcbuilderproject.filters.FilterFlowRow
import com.caruso.pcbuilderproject.filters.FilterListHeader
import com.caruso.pcbuilderproject.navigation.BottomBarScreen
import com.caruso.pcbuilderproject.ui.theme.PCBuilderProjectTheme
import com.caruso.pcbuilderproject.utilities.GlobalData

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun MotherboardFilterDialog(
    filterDialogOpen: MutableState<Boolean>,
    navController: NavHostController?
) {
    val somethingWasChanged = remember { mutableStateOf(false) }

    Dialog(
        properties = DialogProperties(usePlatformDefaultWidth = false),
        onDismissRequest = {
            filterDialogOpen.value = false
            if (somethingWasChanged.value) {
                GlobalData.askingToReloadStore = true

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
                                GlobalData.askingToReloadStore = true

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
                    title = { Text(text = stringResource(motherboardFilters_Title)) },
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
                    component = MOTHERBOARD,
                    name = stringResource(brand_Text),
                    somethingWasChanged = somethingWasChanged
                )

                Divider(modifier = Modifier.padding(top = 15.dp, bottom = 15.dp))

                FilterListHeader(
                    text = stringResource(socket_Text)
                )

                FilterFlowRow(
                    component = MOTHERBOARD,
                    name = stringResource(socket_Text),
                    somethingWasChanged = somethingWasChanged
                )

                Divider(modifier = Modifier.padding(top = 15.dp, bottom = 15.dp))

                FilterListHeader(
                    text = stringResource(chipset_Text)
                )

                FilterFlowRow(
                    component = MOTHERBOARD,
                    name = stringResource(chipset_Text),
                    somethingWasChanged = somethingWasChanged
                )

                Divider(modifier = Modifier.padding(top = 15.dp, bottom = 15.dp))

                FilterListHeader(
                    text = stringResource(formFactor_Text)
                )

                FilterFlowRow(
                    component = MOTHERBOARD,
                    name = stringResource(formFactor_Text),
                    somethingWasChanged = somethingWasChanged
                )

                Divider(modifier = Modifier.padding(top = 15.dp, bottom = 15.dp))

                FilterListHeader(
                    text = stringResource(memoryType_Text)
                )

                FilterFlowRow(
                    component = MOTHERBOARD,
                    name = stringResource(memoryType_Text),
                    somethingWasChanged = somethingWasChanged
                )

                Divider(modifier = Modifier.padding(top = 15.dp, bottom = 15.dp))

                FilterListHeader(
                    text = stringResource(memorySlotNumber_Text)
                )

                FilterFlowRow(
                    component = MOTHERBOARD,
                    name = stringResource(memorySlotNumber_Text),
                    somethingWasChanged = somethingWasChanged
                )

                Divider(modifier = Modifier.padding(top = 15.dp, bottom = 15.dp))

                FilterListHeader(
                    text = stringResource(maxEthernetSpeed)
                )

                FilterFlowRow(
                    component = MOTHERBOARD,
                    name = stringResource(maxEthernetSpeed),
                    somethingWasChanged = somethingWasChanged
                )

                Divider(modifier = Modifier.padding(top = 15.dp, bottom = 15.dp))

                FilterListHeader(
                    text = stringResource(wifiIncluded_Text)
                )

                FilterFlowRow(
                    component = MOTHERBOARD,
                    name = stringResource(wifiIncluded_Text),
                    somethingWasChanged = somethingWasChanged
                )

                Divider(modifier = Modifier.padding(top = 15.dp, bottom = 15.dp))

                FilterListHeader(
                    text = stringResource(bluetoothIncluded_Text)
                )

                FilterFlowRow(
                    component = MOTHERBOARD,
                    name = stringResource(bluetoothIncluded_Text),
                    somethingWasChanged = somethingWasChanged
                )

                Divider(modifier = Modifier.padding(top = 15.dp, bottom = 15.dp))

                FilterListHeader(
                    text = stringResource(pcie5ExpansionSlotPresent_Text)
                )

                FilterFlowRow(
                    component = MOTHERBOARD,
                    name = stringResource(pcie5ExpansionSlotPresent_Text),
                    somethingWasChanged = somethingWasChanged
                )

                Divider(modifier = Modifier.padding(top = 15.dp, bottom = 15.dp))

                FilterListHeader(
                    text = stringResource(nvmeM25SlotPresent_Text)
                )

                FilterFlowRow(
                    component = MOTHERBOARD,
                    name = stringResource(nvmeM25SlotPresent_Text),
                    somethingWasChanged = somethingWasChanged
                )

                Spacer(modifier = Modifier.height(15.dp))
            }
        }
    }
}

@Preview
@Composable
fun MotherboardFilterDialogPreview() {
    PCBuilderProjectTheme(darkTheme = true) {
        MotherboardFilterDialog(
            filterDialogOpen = remember { mutableStateOf(true) },
            navController = null
        )
    }
}