package com.caruso.pcbuilderproject.specslist.componentspecslist

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.caruso.pcbuilderproject.R
import com.caruso.pcbuilderproject.R.string.*
import com.caruso.pcbuilderproject.componentsclasses.Cpu
import com.caruso.pcbuilderproject.specslist.SpecsListItem
import com.caruso.pcbuilderproject.ui.theme.PCBuilderProjectTheme

@Composable
fun CPUSpecs(
    cpu: Cpu
) {
    Column(modifier = Modifier.fillMaxWidth()) {
        SpecsListItem(
            leftItem = stringResource(numberOfCores_Text),
            rightItem = cpu.coreNumber.toString()
        )
        Divider(
            Modifier.padding(bottom = 10.dp, end = 16.dp),
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )
        SpecsListItem(
            leftItem = stringResource(baseClockSpeed_Text),
            rightItem = cpu.baseClockSpeed.toString(),
            unitOfMeasurement = "GHz"
        )
        Divider(
            Modifier.padding(bottom = 10.dp, end = 16.dp),
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )
        SpecsListItem(
            leftItem = stringResource(powerConsumption_Text),
            rightItem = cpu.powerConsumption.toString(),
            unitOfMeasurement = "W"
        )
        Divider(
            Modifier.padding(bottom = 10.dp, end = 16.dp),
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )
        SpecsListItem(
            leftItem = stringResource(architecture_Text),
            rightItem = cpu.architecture
        )
        Divider(
            Modifier.padding(bottom = 10.dp, end = 16.dp),
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )
        SpecsListItem(leftItem = stringResource(socket_Text), rightItem = cpu.socket)
        Divider(
            Modifier.padding(bottom = 10.dp, end = 16.dp),
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )
        SpecsListItem(
            leftItem = stringResource(integratedGraphics_Text),
            rightItem = if (cpu.integratedGraphics) stringResource(yes_Text) else stringResource(
                no_Text
            )
        )
        Divider(
            Modifier.padding(bottom = 10.dp, end = 16.dp),
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )
        SpecsListItem(
            leftItem = stringResource(coolerIncluded_Text),
            rightItem = if (cpu.fanIncluded) stringResource(yes_Text) else stringResource(no_Text)
        )
    }
}

@Preview
@Composable
fun CPUSpecsPreview() {
    PCBuilderProjectTheme(darkTheme = true) {
        Card(
            modifier = Modifier
                .fillMaxWidth(0.9f)
        ) {
            Column(modifier = Modifier.padding(start = 16.dp, end = 16.dp)) {
                CPUSpecs(
                    cpu = Cpu(
                        id = 1,
                        brand = "AMD",
                        series = "Ryzen 7",
                        name = "7800X3D",
                        price = 520.99f,
                        coreNumber = 8,
                        baseClockSpeed = 3.4f,
                        powerConsumption = 125,
                        architecture = "Zen 4",
                        socket = "AM5",
                        integratedGraphics = true,
                        fanIncluded = false,
                        defaultImagePainterId = R.drawable.cpu_placeholder,
                        imagePainterLink = null
                    )
                )
            }
        }
    }
}