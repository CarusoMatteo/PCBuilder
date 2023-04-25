package com.caruso.pcbuilderproject.partslistscreen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import com.caruso.pcbuilderproject.classes.GlobalData
import com.caruso.pcbuilderproject.ui.theme.PCBuilderProjectTheme

@Composable
fun PartsListScreen(
    snackbarHostState: SnackbarHostState? = null,
    navController: NavHostController? = null
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background),
        contentAlignment = Alignment.Center
    ) {
        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {


            Text(
                text = "PARTS LIST",
                fontSize = MaterialTheme.typography.headlineLarge.fontSize,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.onBackground
            )

            Row(
                modifier = Modifier.fillMaxWidth(0.9f),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                Button(onClick = {
                    if (navController != null) {
                        GlobalData.changeStoreProductTypeSelected(
                            newValue = 1,
                            navController = navController
                        )
                    }
                }) {
                    Text(text = "Go to CPU")
                }

                Button(onClick = {
                    if (navController != null) {
                        GlobalData.changeStoreProductTypeSelected(
                            newValue = 2,
                            navController = navController
                        )
                    }
                }) {
                    Text(text = "Go to Motherboard")
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PartsListScreenPreview() {
    PCBuilderProjectTheme(darkTheme = true) {
        PartsListScreen()
    }
}