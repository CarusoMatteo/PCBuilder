package com.caruso.pcbuilderproject.accountscreen

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import com.caruso.pcbuilderproject.navigation.BottomBarScreen
import com.caruso.pcbuilderproject.ui.theme.PCBuilderProjectTheme
import com.caruso.pcbuilderproject.utilities.GlobalData

@Composable
fun ProfileScreen(
    modifier: Modifier = Modifier,
    paddingValues: PaddingValues = PaddingValues(all = 0.dp),
    // snackbarHostState: SnackbarHostState? = null,
    navController: NavHostController? = null
) {
    val loadingIconOnButtonVisible = remember {
        mutableStateOf(false)
    }

    val context = LocalContext.current

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(paddingValues)
                then modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = "User currently logged in: " + GlobalData.loggedInUser,
            fontSize = MaterialTheme.typography.headlineLarge.fontSize,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.onBackground
        )
        Button(onClick = {
            GlobalData.logout(context = context)

            navController?.navigate(BottomBarScreen.AccountScreen.route) {
                popUpTo(id = navController.graph.findStartDestination().id)
                launchSingleTop = true
            }
        }) {
            AnimatedVisibility(
                visible = loadingIconOnButtonVisible.value
            ) {
                Box(
                    modifier = Modifier.padding(end = 8.dp)
                ) {
                    CircularProgressIndicator(
                        color = MaterialTheme.colorScheme.onPrimary,
                        strokeWidth = 2.dp,
                        modifier = Modifier
                            .size(MaterialTheme.typography.labelLarge.fontSize.value.dp)
                    )
                }
            }

            Text(
                text = "Exit"
            )
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