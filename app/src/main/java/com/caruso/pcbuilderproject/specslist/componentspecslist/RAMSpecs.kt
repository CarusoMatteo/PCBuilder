package com.caruso.pcbuilderproject.specslist.componentspecslist

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.caruso.pcbuilderproject.R.drawable.ram_placeholder
import com.caruso.pcbuilderproject.R.string.*
import com.caruso.pcbuilderproject.componentsclasses.Ram
import com.caruso.pcbuilderproject.specslist.SpecsListItem
import com.caruso.pcbuilderproject.ui.theme.PCBuilderProjectTheme

@Composable
fun RAMSpecs(
    ram: Ram
) {
    Column(modifier = Modifier.fillMaxWidth()) {
        SpecsListItem(
            leftItem = stringResource(memoryType_Text),
            rightItem = ram.memoryType
        )
        Divider(
            Modifier.padding(bottom = 10.dp, end = 16.dp),
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )
        SpecsListItem(
            leftItem = stringResource(memorySpeed_Text),
            rightItem = ram.memorySpeed.toString(),
            unitOfMeasurement = "MT/s"
        )
        Divider(
            Modifier.padding(bottom = 10.dp, end = 16.dp),
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )
        SpecsListItem(
            leftItem = stringResource(totalSize_Text),
            rightItem = ram.totalSize.toString(),
            unitOfMeasurement = "GB"
        )
        Divider(
            Modifier.padding(bottom = 10.dp, end = 16.dp),
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )
        SpecsListItem(
            leftItem = stringResource(numberOfSticks_Text),
            rightItem = ram.numberOfSticks.toString()
        )
    }
}

@Preview
@Composable
fun RAMSpecsPreview() {
    PCBuilderProjectTheme(darkTheme = true) {
        Card(
            modifier = Modifier
                .fillMaxWidth(0.9f)
        ) {
            Column(modifier = Modifier.padding(start = 16.dp, end = 16.dp)) {
                RAMSpecs(
                    ram = Ram(
                        id = 1,
                        brand = "Corsair",
                        name = "Name",
                        price = 90.00f,
                        defaultImagePainterId = ram_placeholder,
                        imagePainterLink = null,
                        memoryType = "DDR4",
                        memorySpeed = 3200,
                        totalSize = 32,
                        numberOfSticks = 2
                    )
                )
            }
        }
    }
}