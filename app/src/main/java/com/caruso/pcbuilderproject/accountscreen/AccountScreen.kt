package com.caruso.pcbuilderproject.accountscreen

import android.content.Context
import android.os.Handler
import android.os.Looper
import android.util.Log
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
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.caruso.pcbuilderproject.R.string.*
import com.caruso.pcbuilderproject.classes.GlobalData
import com.caruso.pcbuilderproject.navigation.BottomBarScreen
import com.caruso.pcbuilderproject.ui.theme.PCBuilderProjectTheme
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import org.json.JSONException
import org.json.JSONObject
import java.net.HttpURLConnection
import java.net.URL


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

    val createAccountDialogOpen = remember { mutableStateOf(false) }



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

                    Text(
                        text = stringResource(login_cardHeader),
                        fontSize = MaterialTheme.typography.headlineMedium.fontSize,
                        modifier = Modifier.padding(16.dp)
                    )

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

                    Row(
                        modifier = Modifier
                            .padding(start = 16.dp, end = 16.dp)
                            .fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(text = stringResource(dontHaveAnAccount_Text))
                        Box(
                            modifier = Modifier
                                .fillMaxWidth(),
                            contentAlignment = Alignment.CenterEnd
                        ) {
                            TextButton(onClick = {
                                createAccountDialogOpen.value = true
                            }) {
                                Text(text = stringResource(createOne_AccountTextButton))
                            }
                        }
                    }

                    Spacer(Modifier.height(16.dp))

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
                                    checkCredentials(
                                        username = usernameTextLogIn.text,
                                        password = passwordTextLogIn.text,
                                        ctx = context,
                                        scope = scope,
                                        snackbarHostState = snackbarHostState,
                                        snackbarMessage = snackbarMessage,
                                        navController = navController
                                    )
                                }

                                if (GlobalData.loggedInUsername != null)
                                    navController?.navigate(BottomBarScreen.AccountScreen.route) {
                                        popUpTo(id = navController.graph.findStartDestination().id)
                                        launchSingleTop = true
                                    }
                            }

                            Handler(Looper.getMainLooper()).postDelayed({
                                loadingIconOnButtonVisible.value = false
                            }, 400)
                        },
                        modifier = Modifier
                            .padding(bottom = 16.dp)
                            .fillMaxWidth(0.8f)
                            .align(Alignment.CenterHorizontally)
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
                            text = stringResource(login_buttonText)
                        )
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

    if (createAccountDialogOpen.value) {
        CreateAccountFullscreenDialog(createAccountDialogOpen = createAccountDialogOpen)
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

/**
 * Searches the Database to see if a user with the specified Username and Password.
 *
 */
private fun checkCredentials(
    username: String,
    password: String,
    ctx: Context,
    scope: CoroutineScope,
    snackbarHostState: SnackbarHostState,
    snackbarMessage: MutableState<String>,
    navController: NavHostController?,
    ngrokLink: String = GlobalData.ngrokServerLinkPrefix
            + GlobalData.ngrokServerLink
            + GlobalData.ngrokServerLinkSuffix
) {
    @Suppress("SpellCheckingInspection") val url =
        "$ngrokLink/ControllaAccessoJson.php?Username=$username&Password=$password"


    val queue = Volley.newRequestQueue(ctx)

    val request: StringRequest =
        object : StringRequest(
            url,
            Response.Listener { response ->
                try {
                    val jsonObject = JSONObject(response)

                    val result = jsonObject.getString("ris")

                    if (result == "1") {
//snackbarMessage.value = ctx.getString(correctCredentials_SnackbarMessage)
                        GlobalData.loggedInUsername = username

                        navController?.navigate(BottomBarScreen.AccountScreen.route) {
                            popUpTo(id = navController.graph.findStartDestination().id)
                            launchSingleTop = true
                        }
                    } else {
                        snackbarMessage.value = ctx.getString(accountDoesntExists)
                        GlobalData.loggedInUsername = null // Shouldn't be needed, just to be safe

                        scope.launch {
                            snackbarHostState.showSnackbar(
                                snackbarMessage.value
                            )
                        }
                    }
                } catch (e: JSONException) {
                    e.printStackTrace()
                }
            },
            Response.ErrorListener { error ->
                Log.e("tag", "error is " + error!!.message)

                // Check if the error was caused by the phone being offline or
                // if the server is inactive.

                Thread {
                    if (!isInternetReachable()) {
                        snackbarMessage.value = ctx.getString(offlineWarning_Message)
                    } else {
                        snackbarMessage.value = ctx.getString(serverError_Message)
                    }

                    scope.launch {
                        snackbarHostState.showSnackbar(
                            snackbarMessage.value
                        )
                    }
                }.start()
            }) {
            override fun getParams(): Map<String, String> {
                val params: MutableMap<String, String> = HashMap()

                params["Username"] = username
                params["Password"] = password

                return params
            }
        }

    queue.add(request)
}

fun isInternetReachable(): Boolean {
    try {
// URL to a known source
        val url = URL("https://www.google.com")

// Open a connection to that source
        val urlConnect: HttpURLConnection = url.openConnection() as HttpURLConnection

// Trying to retrieve data from the source
// If there is no connection, this will trigger an exception
        @Suppress("UNUSED_VARIABLE") val objData: Any = urlConnect.content
    } catch (e: java.lang.Exception) {
        e.printStackTrace()
        return false
    }
    return true
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