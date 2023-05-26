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
import com.caruso.pcbuilderproject.componentsclasses.Psu
import com.caruso.pcbuilderproject.specslist.SpecsListItem
import com.caruso.pcbuilderproject.ui.theme.PCBuilderProjectTheme

@Composable
fun PSUSpecs(
    psu: Psu
) {
    Column(modifier = Modifier.fillMaxWidth()) {
        SpecsListItem(
            leftItem = stringResource(brand_Text),
            rightItem = psu.brand
        )
        Divider(
            Modifier.padding(bottom = 10.dp, end = 16.dp),
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )
        SpecsListItem(
            leftItem = stringResource(wattage_Text),
            rightItem = psu.wattage.toString(),
            unitOfMeasurement = "W"
        )
        Divider(
            Modifier.padding(bottom = 10.dp, end = 16.dp),
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )
        SpecsListItem(
            leftItem = stringResource(formFactor_Text),
            rightItem = psu.formFactor,
        )
        Divider(
            Modifier.padding(bottom = 10.dp, end = 16.dp),
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )
        SpecsListItem(
            leftItem = stringResource(length_Text),
            rightItem = psu.length.toString(),
            unitOfMeasurement = "mm"
        )
    }
}

@Preview
@Composable
fun PSUSpecsPreview() {
    PCBuilderProjectTheme(darkTheme = true) {
        Card(
            modifier = Modifier
                .fillMaxWidth(0.9f)
        ) {
            Column(modifier = Modifier.padding(start = 16.dp, end = 16.dp)) {
                PSUSpecs(
                    psu = Psu(
                        id = 1,
                        brand = "Corsair",
                        name = "RM850x",
                        price = 120f,
                        defaultImagePainterId = R.drawable.psu_placeholder,
                        imagePainterLink = null,
                        wattage = 850,
                        formFactor = "ATX",
                        length = 160
                    )
                )
            }
        }
    }
}