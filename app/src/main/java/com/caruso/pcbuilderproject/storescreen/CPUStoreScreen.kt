package com.caruso.pcbuilderproject.storescreen

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.caruso.pcbuilderproject.R
import com.caruso.pcbuilderproject.classes.CPU

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