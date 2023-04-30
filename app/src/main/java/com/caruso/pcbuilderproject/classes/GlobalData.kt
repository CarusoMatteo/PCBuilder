package com.caruso.pcbuilderproject.classes

import android.content.*
import android.util.Log
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import com.caruso.pcbuilderproject.navigation.BottomBarScreen

abstract class GlobalData {
    companion object {

        const val dynamicColorActive = false

        // Contains the Username of the user currently logged-in, otherwise is null
        var loggedInUser: User = User()

        // Contains the link to connect to the ngrok server
        const val ngrokServerLinkPrefix = "https://"
        var ngrokServerLink = "4128-93-40-210-148"
        const val ngrokServerLinkSuffix = ".ngrok-free.app/PCBuilder"

        // Contains the product type currently selected in the store
        // If 1: CPU,
        // If 2: Motherboard,
        // If 3: RAM,
        // If 4: GPU,
        // If 5: Storage,
        // If 6: PSU
        private var storeProductTypeSelected: Int = 1

        // List of all possible filters
        val filterList: MutableList<Filter> = mutableListOf(
            FilterList.brandAMD,
            FilterList.brandIntel,
            FilterList.seriesRyzen3,
            FilterList.seriesRyzen5,
            FilterList.seriesRyzen7,
            FilterList.seriesRyzen9,
            FilterList.seriesCoreI3,
            FilterList.seriesCoreI5,
            FilterList.seriesCoreI7,
            FilterList.seriesCoreI9,
            FilterList.architectureZen3,
            FilterList.architectureZen4,
            FilterList.architectureAlderLake,
            FilterList.architectureRocketLake,
            FilterList.socketAM4,
            FilterList.socketAM5,
            FilterList.socketLGA1700
        )

        fun getStoreProductTypeSelected(): Int {
            return storeProductTypeSelected
        }

        fun changeStoreProductTypeSelected(
            newValue: Int,
            navController: NavHostController,
        ) {
            storeProductTypeSelected = newValue

            Log.d(
                "Go_to_StoreScreen",
                "Attempting to go to StoreScreen with storeProductTypeSelected = $storeProductTypeSelected."
            )

            navController.navigate(BottomBarScreen.StoreScreen.route) {
                popUpTo(id = navController.graph.findStartDestination().id)
                launchSingleTop = true
            }
        }

        fun floatToStringChecker(
            number: Float,
            currency: Char = '€',
            decimalPoint: Char = '.'
        ): String {
            val numberString = number.toString()

            val numberStringArray = numberString.split('.')

            val wholePart = numberStringArray[0]
            var decimalPart = numberStringArray[1]

            if (decimalPart.isEmpty()) {
                decimalPart = "00"
            } else if (decimalPart.length == 1) {
                decimalPart += "0"
            } else if (decimalPart.length > 2) {
                decimalPart = decimalPart.dropLast(decimalPart.length - 2)
            }

            return "$currency $wholePart$decimalPoint$decimalPart"
        }

        fun copyToClipboard(
            context: Context,
            text: String
        ) {
            val clipboardManager =
                context.getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
            val clip = ClipData.newPlainText("Server link", text)
            clipboardManager.setPrimaryClip(clip)
        }

        fun login(
            username: String,
            cpu: CPU? = null,
            motherboard: Motherboard? = null,
            ram: RAM? = null,
            gpu: GPU? = null,
            storage: Storage? = null,
            psu: PSU? = null
        ) {
            loggedInUser.username = username
            loggedInUser.cpuSelected = cpu
            loggedInUser.motherboardSelected = motherboard
            loggedInUser.ramSelected = ram
            loggedInUser.gpuSelected = gpu
            loggedInUser.storageSelected = storage
            loggedInUser.psuSelected = psu
        }

        fun logout() {
            loggedInUser.username = null
            loggedInUser.cpuSelected = null
            loggedInUser.motherboardSelected = null
            loggedInUser.ramSelected = null
            loggedInUser.gpuSelected = null
            loggedInUser.storageSelected = null
            loggedInUser.psuSelected = null
        }
    }
}