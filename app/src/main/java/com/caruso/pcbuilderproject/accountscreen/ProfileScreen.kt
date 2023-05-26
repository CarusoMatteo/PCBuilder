package com.caruso.pcbuilderproject.accountscreen

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.caruso.pcbuilderproject.ui.theme.PCBuilderProjectTheme
import com.caruso.pcbuilderproject.utilities.GlobalData

@Composable
fun ProfileScreen(
    modifier: Modifier = Modifier,
    paddingValues: PaddingValues = PaddingValues(all = 0.dp),
) {
    val addBalanceDialogVisible = remember { mutableStateOf(false) }

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(paddingValues)
                then modifier,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        item {
            BalanceCard(
                modifier = Modifier.fillMaxWidth(0.9f),
                balance = GlobalData.loggedInUser!!.balance,
                addBalanceDialogVisible = addBalanceDialogVisible
            )

            Spacer(Modifier.height(20.dp))

            Box(
                modifier = Modifier.fillMaxWidth(0.9f),
                contentAlignment = Alignment.CenterStart
            ) {
                Text(
                    text = "Order history",
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold
                )
            }

            Spacer(Modifier.height(20.dp))
        }

        GlobalData.loggedInUser?.let {
            items(items = it.orderHistory) { order ->
                OrderCard(
                    modifier = Modifier.fillMaxWidth(0.9f),
                    order = order
                )

                Spacer(Modifier.height(20.dp))
            }
        }
    }
}

@Preview
@Composable
fun ProfileScreenPreview() {
    PCBuilderProjectTheme {
        Surface(modifier = Modifier.fillMaxSize()) {
            ProfileScreen()
        }
    }
}