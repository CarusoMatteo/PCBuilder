package com.caruso.pcbuilderproject.accountscreen

import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.caruso.pcbuilderproject.R.string.currency
import com.caruso.pcbuilderproject.R.string.decimalPoint
import com.caruso.pcbuilderproject.componentsclasses.Component
import com.caruso.pcbuilderproject.utilities.GlobalData

@Composable
fun OrderCardListItem(
    modifier: Modifier = Modifier,
    component: Component
) {
    Row(
        modifier = Modifier.fillMaxWidth()
                then modifier,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            text = component.toStringDropType(),
            maxLines = 1,
            fontStyle = MaterialTheme.typography.headlineSmall.fontStyle,
            fontWeight = FontWeight.Bold,
            overflow = TextOverflow.Ellipsis,
            modifier = Modifier.weight(1f, fill = false)
        )
        Row(
            horizontalArrangement = Arrangement.End,
            modifier = Modifier.padding(start = 5.dp)
        ) {
            Text(
                text = GlobalData.priceInFloatToString(
                    number = component.price,
                    currency = stringResource(currency),
                    decimalPoint = stringResource(decimalPoint)
                ),
                maxLines = 1,
                overflow = TextOverflow.Visible,
            )
        }
    }
}

@Preview
@Composable
fun OrderCardListItemPreview() {
    OrderCardPreview()
}