package com.caruso.pcbuilderproject.specslist.componentspecslist

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.caruso.pcbuilderproject.R.drawable.storage_placeholder
import com.caruso.pcbuilderproject.R.string.*
import com.caruso.pcbuilderproject.componentsclasses.Storage
import com.caruso.pcbuilderproject.specslist.SpecsListItem
import com.caruso.pcbuilderproject.ui.theme.PCBuilderProjectTheme

@Composable
fun StorageSpecs(
    storage: Storage
) {
    Column(modifier = Modifier.fillMaxWidth()) {
        SpecsListItem(
            leftItem = stringResource(brand_Text),
            rightItem = storage.brand
        )
        Divider(
            Modifier.padding(bottom = 10.dp, end = 16.dp),
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )
        SpecsListItem(
            leftItem = stringResource(storageType_Text),
            rightItem = storage.storageType,
        )
        Divider(
            Modifier.padding(bottom = 10.dp, end = 16.dp),
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )
        SpecsListItem(
            leftItem = stringResource(storageSize_Text),
            rightItem = storage.storageSize.toString(),
            unitOfMeasurement = "GB"
        )
    }
}

@Preview
@Composable
fun StorageSpecsPreview() {
    PCBuilderProjectTheme(darkTheme = true) {
        Card(
            modifier = Modifier
                .fillMaxWidth(0.9f)
        ) {
            Column(modifier = Modifier.padding(start = 16.dp, end = 16.dp)) {
                StorageSpecs(
                    storage = Storage(
                        id = 1,
                        brand = "Samsung",
                        name = "980 Pro",
                        price = 100f,
                        defaultImagePainterId = storage_placeholder,
                        imagePainterLink = null,
                        storageType = "NVMe M.2 5.0",
                        storageSize = 2000
                    )
                )
            }
        }
    }
}