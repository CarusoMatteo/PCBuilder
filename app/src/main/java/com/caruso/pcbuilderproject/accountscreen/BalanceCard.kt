package com.caruso.pcbuilderproject.accountscreen

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddCard
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.caruso.pcbuilderproject.R.string.*
import com.caruso.pcbuilderproject.ui.theme.PCBuilderProjectTheme
import com.caruso.pcbuilderproject.utilities.GlobalData

@Composable
fun BalanceCard(
    modifier: Modifier = Modifier,
    balance: Float,
    addBalanceDialogVisible: MutableState<Boolean>,

    balanceColor: Color = if (isSystemInDarkTheme())
        Color(0xFF00FF00)
    else
        Color(0xFF006400)

) {
    ElevatedCard(modifier = modifier) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 6.dp, end = 6.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Spacer(modifier = Modifier.height(10.dp))

            Text(
                text = stringResource(yourCurrentBalance_Text),
                style = MaterialTheme.typography.headlineMedium,
                fontWeight = FontWeight.Bold
            )

            Spacer(modifier = Modifier.height(5.dp))

            Text(
                text = GlobalData.priceInFloatToString(
                    number = balance,
                    currency = stringResource(currency),
                    decimalPoint = stringResource(decimalPoint)
                ),
                style = MaterialTheme.typography.headlineLarge,
                fontWeight = FontWeight.Bold,
                color = balanceColor
            )

            Spacer(modifier = Modifier.height(10.dp))

            Button(
                onClick = { addBalanceDialogVisible.value = true }
            ) {
                Icon(
                    imageVector = Icons.Filled.AddCard,
                    contentDescription = null,
                    modifier = Modifier.padding(end = 10.dp)
                )
                Text(text = stringResource(addFunds_Button))
            }

            Spacer(modifier = Modifier.height(10.dp))
        }
    }
}

@Preview
@Composable
fun BalanceCardPreview() {
    val darkTheme = false
    PCBuilderProjectTheme(darkTheme = darkTheme) {
        val balanceColor = if (darkTheme)
            Color(0xFF00FF00)
        else
            Color(0xFF006400)

        Surface(modifier = Modifier.fillMaxWidth()) {
            Column(
                modifier = Modifier.padding(top = 10.dp, bottom = 10.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                BalanceCard(
                    modifier = Modifier.fillMaxWidth(0.9f),
                    balance = 100.0f,
                    addBalanceDialogVisible = remember { mutableStateOf(false) },
                    balanceColor = balanceColor
                )
            }
        }
    }
}