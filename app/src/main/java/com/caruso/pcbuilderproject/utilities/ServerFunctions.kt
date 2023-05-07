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
import com.caruso.pcbuilderproject.componentsclasses.ComponentType.Companion.CPU
import com.caruso.pcbuilderproject.componentsclasses.ComponentType.Companion.GPU
import com.caruso.pcbuilderproject.componentsclasses.ComponentType.Companion.MOTHERBOARD
import com.caruso.pcbuilderproject.componentsclasses.ComponentType.Companion.PSU
import com.caruso.pcbuilderproject.componentsclasses.ComponentType.Companion.RAM
import com.caruso.pcbuilderproject.componentsclasses.ComponentType.Companion.STORAGE
import com.caruso.pcbuilderproject.componentsclasses.ComponentsList.Companion.clearCPUs
import com.caruso.pcbuilderproject.componentsclasses.ComponentsList.Companion.cpus
import com.caruso.pcbuilderproject.componentsclasses.ComponentsList.Companion.gpus
import com.caruso.pcbuilderproject.componentsclasses.ComponentsList.Companion.motherboards
import com.caruso.pcbuilderproject.componentsclasses.ComponentsList.Companion.psus
import com.caruso.pcbuilderproject.componentsclasses.ComponentsList.Companion.rams
import com.caruso.pcbuilderproject.componentsclasses.ComponentsList.Companion.storages
import com.caruso.pcbuilderproject.navigation.BottomBarScreen
import com.caruso.pcbuilderproject.utilities.GlobalData.Companion.ngrokServerLink
import com.caruso.pcbuilderproject.utilities.GlobalData.Companion.ngrokServerLinkPrefix
import com.caruso.pcbuilderproject.utilities.GlobalData.Companion.ngrokServerLinkSuffix
import com.caruso.pcbuilderproject.utilities.GlobalData.Companion.noItemsFoundCardVisible
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import org.json.JSONException
import org.json.JSONObject
import java.net.HttpURLConnection
import java.net.URL

abstract class ServerFunctions {
    companion object {
        var askingToReloadStore = true

        fun isInternetReachable(): Boolean {
            try {
                Log.d("Is Internet Reachable", "Trying to reach Google.com")

                // URL to a known source
                val url = URL("https://www.google.com")

                // Open a connection to that source
                val urlConnect: HttpURLConnection = url.openConnection() as HttpURLConnection

                // Trying to retrieve data from the source
                // If there is no connection, this will trigger an exception
                @Suppress("UNUSED_VARIABLE") val objData: Any = urlConnect.content
            } catch (e: java.lang.Exception) {
                e.printStackTrace()
                Log.d("Is Internet Reachable", "Google.com is not reachable.")
                return false
            }

            Log.d("Is Internet Reachable", "Google.com is reachable.")
            return true
        }

        fun isServerReachable(): Boolean {
            try {
                Log.d(
                    "Is Server Reachable",
                    "Trying to reach ${ngrokServerLinkPrefix + ngrokServerLink + ngrokServerLinkSuffix}."
                )

                // URL to a known source
                val url = URL(ngrokServerLinkPrefix + ngrokServerLink + ngrokServerLinkSuffix)

                // Open a connection to that source
                val urlConnect: HttpURLConnection = url.openConnection() as HttpURLConnection

                // Trying to retrieve data from the source
                // If there is no connection, this will trigger an exception
                @Suppress("UNUSED_VARIABLE") val objData: Any = urlConnect.content
            } catch (e: java.lang.Exception) {
                e.printStackTrace()

                Log.d(
                    "Is Server Reachable",
                    "${ngrokServerLinkPrefix + ngrokServerLink + ngrokServerLinkSuffix} is not reachable."
                )
                return false
            }

            Log.d(
                "Is Server Reachable",
                "${ngrokServerLinkPrefix + ngrokServerLink + ngrokServerLinkSuffix} is reachable."
            )
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
            ngrokLink: String = ngrokServerLinkPrefix
                    + ngrokServerLink
                    + ngrokServerLinkSuffix
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
                                GlobalData.logout(context = context)

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
            ngrokLink: String = ngrokServerLinkPrefix
                    + ngrokServerLink
                    + ngrokServerLinkSuffix
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
            navController: NavHostController,
            ngrokLink: String = ngrokServerLinkPrefix
                    + ngrokServerLink
                    + ngrokServerLinkSuffix
        ) {
            var url = when (componentType) {
                CPU -> "$ngrokLink/SelectFromCPU.php?"
                MOTHERBOARD -> "$ngrokLink/SelectFromMotherboard.php?"
                RAM -> "$ngrokLink/SelectFromRAM.php?"
                GPU -> "$ngrokLink/SelectFromGPU.php?"
                STORAGE -> "$ngrokLink/SelectFromStorage.php?"
                PSU -> "$ngrokLink/SelectFromPSU.php?"
                else -> ""
            }

            val activeFilters =
                GlobalData.getActiveFilters().filter { it.component == componentType }

            activeFilters.forEach { item ->
                url += buildString {
                    append(item.nameNotLocalized) //.replace(" ", "") The replace isn't needed
                    append(item.valueNotLocalized.replace(" ", ""))
                    append("=1&")
                }
            }

            Log.d("CPUResponse", "Attempting to ask this link: $url")

            val queue = Volley.newRequestQueue(context)

            val request: StringRequest =
                object : StringRequest(
                    url,
                    Response.Listener { response ->
                        try {
                            val jsonObject = JSONObject(response)

                            Log.d(
                                "CPUResponse",
                                "The length of the JsonObject is ${jsonObject.length()}, ${jsonObject.length() - 1} are components."
                            )

                            when (componentType) {
                                CPU -> clearCPUs()
                                MOTHERBOARD -> ComponentsList.clearMotherboards()
                                RAM -> ComponentsList.clearRAMs()
                                GPU -> ComponentsList.clearGPUs()
                                STORAGE -> ComponentsList.clearStorages()
                                PSU -> ComponentsList.clearPSUs()
                            }

                            if (jsonObject.getString("Empty") != "true") {
                                noItemsFoundCardVisible = false

                                Log.d(
                                    "Change to noItemsFoundCardVisible",
                                    "noItemsFoundCardVisible is now $noItemsFoundCardVisible."
                                )

                                for (i in 1 until jsonObject.length()) {

                                    val currentObject: JSONObject = jsonObject["$i"] as JSONObject

                                    when (componentType) {
                                        CPU -> {
                                            Log.d("CPU Response", currentObject.toString())

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

                                        MOTHERBOARD -> {
                                            Log.d("Motherboard Response", currentObject.toString())

                                            motherboards.add(
                                                Motherboard(
                                                    id = currentObject.getString("IdMotherboard")
                                                        .toInt(),
                                                    brand = currentObject.getString("Brand"),
                                                    name = currentObject.getString("Name"),
                                                    price = currentObject.getString("Price")
                                                        .toFloat(),
                                                    imagePainterId = R.drawable.motherboard_placeholder, /*currentObject.getString("ImageURL")*/
                                                    socket = currentObject.getString("Socket"),
                                                    chipset = currentObject.getString("Chipset"),
                                                    formFactor = currentObject.getString("FormFactor"),
                                                    memoryType = currentObject.getString("RAMType"),
                                                    memorySlotNumber = currentObject.getString("NumberOfRAMSlots")
                                                        .toInt(),
                                                    maxEthernetSpeed = currentObject.getString("MaxEthernetSpeed")
                                                        .toFloat(),
                                                    wifiIncluded = currentObject.getString("WifiIncluded") == "1",
                                                    bluetoothIncluded = currentObject.getString("BluetoothIncluded") == "1",
                                                    pcie_x16_5_slotNumber = currentObject.getString(
                                                        "PCIe_x16_5"
                                                    ).toInt(),
                                                    pcie_x16_4_slotNumber = currentObject.getString(
                                                        "PCIe_x16_4"
                                                    ).toInt(),
                                                    pcie_x8_4_slotNumber = currentObject.getString("PCIe_x8")
                                                        .toInt(),
                                                    pcie_x4_4_slotNumber = currentObject.getString("PCIe_x4")
                                                        .toInt(),
                                                    pcie_x1_4_slotNumber = currentObject.getString("PCIe_x1")
                                                        .toInt(),
                                                    m2_nvme_5_slotNumber = currentObject.getString("M2_5")
                                                        .toInt(),
                                                    m2_nvme_4_slotNumber = currentObject.getString("M2_4")
                                                        .toInt(),
                                                    sata_portNumber = currentObject.getString("NumberOfSATA")
                                                        .toInt(),
                                                    usb_a_2_headerNumber = currentObject.getString("USB_2")
                                                        .toInt(),
                                                    usb_a_32_gen1_headerNumber = currentObject.getString(
                                                        "USB_32_1"
                                                    ).toInt(),
                                                    usb_c_32_gen2_headerNumber = currentObject.getString(
                                                        "USB_32_2"
                                                    ).toInt()
                                                )
                                            )
                                        }

                                        // TODO: RAM->{}
                                        // TODO: GPU->{}
                                        // TODO: Storage->{}
                                        // TODO: PSU->{}

                                    }
                                }
                            } else {
                                Log.d("CPUResponse", "No components found")
                                noItemsFoundCardVisible = true

                                Log.d(
                                    "Change to noItemsFoundCardVisible",
                                    "noItemsFoundCardVisible is now $noItemsFoundCardVisible."
                                )

                                clearCPUs()
                            }

                            navController.navigate(BottomBarScreen.StoreScreen.route) {
                                popUpTo(id = navController.graph.findStartDestination().id)
                                launchSingleTop = true
                            }
                        } catch (e: JSONException) {
                            noItemsFoundCardVisible = true

                            Log.d(
                                "Change to noItemsFoundCardVisible",
                                "noItemsFoundCardVisible is now $noItemsFoundCardVisible."
                            )

                            e.printStackTrace()

                            navController.navigate(BottomBarScreen.StoreScreen.route) {
                                popUpTo(id = navController.graph.findStartDestination().id)
                                launchSingleTop = true
                            }
                        }
                    },
                    Response.ErrorListener { error ->
                        Log.e("CPUResponse", "Error is " + error!!.message)
                        noItemsFoundCardVisible = true

                        Log.d(
                            "Change to noItemsFoundCardVisible",
                            "noItemsFoundCardVisible is now $noItemsFoundCardVisible."
                        )

                        when (componentType) {
                            CPU -> cpus.clear()
                            MOTHERBOARD -> motherboards.clear()
                            RAM -> rams.clear()
                            GPU -> gpus.clear()
                            STORAGE -> storages.clear()
                            PSU -> psus.clear()
                        }

                        navController.navigate(BottomBarScreen.StoreScreen.route) {
                            popUpTo(id = navController.graph.findStartDestination().id)
                            launchSingleTop = true
                        }
                    }) {}

            queue.add(request)
        }
    }
}