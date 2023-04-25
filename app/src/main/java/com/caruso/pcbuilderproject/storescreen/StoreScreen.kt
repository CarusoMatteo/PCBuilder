package com.caruso.pcbuilderproject.storescreen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.caruso.pcbuilderproject.R
import com.caruso.pcbuilderproject.classes.GlobalData
import com.caruso.pcbuilderproject.navigation.BottomBarScreen
import com.caruso.pcbuilderproject.ui.theme.PCBuilderProjectTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun StoreScreen(
    snackbarHostState: SnackbarHostState? = null,
    navController: NavHostController? = null
) {
    val context = LocalContext.current

    /*
    If 1: CPU,
    If 2: Motherboard,
    If 3: RAM,
    If 4: GPU,
    If 5: Storage,
    If 6: PSU
     */
    var serverSettingDialogOpen by remember { mutableStateOf(false) }
    var serverLinkTextField by rememberSaveable { mutableStateOf(GlobalData.ngrokServerLink) }
    val scrollBehavior = TopAppBarDefaults.pinnedScrollBehavior()

    Scaffold(
        modifier = Modifier.nestedScroll(scrollBehavior.nestedScrollConnection),

        topBar = {
            TopAppBar(
                title = {
                    Text(text = stringResource(R.string.store_NavBarItem))
                },
                scrollBehavior = scrollBehavior,
                navigationIcon = {
                    Icon(
                        imageVector = BottomBarScreen.StoreScreen.filledIcon,
                        contentDescription = "Menu",
                        modifier = Modifier.padding(start = 16.dp, end = 16.dp)
                    )
                },
                actions = {
                    IconButton(
                        onClick = { serverSettingDialogOpen = true }
                    ) {
                        Icon(
                            imageVector = Icons.Filled.Settings,
                            contentDescription = "View server settings"
                        )
                    }
                }
            )
        }
    ) { paddingValues ->

        when (GlobalData.getStoreProductTypeSelected()) {
            1 -> CPUScoreScreen(paddingValues = paddingValues)
            2 -> MotherboardStoreScreen(paddingValues = paddingValues)

            else -> {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(MaterialTheme.colorScheme.background),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = "INCORRECT STORE INDEX",
                        fontSize = MaterialTheme.typography.headlineLarge.fontSize,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.onBackground
                    )
                }
            }
        }
    }

    if (serverSettingDialogOpen) {
        AlertDialog(
            onDismissRequest = {
                serverSettingDialogOpen = false
                serverLinkTextField = GlobalData.ngrokServerLink
            },
            title = { Text(text = stringResource(R.string.serverSettings_AlertTitle)) },
            text = {
                Column(Modifier.fillMaxWidth()) {
                    Text(text = stringResource(R.string.currentLink_Text) + " " + GlobalData.ngrokServerLink)
                    Spacer(modifier = Modifier.height(10.dp))
                    FilledTonalButton(
                        onClick = {
                            GlobalData.copyToClipboard(
                                context = context,
                                text = GlobalData.ngrokServerLinkPrefix +
                                        GlobalData.ngrokServerLink +
                                        GlobalData.ngrokServerLinkSuffix
                            )
                        },
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Icon(
                            imageVector = Icons.Filled.ContentCopy,
                            contentDescription = "Copy link",
                            modifier = Modifier.padding(end = 8.dp)
                        )
                        Text(
                            text = stringResource(R.string.copyCurrentServerLink_Button)
                        )
                    }

                    Spacer(modifier = Modifier.height(15.dp))
                    Divider(color = MaterialTheme.colorScheme.onSurface)
                    Spacer(modifier = Modifier.height(15.dp))

                    Spacer(modifier = Modifier.height(6.dp))
                    OutlinedTextField(
                        value = serverLinkTextField,
                        onValueChange = {
                            serverLinkTextField = it
                        },
                        label = { Text(text = stringResource(R.string.insertTheNgrokServerLink_AlertMessage)) },
                        singleLine = true
                    )
                }
            },
            confirmButton = {
                TextButton(
                    enabled = serverLinkTextField.isNotEmpty(),
                    onClick = {
                        serverSettingDialogOpen = false
                        GlobalData.ngrokServerLink = serverLinkTextField
                    }) {
                    Text(text = stringResource(R.string.confirm_Button))
                }
            },
            dismissButton = {
                TextButton(onClick = {
                    serverSettingDialogOpen = false
                    serverLinkTextField = GlobalData.ngrokServerLink
                }) {
                    Text(text = stringResource(R.string.cancel_Button))
                }
            }
        )
    }
}

@Preview
@Composable
fun StoreScreenPreview() {
    PCBuilderProjectTheme(darkTheme = true) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.background)
        ) {
            StoreScreen()
        }
    }
}