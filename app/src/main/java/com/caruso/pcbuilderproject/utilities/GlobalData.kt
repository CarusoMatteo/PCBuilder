package com.caruso.pcbuilderproject.utilities

import android.content.*
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import com.caruso.pcbuilderproject.componentsclasses.*
import com.caruso.pcbuilderproject.filters.Filter
import com.caruso.pcbuilderproject.filters.FilterList
import com.caruso.pcbuilderproject.incompatibilities.Incompatibility
import com.caruso.pcbuilderproject.incompatibilities.IncompatibilityList
import com.caruso.pcbuilderproject.navigation.BottomBarScreen
import com.caruso.pcbuilderproject.user.User

abstract class GlobalData {
    companion object {

        const val dynamicColorActive = false

        // Contains the Username of the user currently logged-in, otherwise is null
        var loggedInUser: User = User("Admin")

        // Contains the link to connect to the ngrok server
        const val ngrokServerLinkPrefix = "https://"
        var ngrokServerLink = "dc5f-93-40-208-13"
        const val ngrokServerLinkSuffix = ".ngrok-free.app/PCBuilder"

        // Contains the product type currently selected in the store
        // If 0: CPU,
        // If 1: Motherboard,
        // If 2: RAM,
        // If 3: GPU,
        // If 4: Storage,
        // If 5: PSU
        private var storeProductTypeSelected: Int = ComponentType.CPU

        // List of all possible filters
        val filterList: MutableList<Filter> = mutableListOf(
            FilterList.brandAMD,
            FilterList.brandIntel,
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
            FilterList.architectureRaptorLake,
            FilterList.socketAM4,
            FilterList.socketAM5,
            FilterList.socketLGA1700,
            FilterList.integratedGraphicsYes,
            FilterList.integratedGraphicsNo
        )

        fun getActiveFilters(): MutableList<Filter> {
            val activeFilters: MutableList<Filter> = mutableListOf()

            filterList.forEach {
                if (it.active)
                    activeFilters.add(it)
            }

            return activeFilters
        }

        val incompatibilityList: MutableList<Incompatibility> = mutableListOf(
            IncompatibilityList.wrongSocket
        )

        fun getActiveIncompatibilities(): MutableList<Incompatibility> {
            val activeIncompatibilities: MutableList<Incompatibility> = mutableListOf()

            incompatibilityList.forEach {
                if (it.active)
                    activeIncompatibilities.add(it)
            }

            return activeIncompatibilities
        }

        fun getStoreProductTypeSelected(): Int {
            return storeProductTypeSelected
        }

        fun changeStoreProductTypeSelected(
            newValue: Int,
            navController: NavHostController,
        ) {
            storeProductTypeSelected = newValue

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
            loggedInUser.clear()
        }
    }
}