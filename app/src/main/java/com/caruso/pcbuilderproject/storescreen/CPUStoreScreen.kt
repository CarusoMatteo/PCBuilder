package com.caruso.pcbuilderproject.storescreen

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.FilterList
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.caruso.pcbuilderproject.R
import com.caruso.pcbuilderproject.classes.CPU
import com.caruso.pcbuilderproject.ui.theme.PCBuilderProjectTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CPUScoreScreen(
    paddingValues: PaddingValues
) {
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
                            // TODO
                        }
                    ) {
                        Icon(
                            imageVector = Icons.Default.FilterList,
                            contentDescription = "Open filter list"
                        )
                    }

                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .horizontalScroll(rememberScrollState())
                    ) {
                        FilterChip(
                            selected = true,
                            label = { Text(text = "Brand: AMD") },
                            leadingIcon = {
                                Icon(
                                    imageVector = Icons.Default.Check,
                                    contentDescription = null
                                )
                            },
                            onClick = { /*TODO*/ },
                            modifier = Modifier.padding(end = 5.dp)
                        )

                        FilterChip(
                            selected = true,
                            label = { Text(text = "Series: Ryzen 7") },
                            leadingIcon = {
                                Icon(
                                    imageVector = Icons.Default.Check,
                                    contentDescription = null
                                )
                            },
                            onClick = { /*TODO*/ },
                            modifier = Modifier.padding(end = 5.dp)
                        )

                        FilterChip(
                            selected = true,
                            label = { Text(text = "Core number: 8") },
                            leadingIcon = {
                                Icon(
                                    imageVector = Icons.Default.Check,
                                    contentDescription = null
                                )
                            },
                            onClick = { /*TODO*/ },
                            modifier = Modifier.padding(end = 5.dp)
                        )

                        FilterChip(
                            selected = true,
                            label = { Text(text = "Base clock speed: 3.4") },
                            leadingIcon = {
                                Icon(
                                    imageVector = Icons.Default.Check,
                                    contentDescription = null
                                )
                            },
                            onClick = { /*TODO*/ },
                            modifier = Modifier.padding(end = 5.dp)
                        )
                    }
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