package com.caruso.pcbuilderproject.accountscreen

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.caruso.pcbuilderproject.R.string.orderHistory_Text
import com.caruso.pcbuilderproject.ui.theme.PCBuilderProjectTheme
import com.caruso.pcbuilderproject.utilities.GlobalData

@Composable
fun ProfileScreen(
    modifier: Modifier = Modifier,
    paddingValues: PaddingValues = PaddingValues(all = 0.dp),
    snackbarHostState: SnackbarHostState?
) {
    val addBalanceDialogVisible = remember { mutableStateOf(false) }

    var balanceValue = 0f
    if (GlobalData.loggedInUser?.balance != null) {
        balanceValue = GlobalData.loggedInUser?.balance!!
    }

    val balance: MutableState<Float> = remember { mutableStateOf(balanceValue) }

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(paddingValues)
                then modifier,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        item {
            Spacer(Modifier.height(10.dp))

            BalanceCard(
                modifier = Modifier.fillMaxWidth(0.9f),
                balance = balance.value,
                addBalanceDialogVisible = addBalanceDialogVisible
            )

            Spacer(Modifier.height(20.dp))

            Box(
                modifier = Modifier.fillMaxWidth(0.9f),
                contentAlignment = Alignment.CenterStart
            ) {
                Text(
                    text = stringResource(orderHistory_Text),
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

                Spacer(Modifier.height(10.dp))
            }
        }
    }

    if (addBalanceDialogVisible.value) {
        AddFundsDialog(
            addFundsDialogDialogOpen = addBalanceDialogVisible,
            balance = balance,
            snackbarHostState = snackbarHostState
        )
    }
}

@Preview
@Composable
fun ProfileScreenPreview() {
    PCBuilderProjectTheme {
        Surface(modifier = Modifier.fillMaxSize()) {
            ProfileScreen(
                snackbarHostState = null
            )
        }
    }
}