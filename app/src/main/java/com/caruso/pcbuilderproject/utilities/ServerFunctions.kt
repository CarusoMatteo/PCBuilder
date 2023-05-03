package com.caruso.pcbuilderproject.utilities

import android.content.Context
import android.util.Log
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.MutableState
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.caruso.pcbuilderproject.R
import com.caruso.pcbuilderproject.R.string.*
import com.caruso.pcbuilderproject.componentsclasses.*
import com.caruso.pcbuilderproject.componentsclasses.ComponentsList.Companion.cpus
import com.caruso.pcbuilderproject.componentsclasses.ComponentsList.Companion.gpus
import com.caruso.pcbuilderproject.componentsclasses.ComponentsList.Companion.motherboards
import com.caruso.pcbuilderproject.componentsclasses.ComponentsList.Companion.psus
import com.caruso.pcbuilderproject.componentsclasses.ComponentsList.Companion.rams
import com.caruso.pcbuilderproject.componentsclasses.ComponentsList.Companion.storages
import com.caruso.pcbuilderproject.navigation.BottomBarScreen
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import org.json.JSONException
import org.json.JSONObject
import java.net.HttpURLConnection
import java.net.URL

abstract class ServerFunctions {
    companion object {
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

        fun checkCredentials(
            username: String,
            password: String,
            context: Context,
            scope: CoroutineScope,
            snackbarHostState: SnackbarHostState,
            snackbarMessage: MutableState<String>,
            navController: NavHostController?,
            loadingIconVisible: MutableState<Boolean>,
            ngrokLink: String = GlobalData.ngrokServerLinkPrefix
                    + GlobalData.ngrokServerLink
                    + GlobalData.ngrokServerLinkSuffix
        ) {
            val url = ngrokLink +
                    "/CheckCredentials.php?" +
                    "Username=" + username +
                    "&Password=" + password

            val queue = Volley.newRequestQueue(context)

            val request: StringRequest =
                object : StringRequest(
                    url,
                    Response.Listener { response ->
                        try {
                            val jsonObject = JSONObject(response)

                            val accountExists = jsonObject.getString("ris") == "1"

                            Log.d(
                                "Check Credentials",
                                "A response to the Check Credentials call was received. It's: $accountExists."
                            )

                            if (accountExists) {
                                Log.d(
                                    "Check Credentials",
                                    "The account exists."
                                )

                                GlobalData.login(
                                    username = username,
                                )

                                navController?.navigate(BottomBarScreen.AccountScreen.route) {
                                    popUpTo(id = navController.graph.findStartDestination().id)
                                    launchSingleTop = true
                                }
                            } else {
                                Log.d(
                                    "Check Credentials",
                                    "The account doesn't exist."
                                )

                                snackbarMessage.value = context.getString(accountDoesntExists)
                                GlobalData.logout()

                                scope.launch {
                                    snackbarHostState.showSnackbar(
                                        snackbarMessage.value
                                    )
                                }

                                loadingIconVisible.value = false
                            }
                        } catch (e: JSONException) {
                            e.printStackTrace()

                            loadingIconVisible.value = false
                        }
                    },
                    Response.ErrorListener { error ->
                        Log.e("tag", "error is " + error!!.message)

                        // Check if the error was caused by the phone being offline or
                        // if the server is inactive.
                        Thread {
                            if (!isInternetReachable()) {
                                snackbarMessage.value =
                                    context.getString(offlineWarning_Message)
                            } else {
                                snackbarMessage.value = context.getString(serverError_Message)
                            }

                            scope.launch {
                                snackbarHostState.showSnackbar(
                                    snackbarMessage.value
                                )
                            }

                            loadingIconVisible.value = false
                        }.start()
                    }) {}

            queue.add(request)
        }

        fun createAccount(
            username: String,
            password: String,
            context: Context,
            scope: CoroutineScope,
            snackbarHostState: SnackbarHostState,
            snackbarMessage: MutableState<String>,
            creatingAccount: MutableState<Boolean>,
            loadingIconVisible: MutableState<Boolean>,
            ngrokLink: String = GlobalData.ngrokServerLinkPrefix
                    + GlobalData.ngrokServerLink
                    + GlobalData.ngrokServerLinkSuffix
        ) {
            val url = ngrokLink +
                    "/CreateAccount.php?" +
                    "Username=" + username +
                    "&Password=" + password

            val queue = Volley.newRequestQueue(context)

            val request: StringRequest =
                object : StringRequest(
                    url,
                    Response.Listener { response ->
                        try {
                            val jsonObject = JSONObject(response)

                            val result = jsonObject.getString("UsernameAlreadyUsed")

                            if (result == "false") {
                                snackbarMessage.value =
                                    context.getString(accountCreatedCorrectly_Message)

                                scope.launch {
                                    snackbarHostState.showSnackbar(
                                        snackbarMessage.value
                                    )
                                }

                                loadingIconVisible.value = false
                                creatingAccount.value = !creatingAccount.value
                            } else {
                                snackbarMessage.value =
                                    context.getString(usernameAlreadyExists_Message)

                                scope.launch {
                                    snackbarHostState.showSnackbar(
                                        snackbarMessage.value
                                    )
                                }

                                loadingIconVisible.value = false
                            }
                        } catch (e: JSONException) {
                            e.printStackTrace()

                            loadingIconVisible.value = false
                        }
                    },
                    Response.ErrorListener { error ->
                        Log.e("tag", "error is " + error!!.message)

                        // Check if the error was caused by the phone being offline or
                        // if the server is inactive.
                        Thread {
                            if (!isInternetReachable()) {
                                snackbarMessage.value =
                                    context.getString(offlineWarning_Message)
                            } else {
                                snackbarMessage.value = context.getString(serverError_Message)
                            }

                            scope.launch {
                                snackbarHostState.showSnackbar(
                                    snackbarMessage.value
                                )
                            }

                            loadingIconVisible.value = false
                        }.start()
                    }) {}

            queue.add(request)
        }

        fun getComponents(
            componentType: Int,
            context: Context,
            scope: CoroutineScope,
            snackbarHostState: SnackbarHostState,
            snackbarMessage: MutableState<String>,
            ngrokLink: String = GlobalData.ngrokServerLinkPrefix
                    + GlobalData.ngrokServerLink
                    + GlobalData.ngrokServerLinkSuffix
        ) {
            var url = when (componentType) {
                ComponentType.CPU -> "$ngrokLink/SelectFromCPU.php?"
                else -> ""
            }
            val activeFilters =
                GlobalData.getActiveFilters().filter { it.component == componentType }

            activeFilters.forEach { item ->
                url += "${item.nameNotLocalized}${item.valueNotLocalized.replace(" ", "")}=1&"
            }

            Log.d("CPUResponse", "Attempting to ask this link: $url")

            val queue = Volley.newRequestQueue(context)

            val request: StringRequest =
                object : StringRequest(
                    url,
                    Response.Listener { response ->
                        try {
                            val jsonObject = JSONObject(response)
                            //val result = jsonObject.getString("UsernameAlreadyUsed")

                            Log.d(
                                "CPUResponse",
                                "The length of the JsonObject is ${jsonObject.length()}, ${jsonObject.length() - 1} are components."
                            )

                            if (jsonObject.getString("Empty") != "true") {
                                cpus.clear()

                                for (i in 1 until jsonObject.length()) {

                                    val currentObject: JSONObject = jsonObject["$i"] as JSONObject

                                    Log.d("CPUResponse", currentObject.toString())

                                    cpus.add(
                                        CPU(
                                            id = currentObject.getString("IdCPU")
                                                .toInt(),
                                            brand = currentObject.getString("Brand"),
                                            series = currentObject.getString("Series"),
                                            name = currentObject.getString("Name"),
                                            price = currentObject.getString("Price")
                                                .toFloat(),
                                            imagePainterId = R.drawable.cpu_placeholder /*currentObject.getString("ImageURL")*/,
                                            coreNumber = currentObject.getString("NumberOfCores")
                                                .toInt(),
                                            baseClockSpeed = currentObject.getString("ClockSpeed")
                                                .toFloat(),
                                            powerConsumption = currentObject.getString("TDP")
                                                .toInt(),
                                            architecture = currentObject.getString("Architecture"),
                                            socket = currentObject.getString("Socket"),
                                            integratedGraphics = currentObject.getString("IntegratedGraphics") == "1",
                                            fanIncluded = currentObject.getString("CoolerIncluded") == "1"
                                        )
                                    )
                                }
                            } else {
                                Log.d("CPUResponse", "No components found")
                                cpus.clear()
                            }

                            /*
                            navController?.navigate(BottomBarScreen.StoreScreen.route) {
                                popUpTo(id = navController.graph.findStartDestination().id)
                                launchSingleTop = true
                            }
                             */

                        } catch (e: JSONException) {
                            e.printStackTrace()
                        }
                    },
                    Response.ErrorListener { error ->
                        Log.e("CPUResponse", "Error is " + error!!.message)

                        when (componentType) {
                            ComponentType.CPU -> cpus.clear()
                            ComponentType.MOTHERBOARD -> motherboards.clear()
                            ComponentType.RAM -> rams.clear()
                            ComponentType.GPU -> gpus.clear()
                            ComponentType.STORAGE -> storages.clear()
                            ComponentType.PSU -> psus.clear()
                        }

                        // Check if the error was caused by the phone being offline or
                        // if the server is inactive.
                        Thread {
                            if (!isInternetReachable()) {
                                snackbarMessage.value =
                                    context.getString(offlineWarning_Message)
                            } else {
                                snackbarMessage.value = context.getString(serverError_Message)
                            }

                            scope.launch {
                                snackbarHostState.showSnackbar(
                                    snackbarMessage.value
                                )
                            }
                        }.start()
                    }) {}

            queue.add(request)
        }
    }
}