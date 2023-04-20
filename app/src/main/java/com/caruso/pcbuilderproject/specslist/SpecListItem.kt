package com.caruso.pcbuilderproject.specslist

import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp


@Composable
fun SpecListItem(
        modifier: Modifier = Modifier,
        leftItem: String,
        rightItem: String
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
        Box(
                contentAlignment = Alignment.CenterEnd,
                modifier = Modifier.fillMaxWidth()
        ) {
            Text(
                    text = rightItem,
                    fontStyle = MaterialTheme.typography.headlineSmall.fontStyle,
            )
        }
    }
}