package com.caruso.pcbuilderproject.accountscreen

import android.os.Handler
import android.os.Looper
import androidx.compose.animation.*
import androidx.compose.animation.core.tween
import androidx.compose.foundation.*
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
import androidx.compose.ui.text.TextRange
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.*
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import com.caruso.pcbuilderproject.R.string.*
import com.caruso.pcbuilderproject.classes.GlobalData
import com.caruso.pcbuilderproject.classes.ServerFunctions
import com.caruso.pcbuilderproject.navigation.BottomBarScreen
import com.caruso.pcbuilderproject.ui.theme.PCBuilderProjectTheme


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AccountScreen(
    snackbarHostState: SnackbarHostState? = null,
    navController: NavHostController? = null
) {
    val context = LocalContext.current
    val scope = rememberCoroutineScope()

    val scrollBehavior = TopAppBarDefaults.pinnedScrollBehavior()
    val snackbarMessage = remember { (mutableStateOf("")) }

    var serverSettingDialogOpen by remember { mutableStateOf(false) }
    var serverLinkTextField by rememberSaveable { mutableStateOf(GlobalData.ngrokServerLink) }

    Scaffold(
        modifier = Modifier
            .nestedScroll(scrollBehavior.nestedScrollConnection)
            .padding(bottom = 80.dp),
        topBar = {
            TopAppBar(
                title = {
                    if (GlobalData.loggedInUsername == null)
                        Text(text = stringResource(account_NavBarItem))
                    else
                        Text(text = stringResource(welcomeUser_Text) + GlobalData.loggedInUsername + "!")
                },
                scrollBehavior = scrollBehavior,
                navigationIcon = {
                    Icon(
                        imageVector = BottomBarScreen.AccountScreen.filledIcon,
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

        if (GlobalData.loggedInUsername == null) {

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
                    .verticalScroll(rememberScrollState()),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Spacer(modifier = Modifier.height(10.dp))

                Card(
                    modifier = Modifier
                        .fillMaxWidth(0.9f)

                ) {
                    Crossfade(targetState = creatingAccount) { creatingAccount ->
                        Text(
                            text = if (!creatingAccount.value)
                                stringResource(login_cardHeader)
                            else
                                stringResource(createAnAccount_cardHeader),
                            fontSize = MaterialTheme.typography.headlineMedium.fontSize,
                            modifier = Modifier.padding(16.dp)
                        )
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
                            Text(
                                text = if (!creatingAccount.value)
                                    stringResource(dontHaveAnAccount_Text)
                                else
                                    stringResource(alreadyHaveAnAccount_Text)
                            )
                            Box(
                                modifier = Modifier
                                    .fillMaxWidth(),
                                contentAlignment = Alignment.CenterEnd
                            ) {
                                TextButton(onClick = {
                                    creatingAccount.value = !creatingAccount.value
                                }) {
                                    Text(
                                        text = if (!creatingAccountTarget)
                                            stringResource(createOne_AccountTextButton)
                                        else
                                            stringResource(logIn_TextButton)
                                    )
                                }
                            }
                        }
                    }

                    Spacer(Modifier.height(16.dp))

                    Crossfade(targetState = creatingAccount) { creatingAccount ->

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
                                        if (snackbarHostState != null) {
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

                                        if (GlobalData.loggedInUsername != null)
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
                                Text(
                                    text = if (!creatingAccount.value)
                                        stringResource(login_buttonText)
                                    else
                                        stringResource(create_buttonText)
                                )
                            }
                        }
                    }
                }

                Spacer(modifier = Modifier.height(10.dp))

            }
        } else {
            val loadingIconOnButtonVisible = remember {
                mutableStateOf(false)
            }

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Text(
                    text = "User currently logged in: " + GlobalData.loggedInUsername,
                    fontSize = MaterialTheme.typography.headlineLarge.fontSize,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.onBackground
                )
                Button(onClick = {
                    GlobalData.loggedInUsername = null

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
    }

    if (serverSettingDialogOpen) {
        AlertDialog(
            onDismissRequest = {
                serverSettingDialogOpen = false
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
                        },
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Icon(
                            imageVector = Icons.Filled.ContentCopy,
                            contentDescription = "Copy link",
                            modifier = Modifier.padding(end = 8.dp)
                        )
                        Text(
                            text = stringResource(copyCurrentServerLink_Button)
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
                        label = { Text(text = stringResource(insertTheNgrokServerLink_AlertMessage)) },
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
                    Text(text = stringResource(confirm_Button))
                }
            },
            dismissButton = {
                TextButton(onClick = {
                    serverSettingDialogOpen = false
                    serverLinkTextField = GlobalData.ngrokServerLink
                }) {
                    Text(text = stringResource(cancel_Button))
                }
            }
        )
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


@Preview(showBackground = true)
@Composable
fun AccountScreenPreview() {
    PCBuilderProjectTheme(darkTheme = true) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.background)
        ) {
            AccountScreen()
        }
    }
}