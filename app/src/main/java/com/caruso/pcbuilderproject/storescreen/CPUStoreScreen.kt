package com.caruso.pcbuilderproject.storescreen

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.FilterList
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import com.caruso.pcbuilderproject.R
import com.caruso.pcbuilderproject.R.string.*
import com.caruso.pcbuilderproject.classes.*
import com.caruso.pcbuilderproject.dialogs.CPUFilterDialog
import com.caruso.pcbuilderproject.navigation.BottomBarScreen
import com.caruso.pcbuilderproject.ui.theme.PCBuilderProjectTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CPUScoreScreen(
    paddingValues: PaddingValues,
    navController: NavHostController? = null,
) {
    val filterDialogOpen = remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(paddingValues)
            .padding(bottom = 80.dp)//, start = 16.dp, end = 16.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState()),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.height(10.dp))

            ElevatedCard(
                modifier = Modifier.fillMaxWidth(0.9f)
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
                    Spacer(modifier = Modifier.width(5.dp))
                }
            }

            Spacer(modifier = Modifier.height(10.dp))

            CPUProductCard(
                modifier = Modifier.fillMaxWidth(0.9f),
                nameSize = MaterialTheme.typography.titleMedium,
                product = CPU(
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
                )
            )

            Spacer(modifier = Modifier.height(10.dp))

            CPUProductCard(
                modifier = Modifier.fillMaxWidth(0.9f),
                nameSize = MaterialTheme.typography.titleMedium,
                product = CPU(
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
            )

            Spacer(modifier = Modifier.height(10.dp))

        }
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