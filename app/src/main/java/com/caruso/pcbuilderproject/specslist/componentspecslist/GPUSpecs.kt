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
import com.caruso.pcbuilderproject.componentsclasses.Gpu
import com.caruso.pcbuilderproject.specslist.SpecsListItem
import com.caruso.pcbuilderproject.ui.theme.PCBuilderProjectTheme

@Composable
fun GPUSpecs(
    gpu: Gpu
) {
    Column(modifier = Modifier.fillMaxWidth()) {
        SpecsListItem(
            leftItem = stringResource(brand_Text),
            rightItem = gpu.brand
        )
        Divider(
            Modifier.padding(bottom = 10.dp, end = 16.dp),
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )
        SpecsListItem(
            leftItem = stringResource(chipsetBrand_Text),
            rightItem = gpu.chipsetBrand,
        )
        Divider(
            Modifier.padding(bottom = 10.dp, end = 16.dp),
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )
        SpecsListItem(
            leftItem = stringResource(chipset_Text),
            rightItem = gpu.chipset,
        )
        Divider(
            Modifier.padding(bottom = 10.dp, end = 16.dp),
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )
        SpecsListItem(
            leftItem = stringResource(vramSize_Text),
            rightItem = gpu.vRamSize.toString(),
            unitOfMeasurement = "GB"
        )
        Divider(
            Modifier.padding(bottom = 10.dp, end = 16.dp),
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )
        SpecsListItem(
            leftItem = stringResource(baseClockSpeed_Text),
            rightItem = gpu.clockSpeed.toString(),
            unitOfMeasurement = "GHz"
        )
        Divider(
            Modifier.padding(bottom = 10.dp, end = 16.dp),
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )
        SpecsListItem(
            leftItem = stringResource(length_Text),
            rightItem = gpu.length.toString(),
            unitOfMeasurement = "mm"
        )
        Divider(
            Modifier.padding(bottom = 10.dp, end = 16.dp),
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )
        SpecsListItem(
            leftItem = stringResource(size_Text),
            rightItem = gpu.size.toString(),
            unitOfMeasurement = stringResource(slots_Text)
        )
        Divider(
            Modifier.padding(bottom = 10.dp, end = 16.dp),
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )
        SpecsListItem(
            leftItem = stringResource(powerConsumption_Text),
            rightItem = gpu.powerConsumption.toString(),
            unitOfMeasurement = "W"
        )
        Divider(
            Modifier.padding(bottom = 10.dp, end = 16.dp),
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )
        SpecsListItem(
            leftItem = stringResource(numberOfHdmiPorts_Text),
            rightItem = gpu.hdmiPortNumber.toString()
        )
        Divider(
            Modifier.padding(bottom = 10.dp, end = 16.dp),
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )
        SpecsListItem(
            leftItem = stringResource(numberOfDisplayPorts_Text),
            rightItem = gpu.displayPortNumber.toString()
        )
    }
}

@Preview
@Composable
fun GPUSpecsPreview() {
    PCBuilderProjectTheme(darkTheme = true) {
        Card(
            modifier = Modifier
                .fillMaxWidth(0.9f)
        ) {
            Column(modifier = Modifier.padding(start = 16.dp, end = 16.dp)) {
                GPUSpecs(
                    gpu = Gpu(
                        id = 1,
                        brand = "Asus",
                        name = "TUF",
                        price = 1600f,
                        defaultImagePainterId = R.drawable.gpu_placeholder,
                        imagePainterLink = null,
                        chipsetBrand = "NVIDIA",
                        chipset = "GeForce RTX 4080",
                        vRamSize = 16,
                        clockSpeed = 2.205f,
                        length = 348,
                        size = 4,
                        powerConsumption = 320,
                        hdmiPortNumber = 2,
                        displayPortNumber = 3
                    )
                )
            }
        }
    }
}