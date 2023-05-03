package com.caruso.pcbuilderproject.specslist

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.caruso.pcbuilderproject.ui.theme.PCBuilderProjectTheme


@Composable
fun SpecListItem(
    modifier: Modifier = Modifier,
    leftItem: String,
    rightItem: String,
    unitOfMeasurement: String? = null
) {
    Row(
        modifier = Modifier.padding(end = 16.dp, bottom = 10.dp)
                then modifier
    ) {
        Text(
            text = leftItem,
            fontStyle = MaterialTheme.typography.headlineSmall.fontStyle,
            fontWeight = FontWeight.Bold
        )
        Row(
            horizontalArrangement = Arrangement.End,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(
                text = rightItem +
                        if (unitOfMeasurement != null)
                            " $unitOfMeasurement"
                        else
                            "",
                fontStyle = MaterialTheme.typography.headlineSmall.fontStyle,
            )
        }
    }
}

@Preview
@Composable
fun SpecsListItemPreview() {
    PCBuilderProjectTheme(darkTheme = true) {
        Card {
            SpecListItem(leftItem = "Power consumption", rightItem = "120", unitOfMeasurement = "W")
        }
    }
}