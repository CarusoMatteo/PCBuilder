package com.caruso.pcbuilderproject.storescreen

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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.caruso.pcbuilderproject.R
import com.caruso.pcbuilderproject.R.string.*
import com.caruso.pcbuilderproject.classes.*
import com.caruso.pcbuilderproject.dialogs.CPUFilterDialog
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
    val filterCardHidden = remember { mutableStateOf(false) }
    val filterDialogOpen = remember { mutableStateOf(false) }
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

        when (storeProductTypeSelected) {
            1 -> {
                ComponentStoreScreen(
                    paddingValues = paddingValues,
                    filterCardHidden = filterCardHidden,
                    filterDialogOpen = filterDialogOpen,
                    navController = navController,
                    snackbarHostState = snackbarHostState,
                    components = mutableListOf(
                        CPU(
                            id = 1,
                            brand = "AMD",
                            series = "Ryzen 7",
                            name = "7800X3D",
                            price = 520.99f,
                            coreNumber = 8,
                            baseClockSpeed = 4.2f,
                            powerConsumption = 120,
                            architecture = "Zen 4",
                            socket = "AM5",
                            integratedGraphics = true,
                            fanIncluded = false,
                            imagePainter = painterResource(id = R.drawable.cpu_placeholder)
                        ),
                        CPU(
                            id = 2,
                            brand = "Intel",
                            series = "Core i7",
                            name = "13700K",
                            price = 500.99f,
                            coreNumber = 16,
                            baseClockSpeed = 3.4f,
                            powerConsumption = 125,
                            architecture = "Rocket Lake",
                            socket = "LGA1700",
                            integratedGraphics = true,
                            fanIncluded = false,
                            imagePainter = painterResource(id = R.drawable.cpu_placeholder)
                        )
                    ),
                    componentsType = stringResource(cpu_Text)
                )
            }

            2 -> {
                ComponentStoreScreen(
                    paddingValues = paddingValues,
                    filterCardHidden = filterCardHidden,
                    filterDialogOpen = filterDialogOpen,
                    navController = navController,
                    snackbarHostState = snackbarHostState,
                    components = mutableListOf(
                        Motherboard(
                            id = 1,
                            brand = "MSI",
                            name = "MPG Z790 CARBON",
                            price = 350.72f,
                            imagePainter = painterResource(id = R.drawable.motherboard_placeholder),
                            socket = "LGA1700",
                            chipset = "Z790",
                            formFactor = "ATX",
                            memoryType = "DDR4",
                            memorySlotNumber = 4,
                            maxEthernetSpeed = 2.5f,
                            wifiVersion = null,
                            bluetoothVersion = null,
                            pcie_x16_5_slotNumber = 1,
                            pcie_x16_4_slotNumber = 1,
                            pcie_x8_4_slotNumber = 0,
                            pcie_x4_4_slotNumber = 0,
                            pcie_x1_4_slotNumber = 0,
                            m2_nvme_5_slotNumber = 1,
                            m2_nvme_4_slotNumber = 4,
                            m2_sata_slotNumber = 0,
                            sata_portNumber = 6,
                            usb_a_2_headerNumber = 2,
                            usb_a_32_gen1_headerNumber = 1,
                            usb_c_32_gen2_headerNumber = 1
                        ),
                        Motherboard(
                            id = 1,
                            brand = "MSI",
                            name = "MPG Z790 CARBON",
                            price = 350.72f,
                            imagePainter = painterResource(id = R.drawable.motherboard_placeholder),
                            socket = "LGA1700",
                            chipset = "Z790",
                            formFactor = "ATX",
                            memoryType = "DDR4",
                            memorySlotNumber = 4,
                            maxEthernetSpeed = 2.5f,
                            wifiVersion = null,
                            bluetoothVersion = null,
                            pcie_x16_5_slotNumber = 1,
                            pcie_x16_4_slotNumber = 1,
                            pcie_x8_4_slotNumber = 0,
                            pcie_x4_4_slotNumber = 0,
                            pcie_x1_4_slotNumber = 0,
                            m2_nvme_5_slotNumber = 1,
                            m2_nvme_4_slotNumber = 4,
                            m2_sata_slotNumber = 0,
                            sata_portNumber = 6,
                            usb_a_2_headerNumber = 2,
                            usb_a_32_gen1_headerNumber = 1,
                            usb_c_32_gen2_headerNumber = 1
                        )
                    ),
                    componentsType = stringResource(motherboard_Text)
                )
            }

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

    if (filterDialogOpen.value) {
        when (storeProductTypeSelected) {
            1 -> CPUFilterDialog(
                filterDialogOpen = filterDialogOpen
            )
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
            StoreScreen()
        }
    }
}