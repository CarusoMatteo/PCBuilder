package com.caruso.pcbuilderproject.accountscreen

import androidx.compose.animation.*
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextRange
import androidx.compose.ui.text.input.*
import androidx.compose.ui.tooling.preview.Preview
import com.caruso.pcbuilderproject.R.string.*
import com.caruso.pcbuilderproject.ui.theme.PCBuilderProjectTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CreateAccountFullscreenDialog(
    createAccountDialogOpen: MutableState<Boolean>
) {
    var usernameTextLogIn by rememberSaveable(stateSaver = TextFieldValue.Saver) {
        mutableStateOf(TextFieldValue(text = "", TextRange(0, 7)))
    }

    var passwordTextLogIn by rememberSaveable(stateSaver = TextFieldValue.Saver) {
        mutableStateOf(TextFieldValue(text = "", TextRange(0, 7)))
    }

    val usernameErrorLogIn = remember {
        (mutableStateOf(false))
    }

    val passwordErrorLogIn = remember {
        (mutableStateOf(false))
    }

    val passwordVisibilityIconVisibleLogin =
        passwordTextLogIn.text.isNotEmpty()


    val usernameErrorLogInSupportingLineLogin =
        if (usernameErrorLogIn.value)
            stringResource(requiredField_errorMessage)
        else
            ""

    val passwordErrorLogInSupportingLineLogin =
        if (passwordErrorLogIn.value)
            stringResource(requiredField_errorMessage)
        else
            ""

    var passwordVisibleLogin by remember {
        mutableStateOf(false)
    }

    val passwordVisualTransformationLogin =
        if (passwordVisibleLogin) {
            VisualTransformation.None
        } else {
            PasswordVisualTransformation()
        }

    /*
    Dialog(
        //properties = DialogProperties(usePlatformDefaultWidth = false),
        onDismissRequest = { createAccountDialogOpen.value = false }
    ) {
        //Surface(modifier = Modifier.fillMaxSize()) {
        Scaffold(
            topBar = {
                TopAppBar(
                    title = { Text(text = stringResource(R.string.createAnAccount_Label)) },
                    navigationIcon = {
                        IconButton(onClick = {
                            createAccountDialogOpen.value = false
                        }) {
                            Icon(
                                imageVector = Icons.Default.Close,
                                contentDescription = "Close without saving"
                            )
                        }
                    },
                    actions = {
                        TextButton(onClick = {
                            createAccount()
                            createAccountDialogOpen.value = false
                        }) {
                            Text(text = stringResource(R.string.save_TextButton))
                        }
                    }
                )
            }
        ) { paddingValues ->
            Column(
                modifier = Modifier
                    .padding(paddingValues)
                    .padding(start = 24.dp, end = 24.dp)
            ) {
                OutlinedTextField(
                    value = usernameTextLogIn,
                    onValueChange = { usernameTextLogIn = it },
                    label = {
                        Text(text = stringResource(R.string.enterUsername_textFieldLabel))
                    },
                    isError = usernameErrorLogIn.value,
                    supportingText = {
                        Text(text = usernameErrorLogInSupportingLineLogin)
                    },
                    maxLines = 1,
                    modifier = Modifier
                        .padding(bottom = 5.dp)
                        .fillMaxWidth()
                )

                OutlinedTextField(
                    value = passwordTextLogIn,
                    onValueChange = {
                        passwordTextLogIn = it
                    },
                    label = {
                        Text(text = stringResource(R.string.enterPassword_textFieldLabel))
                    },
                    isError = passwordErrorLogIn.value,
                    supportingText = {
                        Text(text = passwordErrorLogInSupportingLineLogin)
                    },
                    maxLines = 1,
                    trailingIcon = {
                        AnimatedVisibility(
                            visible = passwordVisibilityIconVisibleLogin,
                            enter = fadeIn(
                                animationSpec = tween(150)
                            ),
                            exit = fadeOut(
                                animationSpec = tween(150)
                            )
                        ) {
                            IconButton(
                                onClick = {
                                    passwordVisibleLogin = !passwordVisibleLogin
                                },
                            ) {

                                Crossfade(targetState = passwordVisibleLogin) { passwordVisibleLogin ->
                                    Icon(
                                        imageVector = if (passwordVisibleLogin) {
                                            Icons.Filled.Visibility
                                        } else {
                                            Icons.Filled.VisibilityOff
                                        },

                                        contentDescription = if (passwordVisibleLogin) {
                                            stringResource(R.string.hidePassword_contentDescription)
                                        } else {
                                            stringResource(R.string.showPassword_contentDescription)
                                        }
                                    )
                                }
                            }
                        }
                    },

                    visualTransformation = passwordVisualTransformationLogin,
                    modifier = Modifier
                        .animateContentSize()
                        .padding(bottom = 10.dp)
                        .fillMaxWidth()
                )
            }
        }
        //}
    }

     */

    AlertDialog(
        onDismissRequest = {
            createAccountDialogOpen.value = false
        },
        title = { Text(text = stringResource(id = createAnAccount_Label)) },
        text = {
            Column(Modifier.fillMaxWidth()) {
                OutlinedTextField(
                    value = usernameTextLogIn,
                    onValueChange = { usernameTextLogIn = it },
                    label = {
                        Text(text = stringResource(enterUsername_textFieldLabel))
                    },
                    isError = usernameErrorLogIn.value,
                    supportingText = {
                        Text(text = usernameErrorLogInSupportingLineLogin)
                    },
                    maxLines = 1,
                    modifier = Modifier
                        //.padding(bottom = 5.dp)
                        .fillMaxWidth()
                )

                OutlinedTextField(
                    value = passwordTextLogIn,
                    onValueChange = {
                        passwordTextLogIn = it
                    },
                    label = {
                        Text(text = stringResource(enterPassword_textFieldLabel))
                    },
                    isError = passwordErrorLogIn.value,
                    supportingText = {
                        Text(text = passwordErrorLogInSupportingLineLogin)
                    },
                    maxLines = 1,
                    trailingIcon = {
                        AnimatedVisibility(
                            visible = passwordVisibilityIconVisibleLogin,
                            enter = fadeIn(
                                animationSpec = tween(150)
                            ),
                            exit = fadeOut(
                                animationSpec = tween(150)
                            )
                        ) {
                            IconButton(
                                onClick = {
                                    passwordVisibleLogin = !passwordVisibleLogin
                                },
                            ) {

                                Crossfade(targetState = passwordVisibleLogin) { passwordVisibleLogin ->
                                    Icon(
                                        imageVector = if (passwordVisibleLogin) {
                                            Icons.Filled.Visibility
                                        } else {
                                            Icons.Filled.VisibilityOff
                                        },

                                        contentDescription = if (passwordVisibleLogin) {
                                            stringResource(hidePassword_contentDescription)
                                        } else {
                                            stringResource(showPassword_contentDescription)
                                        }
                                    )
                                }
                            }
                        }
                    },

                    visualTransformation = passwordVisualTransformationLogin,
                    modifier = Modifier
                        .animateContentSize()
                        //.padding(bottom = 10.dp)
                        .fillMaxWidth()
                )


            }
        },
        confirmButton = {
            Button(
                enabled = usernameTextLogIn.text.isNotEmpty() && passwordTextLogIn.text.isNotEmpty(),
                onClick = {
                    createAccount()

                    createAccountDialogOpen.value = false
                }) {
                Text(text = stringResource(confirm_Button))
            }
        },
        dismissButton = {
            TextButton(onClick = {
                createAccountDialogOpen.value = false
            }) {
                Text(text = stringResource(cancel_Button))
            }
        }
    )

}

fun createAccount() {

}

@Preview(showBackground = false)
@Composable
fun CreateAccountFullscreenDialogPreview() {
    PCBuilderProjectTheme(darkTheme = true) {
        CreateAccountFullscreenDialog(
            createAccountDialogOpen = remember { mutableStateOf(true) }
        )
    }
}