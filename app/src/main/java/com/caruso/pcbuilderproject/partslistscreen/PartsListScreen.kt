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
import androidx.compose.ui.platform.LocalContext
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
import com.caruso.pcbuilderproject.specslist.SpecsListItem
import com.caruso.pcbuilderproject.ui.theme.PCBuilderProjectTheme
import com.caruso.pcbuilderproject.utilities.*

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PartsListScreen(
    snackbarHostState: SnackbarHostState?,
    navController: NavHostController?
) {
    Log.d("PartsList Loading", "----------------------------")
    Log.d("PartsList Loading", "Currently loading PartsListScreen.")
    Log.d("PartsList Loading", "----------------------------")

    val serverSettingDialogOpen = remember { mutableStateOf(false) }
    val filterCardHidden = remember { mutableStateOf(false) }
    val filterDialogOpen = remember { mutableStateOf(false) }
    val scrollBehavior = TopAppBarDefaults.pinnedScrollBehavior()
    val incompatibilityDialogVisible = remember { mutableStateOf(false) }
    val currentIncompatibilityClicked = remember { mutableStateOf(Incompatibility("", "")) }

    val context = LocalContext.current
    val scope = rememberCoroutineScope()
    val snackbarMessage = remember { mutableStateOf("") }

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
                    modifier = Modifier.fillMaxWidth()
                ) {
                    item {
                        Spacer(modifier = Modifier.width(10.dp))
                    }

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

                    item {
                        Spacer(modifier = Modifier.width(5.dp))
                    }
                }
            }

            Spacer(modifier = Modifier.height(10.dp))

            ComponentPartsListItem(
                modifier = Modifier.fillMaxWidth(0.9f),
                navController = navController,
                component = GlobalData.loggedInUser?.cpuSelected,
                componentType = ComponentType.CPU,
                snackbarHostState = snackbarHostState
            )

            Spacer(modifier = Modifier.height(10.dp))

            ComponentPartsListItem(
                modifier = Modifier.fillMaxWidth(0.9f),
                navController = navController,
                component = GlobalData.loggedInUser?.motherboardSelected,
                componentType = ComponentType.MOTHERBOARD,
                snackbarHostState = snackbarHostState
            )

            Spacer(modifier = Modifier.height(10.dp))

            ComponentPartsListItem(
                modifier = Modifier.fillMaxWidth(0.9f),
                navController = navController,
                component = GlobalData.loggedInUser?.ramSelected,
                componentType = ComponentType.RAM,
                snackbarHostState = snackbarHostState
            )

            Spacer(modifier = Modifier.height(10.dp))

            ComponentPartsListItem(
                modifier = Modifier.fillMaxWidth(0.9f),
                navController = navController,
                component = GlobalData.loggedInUser?.gpuSelected,
                componentType = ComponentType.GPU,
                snackbarHostState = snackbarHostState
            )

            Spacer(modifier = Modifier.height(10.dp))

            ComponentPartsListItem(
                modifier = Modifier.fillMaxWidth(0.9f),
                navController = navController,
                component = GlobalData.loggedInUser?.storageSelected,
                componentType = ComponentType.STORAGE,
                snackbarHostState = snackbarHostState
            )

            Spacer(modifier = Modifier.height(10.dp))

            ComponentPartsListItem(
                modifier = Modifier.fillMaxWidth(0.9f),
                navController = navController,
                component = GlobalData.loggedInUser?.psuSelected,
                componentType = ComponentType.PSU,
                snackbarHostState = snackbarHostState
            )

            Column(Modifier.fillMaxWidth(0.9f)) {
                Divider(modifier = Modifier.padding(top = 20.dp, bottom = 15.dp))

                SpecsListItem(
                    leftItem = stringResource(subTotal_Text),
                    rightItem = buildString {
                        append(
                            GlobalData.priceInFloatToString(
                                number = if (GlobalData.loggedInUser != null)
                                    GlobalData.loggedInUser!!.getTotalPrice()
                                else
                                    0f,
                                currency = stringResource(currency),
                                decimalPoint = stringResource(decimalPoint),
                                dropCurrency = false
                            )
                        )
                    }
                )

                if (GlobalData.loggedInUser != null && GlobalData.loggedInUser!!.balance > 0f) {
                    SpecsListItem(
                        leftItem = stringResource(balance_Text),
                        rightItem = buildString {
                            append("− ")
                            append(
                                GlobalData.priceInFloatToString(
                                    number = if (GlobalData.loggedInUser!!.balance < GlobalData.loggedInUser!!.getTotalPrice())
                                        GlobalData.loggedInUser!!.balance
                                    else
                                        GlobalData.loggedInUser!!.getTotalPrice(),
                                    currency = stringResource(currency),
                                    decimalPoint = stringResource(decimalPoint),
                                    dropCurrency = true
                                )
                            )
                        }
                    )

                    Divider(modifier = Modifier.padding(start = 290.dp))
                }

                if (GlobalData.loggedInUser != null) {
                    SpecsListItem(
                        leftItem = stringResource(total_Text),
                        rightItem = buildString {
                            append("= ")
                            append(
                                GlobalData.priceInFloatToString(
                                    number = if (GlobalData.loggedInUser!!.balance <= GlobalData.loggedInUser!!.getTotalPrice())
                                        GlobalData.loggedInUser!!.getTotalPrice() - GlobalData.loggedInUser!!.balance
                                    else
                                        0f,
                                    currency = stringResource(currency),
                                    decimalPoint = stringResource(decimalPoint),
                                    dropCurrency = true
                                )
                            )
                        }
                    )
                }

                val loadingIconVisible = remember { mutableStateOf(false) }

                FilledTonalButton(
                    onClick = {
                        if (GlobalData.loggedInUser != null && navController != null && snackbarHostState != null) {
                            ServerFunctions.createOrder(
                                username = GlobalData.loggedInUser!!.username,
                                totalPrice = GlobalData.loggedInUser!!.getTotalPrice(),
                                loadingIconVisible = loadingIconVisible,
                                context = context,
                                navController = navController,
                                scope = scope,
                                snackbarHostState = snackbarHostState,
                                snackbarMessage = snackbarMessage
                            )
                        }
                    },
                    modifier = Modifier.fillMaxWidth(),
                    enabled = if (GlobalData.loggedInUser != null) {
                        GlobalData.loggedInUser!!.getTotalPrice() != 0f
                    } else
                        false
                ) {
                    Crossfade(targetState = loadingIconVisible) { loadingIconVisible ->
                        if (!loadingIconVisible.value) {
                            Icon(
                                imageVector = Icons.Filled.ShoppingCart,
                                contentDescription = "",
                                modifier = Modifier.padding(end = 8.dp)
                            )
                        } else {
                            Box(
                                modifier = Modifier.padding(end = 8.dp)
                            ) {
                                CircularProgressIndicator(
                                    color = MaterialTheme.colorScheme.onPrimary,
                                    strokeWidth = 2.dp,
                                    modifier = Modifier
                                        .size(MaterialTheme.typography.labelLarge.fontSize.value.dp)
                                )
                            }
                        }
                    }
                    Text(text = stringResource(proceedToPurchase_Button))
                }
            }

            Spacer(modifier = Modifier.height(15.dp))
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