package com.caruso.pcbuilderproject.accountscreen

import android.content.Context
import android.os.Handler
import android.os.Looper
import androidx.compose.animation.*
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextRange
import androidx.compose.ui.text.input.*
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import com.caruso.pcbuilderproject.R.string.*
import com.caruso.pcbuilderproject.navigation.BottomBarScreen
import com.caruso.pcbuilderproject.ui.theme.PCBuilderProjectTheme
import com.caruso.pcbuilderproject.utilities.GlobalData
import com.caruso.pcbuilderproject.utilities.ServerFunctions
import kotlinx.coroutines.CoroutineScope

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoginScreen(
    modifier: Modifier = Modifier,
    paddingValues: PaddingValues = PaddingValues(all = 0.dp),
    snackbarHostState: SnackbarHostState?,
    navController: NavHostController?,
    context: Context = LocalContext.current,
    scope: CoroutineScope = rememberCoroutineScope()
) {
    val snackbarMessage = remember { (mutableStateOf("")) }

    val creatingAccount = remember {
        mutableStateOf(false)
    }

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

    val loadingIconOnButtonVisible = remember {
        mutableStateOf(false)
    }

    Column(
        modifier = Modifier
            .padding(paddingValues)
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
                then modifier,
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(10.dp))

        Card(
            modifier = Modifier
                .fillMaxWidth(0.9f)

        ) {
            Crossfade(targetState = creatingAccount) { creatingAccountTarget ->
                if (creatingAccountTarget.value) {
                    Text(
                        text = stringResource(createAnAccount_cardHeader),
                        fontSize = MaterialTheme.typography.headlineMedium.fontSize,
                        modifier = Modifier.padding(16.dp)
                    )
                } else {
                    Text(
                        text = stringResource(login_cardHeader),
                        fontSize = MaterialTheme.typography.headlineMedium.fontSize,
                        modifier = Modifier.padding(16.dp)
                    )
                }
            }

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
                    .padding(start = 16.dp, end = 16.dp, bottom = 5.dp)
                    .align(Alignment.Start)
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
                    .padding(start = 16.dp, end = 16.dp)
                    .align(Alignment.Start)
                    .fillMaxWidth()
            )

            Crossfade(targetState = creatingAccount.value) { creatingAccountTarget ->
                Row(
                    modifier = Modifier
                        .padding(start = 16.dp, end = 16.dp)
                        .fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    if (creatingAccountTarget) {
                        Text(
                            stringResource(alreadyHaveAnAccount_Text)
                        )
                    } else {
                        Text(
                            stringResource(dontHaveAnAccount_Text)
                        )
                    }

                    Box(
                        modifier = Modifier
                            .fillMaxWidth(),
                        contentAlignment = Alignment.CenterEnd
                    ) {
                        TextButton(onClick = {
                            creatingAccount.value = !creatingAccount.value
                        }) {
                            Text(
                                text = if (creatingAccountTarget)
                                    stringResource(logIn_TextButton)
                                else
                                    stringResource(createOne_AccountTextButton)
                            )
                        }
                    }
                }
            }

            Spacer(Modifier.height(16.dp))

            Box(
                modifier = Modifier.fillMaxWidth(),
                contentAlignment = Alignment.Center
            ) {
                Button(
                    onClick = {
                        loadingIconOnButtonVisible.value = true

                        if (textFieldsAreEmpty(
                                username = usernameTextLogIn.text,
                                password = passwordTextLogIn.text,
                                usernameErrorLogIn = usernameErrorLogIn,
                                passwordErrorLogIn = passwordErrorLogIn
                            )
                        ) {
                            if (snackbarHostState != null && navController != null) {
                                if (!creatingAccount.value) {
                                    ServerFunctions.checkCredentials(
                                        username = usernameTextLogIn.text,
                                        password = passwordTextLogIn.text,
                                        context = context,
                                        scope = scope,
                                        snackbarHostState = snackbarHostState,
                                        snackbarMessage = snackbarMessage,
                                        navController = navController,
                                        loadingIconVisible = loadingIconOnButtonVisible
                                    )
                                } else {
                                    ServerFunctions.createAccount(
                                        username = usernameTextLogIn.text,
                                        password = passwordTextLogIn.text,
                                        context = context,
                                        scope = scope,
                                        snackbarHostState = snackbarHostState,
                                        snackbarMessage = snackbarMessage,
                                        creatingAccount = creatingAccount,
                                        loadingIconVisible = loadingIconOnButtonVisible
                                    )
                                }
                            }

                            if (GlobalData.loggedInUser != null)
                                navController?.navigate(BottomBarScreen.AccountScreen.route) {
                                    popUpTo(id = navController.graph.findStartDestination().id)
                                    launchSingleTop = true
                                }
                        } else {
                            Handler(Looper.getMainLooper()).postDelayed({
                                loadingIconOnButtonVisible.value = false
                            }, 200)
                        }
                    },
                    modifier = Modifier
                        .padding(bottom = 16.dp)
                        .fillMaxWidth(0.8f)
                ) {
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

                    Crossfade(
                        targetState = creatingAccount
                    ) { creatingAccountTarget ->
                        if (creatingAccountTarget.value) {
                            Text(
                                text = stringResource(create_buttonText)
                            )
                        } else {
                            Text(
                                text = stringResource(login_buttonText)
                            )
                        }
                    }
                }
            }
        }


        Spacer(modifier = Modifier.height(10.dp))

    }
}

private fun textFieldsAreEmpty(
    username: String,
    password: String,
    usernameErrorLogIn: MutableState<Boolean>,
    passwordErrorLogIn: MutableState<Boolean>
): Boolean {
    var boolReturn = true

    if (username.isEmpty()) {
        usernameErrorLogIn.value = true
        boolReturn = false
    } else {
        usernameErrorLogIn.value = false
    }

    if (password.isEmpty()) {
        passwordErrorLogIn.value = true
        boolReturn = false
    } else {
        passwordErrorLogIn.value = false
    }

    return boolReturn
}

@Preview
@Composable
fun LoginScreenPreview() {
    PCBuilderProjectTheme(darkTheme = true) {
        Surface(modifier = Modifier.fillMaxSize()) {
            LoginScreen(
                snackbarHostState = null,
                navController = null
            )
        }
    }
}