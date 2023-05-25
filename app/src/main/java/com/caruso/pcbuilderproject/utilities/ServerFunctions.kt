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
import com.caruso.pcbuilderproject.R.string.*
import com.caruso.pcbuilderproject.componentsclasses.*
import com.caruso.pcbuilderproject.componentsclasses.ComponentType.Companion.CPU
import com.caruso.pcbuilderproject.componentsclasses.ComponentType.Companion.GPU
import com.caruso.pcbuilderproject.componentsclasses.ComponentType.Companion.MOTHERBOARD
import com.caruso.pcbuilderproject.componentsclasses.ComponentType.Companion.PSU
import com.caruso.pcbuilderproject.componentsclasses.ComponentType.Companion.RAM
import com.caruso.pcbuilderproject.componentsclasses.ComponentType.Companion.STORAGE
import com.caruso.pcbuilderproject.componentsclasses.ComponentsList.Companion.clearCPUs
import com.caruso.pcbuilderproject.componentsclasses.ComponentsList.Companion.clearGPUs
import com.caruso.pcbuilderproject.componentsclasses.ComponentsList.Companion.clearMotherboards
import com.caruso.pcbuilderproject.componentsclasses.ComponentsList.Companion.clearPSUs
import com.caruso.pcbuilderproject.componentsclasses.ComponentsList.Companion.clearRAMs
import com.caruso.pcbuilderproject.componentsclasses.ComponentsList.Companion.clearStorages
import com.caruso.pcbuilderproject.componentsclasses.ComponentsList.Companion.cpus
import com.caruso.pcbuilderproject.componentsclasses.ComponentsList.Companion.gpus
import com.caruso.pcbuilderproject.componentsclasses.ComponentsList.Companion.motherboards
import com.caruso.pcbuilderproject.componentsclasses.ComponentsList.Companion.psus
import com.caruso.pcbuilderproject.componentsclasses.ComponentsList.Companion.rams
import com.caruso.pcbuilderproject.componentsclasses.ComponentsList.Companion.storages
import com.caruso.pcbuilderproject.navigation.BottomBarScreen
import com.caruso.pcbuilderproject.user.User
import com.caruso.pcbuilderproject.utilities.GlobalData.Companion.loggedInUser
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
            Log.d("Is Internet Reachable", "----------------------------")
            val url = URL("https://www.google.com")

            try {
                Log.d("Is Internet Reachable", "Trying to reach $url")

                // URL to a known source

                // Open a connection to that source
                val urlConnect: HttpURLConnection = url.openConnection() as HttpURLConnection

                // Trying to retrieve data from the source
                // If there is no connection, this will trigger an exception
                @Suppress("UNUSED_VARIABLE") val objData: Any = urlConnect.content
            } catch (error: java.lang.Exception) {
                //Log.e("Is Internet Reachable", "Error is " + error.message)
                Log.w("Is Internet Reachable", "$url is not reachable.")
                Log.d("Is Internet Reachable", "----------------------------")
                return false
            }

            Log.d("Is Internet Reachable", "$url is reachable.")
            Log.d("Is Internet Reachable", "----------------------------")
            return true
        }

        fun isServerReachable(): Boolean {
            Log.d("Is Server Reachable", "----------------------------")
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
            } catch (error: java.lang.Exception) {
                //Log.e("Is Server Reachable", "Error is " + error.message)
                Log.w("Is Server Reachable", "The server is not reachable.")
                Log.d("Is Server Reachable", "----------------------------")
                return false
            }

            Log.d("Is Server Reachable", "The server is reachable.")
            Log.d("Is Server Reachable", "----------------------------")
            return true
        }

        fun checkCredentials(
            username: String,
            password: String,
            context: Context,
            scope: CoroutineScope,
            snackbarHostState: SnackbarHostState,
            snackbarMessage: MutableState<String>,
            navController: NavHostController,
            loadingIconVisible: MutableState<Boolean>,
            ngrokLink: String = ngrokServerLinkPrefix
                    + ngrokServerLink
                    + ngrokServerLinkSuffix
        ) {
            val url = buildString {
                append(ngrokLink)
                append("/User/CheckCredentials.php?Username=")
                append(username)
                append("&Password=")
                append(password)
            }

            val queue = Volley.newRequestQueue(context)

            val request: StringRequest =
                object : StringRequest(
                    url,
                    Response.Listener { response ->
                        try {
                            Log.d("Check Credentials", "----------------------------")

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
                                Log.d("Check Credentials", "----------------------------")

                                selectUser(
                                    username = username,
                                    context = context,
                                    snackbarMessage = snackbarMessage
                                )

                                navController.navigate(BottomBarScreen.AccountScreen.route) {
                                    popUpTo(id = navController.graph.findStartDestination().id)
                                    launchSingleTop = true
                                }
                            } else {
                                Log.d(
                                    "Check Credentials",
                                    "The account doesn't exist."
                                )
                                Log.d("Check Credentials", "----------------------------")

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
                        Log.e("Check credentials", "Error is " + error.message)
                        Log.d("Check Credentials", "----------------------------")

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

        fun selectUser(
            username: String,
            context: Context,
            snackbarMessage: MutableState<String>,
            ngrokLink: String = ngrokServerLinkPrefix
                    + ngrokServerLink
                    + ngrokServerLinkSuffix
        ) {
            val url = buildString {
                append(ngrokLink)
                append("/User/SelectUser.php?")
                append("Username=")
                append(username)
            }

            Log.d("Select User", "----------------------------")
            Log.d("Select User", "Attempting to select the user $username at link: $url.")


            val queue = Volley.newRequestQueue(context)

            val request: StringRequest =
                object : StringRequest(
                    url,
                    Response.Listener { response ->
                        try {
                            val jsonObject = JSONObject(response)
                            Log.d("Select User", "Returned $jsonObject.")

                            loggedInUser = User(username = username)

                            try {
                                if (jsonObject.getString("CPU") != "null") {
                                    selectComponentFromID(
                                        componentType = CPU,
                                        componentId = jsonObject.getString("CPU").toInt(),
                                        context = context
                                    )
                                }
                            } catch (error: Exception) {
                                Log.e("Select User", "Error is " + error.message)
                                Log.d("Select User", "----------------------------")

                                loggedInUser = User(username = username)
                            }

                            try {
                                if (jsonObject.getString("Motherboard") != "null") {
                                    selectComponentFromID(
                                        componentType = MOTHERBOARD,
                                        componentId = jsonObject.getString("Motherboard").toInt(),
                                        context = context
                                    )
                                }
                            } catch (error: Exception) {
                                Log.e("Select User", "Error is " + error.message)
                                Log.d("Select User", "----------------------------")

                                loggedInUser = User(username = username)
                            }

                            try {

                                if (jsonObject.getString("RAM") != "null") {
                                    selectComponentFromID(
                                        componentType = RAM,
                                        componentId = jsonObject.getString("RAM").toInt(),
                                        context = context
                                    )
                                }
                            } catch (error: Exception) {
                                Log.e("Select User", "Error is " + error.message)
                                Log.d("Select User", "----------------------------")

                                loggedInUser = User(username = username)
                            }

                            try {
                                if (jsonObject.getString("GPU") != "null") {
                                    selectComponentFromID(
                                        componentType = GPU,
                                        componentId = jsonObject.getString("GPU").toInt(),
                                        context = context
                                    )
                                }
                            } catch (error: Exception) {
                                Log.e("Select User", "Error is " + error.message)
                                Log.d("Select User", "----------------------------")

                                loggedInUser = User(username = username)
                            }

                            try {
                                if (jsonObject.getString("Storage") != "null") {
                                    selectComponentFromID(
                                        componentType = STORAGE,
                                        componentId = jsonObject.getString("Storage").toInt(),
                                        context = context
                                    )
                                }
                            } catch (error: Exception) {
                                Log.e("Select User", "Error is " + error.message)
                                Log.d("Select User", "----------------------------")

                                loggedInUser = User(username = username)
                            }

                            try {
                                if (jsonObject.getString("PSU") != "null") {
                                    selectComponentFromID(
                                        componentType = PSU,
                                        componentId = jsonObject.getString("PSU").toInt(),
                                        context = context
                                    )
                                }
                            } catch (error: Exception) {
                                Log.e("Select User", "Error is " + error.message)
                                Log.d("Select User", "----------------------------")

                                loggedInUser = User(username = username)
                            }

                        } catch (error: JSONException) {
                            Log.e("Select User", "Error is: " + error.message)
                            Log.d("Select User", "----------------------------")
                        }
                    },
                    Response.ErrorListener { error ->
                        Log.e("Select User", "Error is: " + error.message)
                        Log.d("Select User", "----------------------------")

                        // Check if the error was caused by the phone being offline or
                        // if the server is inactive.
                        Thread {
                            if (!isInternetReachable()) {
                                snackbarMessage.value =
                                    context.getString(offlineWarning_Message)
                            } else {
                                snackbarMessage.value = context.getString(serverError_Message)
                            }
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

            Log.d("Create Account", "----------------------------")
            Log.d(
                "Create Account",
                "Attempting to create a user with username: \"$username\" and password: \"$password\""
            )

            val url = buildString {
                append(ngrokLink)
                append("/User/CreateAccount.php?")
                append("Username=")
                append(username)
                append("&Password=")
                append(password)
            }

            Log.d("Create Account", "At URL: $url")

            val queue = Volley.newRequestQueue(context)

            val request: StringRequest =
                object : StringRequest(
                    url,
                    Response.Listener { response ->
                        try {
                            val jsonObject = JSONObject(response)

                            Log.d("Create Account", "Returned $jsonObject.")

                            val result = jsonObject.getString("UsernameAlreadyUsed")

                            if (result == "false") {
                                Log.d(
                                    "Create Account",
                                    "The account was created correctly, since no users already have that username."
                                )
                                Log.d("Create Account", "----------------------------")

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
                                Log.d(
                                    "Create Account",
                                    "The account wasn't created, since there already exists a user with that username."
                                )
                                Log.d("Create Account", "----------------------------")

                                snackbarMessage.value =
                                    context.getString(usernameAlreadyExists_Message)

                                scope.launch {
                                    snackbarHostState.showSnackbar(
                                        snackbarMessage.value
                                    )
                                }

                                loadingIconVisible.value = false
                            }
                        } catch (error: JSONException) {
                            Log.e("Create Account", "Error is: " + error.message)
                            Log.d("Create Account", "----------------------------")

                            loadingIconVisible.value = false
                        }
                    },
                    Response.ErrorListener { error ->
                        Log.e("Create Account", "Error is: " + error.message)
                        Log.d("Create Account", "----------------------------")

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
                CPU -> "$ngrokLink/SelectAllComponents/SelectFromCPU.php?"
                MOTHERBOARD -> "$ngrokLink/SelectAllComponents/SelectFromMotherboard.php?"
                RAM -> "$ngrokLink/SelectAllComponents/SelectFromRAM.php?"
                GPU -> "$ngrokLink/SelectAllComponents/SelectFromGPU.php?"
                STORAGE -> "$ngrokLink/SelectAllComponents/SelectFromStorage.php?"
                PSU -> "$ngrokLink/SelectAllComponents/SelectFromPSU.php?"
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

            Log.d("Get Components", "----------------------------")
            Log.d(
                "Get Components",
                "Attempting to fetch " + ComponentType.toString(componentType, null) + " at $url"
            )

            val queue = Volley.newRequestQueue(context)

            val request: StringRequest =
                object : StringRequest(
                    url,
                    Response.Listener { response ->
                        try {
                            val jsonObject = JSONObject(response)

                            Log.d(
                                "Get Components",
                                "${jsonObject.length() - 1} " + ComponentType.toStringPlural(
                                    componentType,
                                    null
                                ) + " were found."
                            )

                            when (componentType) {
                                CPU -> clearCPUs()
                                MOTHERBOARD -> clearMotherboards()
                                RAM -> clearRAMs()
                                GPU -> clearGPUs()
                                STORAGE -> clearStorages()
                                PSU -> clearPSUs()
                            }

                            if (jsonObject.getString("Empty") != "true") {
                                noItemsFoundCardVisible = false

                                for (i in 1 until jsonObject.length()) {

                                    val currentObject: JSONObject = jsonObject["$i"] as JSONObject
                                    Log.d("GetComponents", currentObject.toString())

                                    when (componentType) {
                                        CPU -> {
                                            cpus.add(
                                                Cpu.toCPU(currentObject)
                                            )
                                        }

                                        MOTHERBOARD -> {
                                            motherboards.add(
                                                Motherboard.toMotherboard(currentObject)
                                            )
                                        }

                                        RAM -> {
                                            rams.add(
                                                Ram.toRAM(currentObject)
                                            )
                                        }

                                        GPU -> {
                                            gpus.add(
                                                Gpu.toGPU(currentObject)
                                            )
                                        }

                                        STORAGE -> {
                                            storages.add(
                                                Storage.toStorage(currentObject)
                                            )
                                        }

                                        PSU -> {
                                            psus.add(
                                                Psu.toPSU(currentObject)
                                            )
                                        }

                                    }
                                }
                            } else {
                                noItemsFoundCardVisible = true
                            }

                            Log.d("Get Components", "----------------------------")

                            navController.navigate(BottomBarScreen.StoreScreen.route) {
                                popUpTo(id = navController.graph.findStartDestination().id)
                                launchSingleTop = true
                            }
                        } catch (error: JSONException) {
                            Log.e("Get Components", "Error is " + error.message)
                            Log.d("Get Components", "----------------------------")

                            noItemsFoundCardVisible = true

                            navController.navigate(BottomBarScreen.StoreScreen.route) {
                                popUpTo(id = navController.graph.findStartDestination().id)
                                launchSingleTop = true
                            }
                        }
                    },
                    Response.ErrorListener { error ->
                        Log.e("Get Components", "Error is " + error.message)
                        Log.d("Get Components", "----------------------------")

                        noItemsFoundCardVisible = true

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

        fun addToCart(
            username: String,
            componentType: Int,
            componentId: Int?,
            loadingIconVisible: MutableState<Boolean>,
            context: Context,
            navController: NavHostController,
            scope: CoroutineScope,
            snackbarHostState: SnackbarHostState,
            snackbarMessage: MutableState<String>,
            ngrokLink: String = ngrokServerLinkPrefix
                    + ngrokServerLink
                    + ngrokServerLinkSuffix
        ) {
            val url = buildString {
                append(ngrokLink)
                append("/User/AddToCart.php?")
                append("Username=")
                append(username)
                append("&ComponentType=")
                append(componentType)
                append("&ComponentID=")
                append(componentId ?: "null")
            }

            Log.d("Add To Cart", "----------------------------")
            Log.d(
                "Add To Cart",
                "Attempting to update the user with username \"$username\" at link: $url"
            )


            val queue = Volley.newRequestQueue(context)

            val request: StringRequest =
                object : StringRequest(
                    url,
                    Response.Listener { response ->
                        try {
                            val jsonObject = JSONObject(response)

                            Log.d("Add To Cart", "Returned $jsonObject")

                            val result = jsonObject.getString("UpdateSuccessful")

                            if (result == "true") {
                                Log.d("Add To Cart", "The update was successful.")
                                Log.d("Add To Cart", "----------------------------")

                                loadingIconVisible.value = false

                                navController.navigate(BottomBarScreen.PartsListScreen.route) {
                                    popUpTo(id = navController.graph.findStartDestination().id)
                                    launchSingleTop = true
                                }
                            } else {
                                Log.d("Add To Cart", "The update wasn't successful.")
                                Log.d("Add To Cart", "----------------------------")

                                loadingIconVisible.value = false
                            }

                        } catch (error: JSONException) {
                            Log.e("Add To Cart", "Error is " + error.message)
                            Log.d("Add To Cart", "----------------------------")

                            loadingIconVisible.value = false
                        }
                    },
                    Response.ErrorListener { error ->
                        Log.e("Add To Cart", "Error is " + error.message)
                        Log.d("Add To Cart", "----------------------------")

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

        fun selectComponentFromID(
            componentType: Int,
            componentId: Int,
            context: Context,
            ngrokLink: String = ngrokServerLinkPrefix
                    + ngrokServerLink
                    + ngrokServerLinkSuffix
        ) {
            val url = buildString {
                append(ngrokLink)
                when (componentType) {
                    CPU -> append("/SelectComponentBasedOnId/SelectCPU.php?")
                    MOTHERBOARD -> append("/SelectComponentBasedOnId/SelectMotherboard.php?")
                    RAM -> append("/SelectComponentBasedOnId/SelectRAM.php?")
                    GPU -> append("/SelectComponentBasedOnId/SelectGPU.php?")
                    STORAGE -> append("/SelectComponentBasedOnId/SelectStorage.php?")
                    PSU -> append("/SelectComponentBasedOnId/SelectPSU.php?")
                    else -> append("/404.php")
                }
                append("Id=$componentId")
            }

            Log.d(
                "Select ${ComponentType.toString(componentType, null)} From ID",
                "Attempting to fetch the component with Id=$componentId at link:\"$url\""
            )

            val queue = Volley.newRequestQueue(context)

            val request: StringRequest =
                object : StringRequest(
                    url,
                    Response.Listener { response ->
                        try {
                            val jsonObject = JSONObject(response)
                            Log.d(
                                "Select ${ComponentType.toString(componentType, null)} From ID",
                                "Returned $jsonObject"
                            )

                            when (componentType) {
                                CPU -> loggedInUser?.cpuSelected = Cpu.toCPU(jsonObject)
                                MOTHERBOARD -> loggedInUser?.motherboardSelected =
                                    Motherboard.toMotherboard(jsonObject)

                                RAM -> loggedInUser?.ramSelected = Ram.toRAM(jsonObject)
                                GPU -> loggedInUser?.gpuSelected = Gpu.toGPU(jsonObject)
                                STORAGE -> loggedInUser?.storageSelected =
                                    Storage.toStorage(jsonObject)

                                PSU -> loggedInUser?.psuSelected = Psu.toPSU(jsonObject)
                            }
                            Log.d(
                                "Select ${ComponentType.toString(componentType, null)} From ID",
                                "The LoggedInUser was updated. Now it's: ${loggedInUser?.toStringComplete()}"
                            )
                        } catch (error: JSONException) {
                            Log.e(
                                "Select ${ComponentType.toString(componentType, null)} From ID",
                                "Error is " + error.message
                            )
                        }
                    },
                    Response.ErrorListener { error ->
                        Log.e(
                            "Select ${ComponentType.toString(componentType, null)} From ID",
                            "Error is " + error.message
                        )
                    }) {}

            queue.add(request)
        }

        fun createOrder(
            username: String,
            totalPrice: Float,
            loadingIconVisible: MutableState<Boolean>,
            context: Context,
            navController: NavHostController,
            scope: CoroutineScope,
            snackbarHostState: SnackbarHostState,
            snackbarMessage: MutableState<String>,
            ngrokLink: String = ngrokServerLinkPrefix
                    + ngrokServerLink
                    + ngrokServerLinkSuffix
        ) {
            val url = buildString {
                append(ngrokLink)
                append("/User/CreateOrder.php?")
                append("Username=")
                append(username)
                append("&TotalPrice=")
                append(totalPrice)

                if (loggedInUser?.cpuSelected != null) {
                    append("&IdCPU=")
                    append(loggedInUser?.cpuSelected!!.id)
                }
                if (loggedInUser?.motherboardSelected != null) {
                    append("&IdMotherboard=")
                    append(loggedInUser?.motherboardSelected!!.id)
                }
                if (loggedInUser?.ramSelected != null) {
                    append("&IdRAM=")
                    append(loggedInUser?.ramSelected!!.id)
                }
                if (loggedInUser?.gpuSelected != null) {
                    append("&IdGPU=")
                    append(loggedInUser?.gpuSelected!!.id)
                }
                if (loggedInUser?.storageSelected != null) {
                    append("&IdStorage=")
                    append(loggedInUser?.storageSelected!!.id)
                }
                if (loggedInUser?.psuSelected != null) {
                    append("&IdPSU=")
                    append(loggedInUser?.psuSelected!!.id)
                }
            }

            Log.d("Create Order", "----------------------------")
            Log.d(
                "Create Order",
                "Attempting to create the order for user \"$username\" at link: $url"
            )

            val queue = Volley.newRequestQueue(context)

            val request: StringRequest =
                object : StringRequest(
                    url,
                    Response.Listener { response ->
                        try {
                            val jsonObject = JSONObject(response)

                            Log.d("Create Order", "Returned $jsonObject")

                            val createOrderSuccessful =
                                jsonObject.getString("CreateOrderSuccessful")
                            val updateUserSuccessful =
                                jsonObject.getString("UpdateUserSuccessful")

                            if (createOrderSuccessful == "true" && updateUserSuccessful == "true") {
                                Log.d("Create Order", "The order was created successfully.")
                                Log.d("Create Order", "----------------------------")

                                loadingIconVisible.value = false

                                navController.navigate(BottomBarScreen.AccountScreen.route) {
                                    popUpTo(id = navController.graph.findStartDestination().id)
                                    launchSingleTop = true
                                }

                                selectUser(username, context, snackbarMessage)
                            } else {
                                Log.d("Create Order", "The order wasn't created successfully.")
                                Log.d("Create Order", "----------------------------")

                                loadingIconVisible.value = false
                            }

                        } catch (error: JSONException) {
                            Log.e("Create Order", "Error is " + error.message)
                            Log.d("Create Order", "----------------------------")

                            loadingIconVisible.value = false
                        }
                    },
                    Response.ErrorListener { error ->
                        Log.e("Create Order", "Error is " + error.message)
                        Log.d("Create Order", "----------------------------")

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
    }
}

