package com.caruso.pcbuilderproject.dialogs

import android.os.Handler
import android.os.Looper
import androidx.compose.animation.*
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Assignment
import androidx.compose.material.icons.filled.AssignmentTurnedIn
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.caruso.pcbuilderproject.R.string.*
import com.caruso.pcbuilderproject.utilities.GlobalData
import com.caruso.pcbuilderproject.ui.theme.PCBuilderProjectTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ServerSettingsDialog(
    serverSettingDialogOpen: MutableState<Boolean>
) {
    val context = LocalContext.current

    var serverLinkTextField by rememberSaveable { mutableStateOf(GlobalData.ngrokServerLink) }

    // When 0: Not clicked,
    // When 1: Loading,
    // When 2: Clicked.
    val copyToClipboardClicked = remember { mutableStateOf(0) }

    AlertDialog(
        onDismissRequest = {
            serverSettingDialogOpen.value = false
            serverLinkTextField = GlobalData.ngrokServerLink
        },
        title = { Text(text = stringResource(serverSettings_AlertTitle)) },
        text = {
            Column(Modifier.fillMaxWidth()) {
                Text(text = stringResource(currentLink_Text) + " " + GlobalData.ngrokServerLink)
                Spacer(modifier = Modifier.height(10.dp))
                FilledTonalButton(
                    onClick = {
                        GlobalData.copyToClipboard(
                            context = context,
                            text = GlobalData.ngrokServerLinkPrefix +
                                    GlobalData.ngrokServerLink +
                                    GlobalData.ngrokServerLinkSuffix
                        )
                        copyToClipboardClicked.value = 1

                        Handler(Looper.getMainLooper()).postDelayed({
                            copyToClipboardClicked.value = 2
                        }, 500)
                    },
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Crossfade(
                        targetState = copyToClipboardClicked,
                        animationSpec = tween(1000)
                    ) { copyToClipboardClickedTrigger ->
                        when (copyToClipboardClickedTrigger.value) {
                            0 -> Icon(
                                imageVector = Icons.Filled.Assignment,
                                contentDescription = "Copy link",
                                modifier = Modifier.padding(end = 8.dp)
                            )

                            1 -> {
                                Box(
                                    modifier = Modifier.padding(end = 8.dp)
                                ) {
                                    CircularProgressIndicator(
                                        color = MaterialTheme.colorScheme.onSecondaryContainer,
                                        strokeWidth = 2.dp,
                                        modifier = Modifier
                                            .size(MaterialTheme.typography.labelLarge.fontSize.value.dp)
                                    )
                                }
                            }

                            2 -> {
                                Icon(
                                    imageVector = Icons.Filled.AssignmentTurnedIn,
                                    contentDescription = "Copy link",
                                    modifier = Modifier.padding(end = 8.dp)
                                )
                            }
                        }
                    }
                    Crossfade(
                        targetState = copyToClipboardClicked,
                        animationSpec = tween(1000)
                    ) { copyToClipboardClickedTrigger ->
                        when (copyToClipboardClickedTrigger.value) {
                            2 -> {
                                Text(
                                    text = stringResource(copiedLink_Button)
                                )
                            }

                            else -> {
                                Text(
                                    text = stringResource(copyCurrentServerLink_Button)
                                )
                            }
                        }
                    }
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
                    label = { Text(text = stringResource(insertTheNgrokServerLink_AlertMessage)) },
                    singleLine = true
                )
            }
        },
        confirmButton = {
            TextButton(
                enabled = serverLinkTextField.isNotEmpty(),
                onClick = {
                    serverSettingDialogOpen.value = false
                    GlobalData.ngrokServerLink = serverLinkTextField
                }) {
                Text(text = stringResource(confirm_Button))
            }
        },
        dismissButton = {
            TextButton(onClick = {
                serverSettingDialogOpen.value = false
                serverLinkTextField = GlobalData.ngrokServerLink
            }) {
                Text(text = stringResource(cancel_Button))
            }
        }
    )
}

@Preview
@Composable
fun ServerSettingsDialogPreview() {
    PCBuilderProjectTheme(darkTheme = true) {
        Surface(modifier = Modifier.fillMaxSize()) {
            ServerSettingsDialog(
                serverSettingDialogOpen = remember { mutableStateOf(true) }
            )
        }
    }
}