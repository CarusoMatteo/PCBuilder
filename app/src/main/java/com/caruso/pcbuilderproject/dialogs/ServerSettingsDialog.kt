package com.caruso.pcbuilderproject.dialogs

import androidx.compose.animation.Crossfade
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AssignmentTurnedIn
import androidx.compose.material.icons.filled.ContentCopy
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.caruso.pcbuilderproject.R
import com.caruso.pcbuilderproject.classes.GlobalData

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ServerSettingsDialog(
    serverSettingDialogOpen: MutableState<Boolean>
) {
    val context = LocalContext.current
    var serverLinkTextField by rememberSaveable { mutableStateOf(GlobalData.ngrokServerLink) }
    val copyToClipboardClicked = remember { mutableStateOf(false) }

    AlertDialog(
        onDismissRequest = {
            serverSettingDialogOpen.value = false
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
                        copyToClipboardClicked.value = true
                    },
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Crossfade(
                        targetState = copyToClipboardClicked,
                        animationSpec = tween(300)
                    ) { copyToClipboardClickedTrigger ->
                        if (!copyToClipboardClickedTrigger.value)
                            Icon(
                                imageVector = Icons.Filled.ContentCopy,
                                contentDescription = "Copy link",
                                modifier = Modifier.padding(end = 8.dp)
                            )
                        else {
                            Icon(
                                imageVector = Icons.Filled.AssignmentTurnedIn,
                                contentDescription = "Copy link",
                                modifier = Modifier.padding(end = 8.dp)
                            )
                        }
                    }
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
                    serverSettingDialogOpen.value = false
                    GlobalData.ngrokServerLink = serverLinkTextField
                }) {
                Text(text = stringResource(R.string.confirm_Button))
            }
        },
        dismissButton = {
            TextButton(onClick = {
                serverSettingDialogOpen.value = false
                serverLinkTextField = GlobalData.ngrokServerLink
            }) {
                Text(text = stringResource(R.string.cancel_Button))
            }
        }
    )
}