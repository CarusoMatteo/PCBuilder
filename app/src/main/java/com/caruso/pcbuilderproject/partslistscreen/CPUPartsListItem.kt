package com.caruso.pcbuilderproject.partslistscreen

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import com.caruso.pcbuilderproject.R
import com.caruso.pcbuilderproject.classes.CPU
import com.caruso.pcbuilderproject.classes.GlobalData
import com.caruso.pcbuilderproject.ui.theme.PCBuilderProjectTheme

@Composable
fun CPUPartsListItem(
    cpu: CPU? = GlobalData.loggedInUser.cpuSelected
) {
    if (cpu != null) {
        // Card with details
    } else {
        // Card with empty details
    }
}

@Preview
@Composable
fun CPUPartsListItemPreview() {
    PCBuilderProjectTheme(darkTheme = true) {
        Surface(modifier = Modifier.fillMaxSize()) {
            CPUPartsListItem(
                cpu = CPU(
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
        }
    }
}