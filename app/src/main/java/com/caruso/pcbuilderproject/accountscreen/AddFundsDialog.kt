package com.caruso.pcbuilderproject.accountscreen

import android.util.Log
import androidx.compose.animation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddCard
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.caruso.pcbuilderproject.R.string.*
import com.caruso.pcbuilderproject.ui.theme.PCBuilderProjectTheme
import com.caruso.pcbuilderproject.utilities.GlobalData
import com.caruso.pcbuilderproject.utilities.ServerFunctions
import kotlin.math.absoluteValue

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddFundsDialog(
    addFundsDialogDialogOpen: MutableState<Boolean>,
    balance: MutableState<Float>,
    snackbarHostState: SnackbarHostState?
) {
    val context = LocalContext.current
    val scope = rememberCoroutineScope()

    var addFundsTextField by rememberSaveable { mutableStateOf("") }
    val snackbarMessage = remember { mutableStateOf("") }

    AlertDialog(
        onDismissRequest = {
            addFundsDialogDialogOpen.value = false
        },
        title = { Text(text = stringResource(addFunds_Text)) },
        text = {
            Column(Modifier.fillMaxWidth()) {
                Text(text = stringResource(writeTheAmountOfFundsToAddTtoYourAaccount_Text))

                OutlinedTextField(
                    value = addFundsTextField,
                    onValueChange = {
                        addFundsTextField = when {
                            it == "" -> {
                                it
                            }
                            // If a comma or a dot has been entered as the first char
                            it.startsWith(",") || it.startsWith(".") ||
                                    it.startsWith("-") -> {
                                ""
                            }
                            // If a space or a dash is entered
                            it.endsWith(" ") || it.endsWith("-") -> {
                                it.dropLast(1)
                            }
                            // If a dot has been entered and...
                            it.last() == '.' -> {
                                // ... there is already a comma or a dot
                                if (addFundsTextField.contains(',') ||
                                    addFundsTextField.contains('.')
                                )
                                    it.dropLast(1)
                                else
                                    it
                            }
                            // If a comma has been entered and...
                            it.last() == ',' -> {
                                // ... there is already a dot or a comma
                                if (addFundsTextField.contains('.') ||
                                    addFundsTextField.contains(',')
                                )
                                    it.dropLast(1)
                                else
                                    it
                            }

                            else -> {
                                it
                            }
                        }
                    },
                    singleLine = true,
                    label = {
                        Text(text = stringResource(foundsToAdd_Text))
                    },
                    leadingIcon = {
                        Text(text = stringResource(currency))
                    },
                    placeholder = { Text(text = "10" + stringResource(decimalPoint) + "00") },
                    modifier = Modifier.padding(vertical = 5.dp),
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                )
            }
        },
        confirmButton = {
            TextButton(
                enabled = addFundsTextField.isNotEmpty(),
                onClick = {
                    Thread {
                        if (snackbarHostState != null) {
                            try {
                                ServerFunctions.updateBalance(
                                    fundsToAdd = addFundsTextField.replace(',', '.')
                                        .toFloat().absoluteValue,
                                    currentFunds = balance,
                                    context = context,
                                    snackbarMessage = snackbarMessage,
                                    snackbarHostState = snackbarHostState,
                                    scope = scope
                                )
                            } catch (e: Exception) {
                                if (e.message != null)
                                    Log.e("Add Funds Dialog", "Error is " + e.message)
                                else
                                    Log.e(
                                        "Add Funds Dialog",
                                        "An unknown error occurred.\nIt's likely an impossible string to float conversion."
                                    )
                            }
                        }
                        addFundsDialogDialogOpen.value = false
                    }.start()
                }) {
                Text(text = stringResource(confirm_Button))
            }
        },
        dismissButton = {
            TextButton(onClick = {
                addFundsDialogDialogOpen.value = false
                addFundsTextField = GlobalData.ngrokServerLink
            }) {
                Text(text = stringResource(cancel_Button))
            }
        },
        icon = {
            Icon(imageVector = Icons.Filled.AddCard, contentDescription = null)
        }
    )
}

@Preview
@Composable
fun AddFundsDialogPreview() {
    PCBuilderProjectTheme(darkTheme = true) {
        Surface(modifier = Modifier.fillMaxSize()) {
            AddFundsDialog(
                addFundsDialogDialogOpen = remember { mutableStateOf(true) },
                balance = remember { mutableStateOf(0f) },
                snackbarHostState = null
            )
        }
    }
}