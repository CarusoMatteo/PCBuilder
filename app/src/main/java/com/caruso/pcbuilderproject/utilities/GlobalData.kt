package com.caruso.pcbuilderproject.utilities

import android.content.*
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import com.caruso.pcbuilderproject.componentsclasses.ComponentType
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
        var loggedInUser: User? = null
        // var loggedInUser: User? = User("Admin")

        // Contains the link to connect to the ngrok server
        const val ngrokServerLinkPrefix = "https://"
        var ngrokServerLink = "5576-93-40-208-236"
        const val ngrokServerLinkSuffix = ".ngrok-free.app/PCBuilder"

        // Contains the product type currently selected in the store
        // When 0: CPU,
        // When 1: Motherboard,
        // When 2: RAM,
        // When 3: GPU,
        // When 4: Storage,
        // When 5: PSU
        //
        // When app starts the product type selected is CPU.
        private var storeProductTypeSelected: Int = ComponentType.CPU

        var noItemsFoundCardVisible = false

        var reloadAccountScreenForXTimes = 0

        // List of all possible filters
        val filterList: MutableList<Filter> = mutableListOf(

            // region CPU filters

            FilterList.brandAMD_CPU,
            FilterList.brandIntel_CPU,
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
            FilterList.socketAM4_CPU,
            FilterList.socketAM5_CPU,
            FilterList.socketLGA1700_CPU,
            FilterList.integratedGraphicsYes,
            FilterList.integratedGraphicsNo,
            FilterList.coolerIncludedYes,
            FilterList.coolerIncludedNo,

            // endregion

            // region Motherboard filters

            FilterList.brandAsus_Motherboard,
            FilterList.brandMSI_Motherboard,
            FilterList.socketAM4_Motherboard,
            FilterList.socketAM5_Motherboard,
            FilterList.socketLGA1700_Motherboard,
            FilterList.chipsetB550,
            FilterList.chipsetB650,
            FilterList.chipsetX570,
            FilterList.chipsetX670,
            FilterList.chipsetX670E,
            FilterList.chipsetZ690,
            FilterList.chipsetZ790,
            FilterList.formFactorATX_Motherboard,
            FilterList.formFactorMicroATX,
            FilterList.formFactorMiniITX,
            FilterList.memoryTypeDDR4_Motherboard,
            FilterList.memoryTypeDDR5_Motherboard,
            FilterList.memorySlotNumber2,
            FilterList.memorySlotNumber4,
            FilterList.maxEthernetSpeed1,
            FilterList.maxEthernetSpeed25,
            FilterList.wifiIncludedYes,
            FilterList.wifiIncludedNo,
            FilterList.bluetoothIncludedYes,
            FilterList.bluetoothIncludedNo,
            FilterList.anyPCIe_5_x16Yes,
            FilterList.anyPCIe_5_x16No,
            FilterList.anyM2_NVMe_5Yes,
            FilterList.anyM2_NVMe_5No,

            // endregion

            // region RAM filters

            FilterList.brandCorsair_RAM,
            FilterList.brandGSkill,
            FilterList.brandKingston,
            FilterList.memoryTypeDDR4_RAM,
            FilterList.memoryTypeDDR5_RAM,
            FilterList.totalSize8,
            FilterList.totalSize16,
            FilterList.totalSize32,
            FilterList.totalSize64,
            FilterList.numberOfSticks1,
            FilterList.numberOfSticks2,
            FilterList.numberOfSticks4,
            FilterList.memorySpeed3200,
            FilterList.memorySpeed3600,
            FilterList.memorySpeed5600,
            FilterList.memorySpeed6000,
            FilterList.memorySpeed6600,

            // endregion

            // region GPU filters

            FilterList.brandMSI_GPU,
            FilterList.brandAsus_GPU,
            FilterList.brandGigabyte,
            FilterList.chipsetBrandNVIDIA,
            FilterList.chipsetBrandAMD,
            FilterList.chipsetRTX3050,
            FilterList.chipsetRTX3060,
            FilterList.chipsetRTX3080,
            FilterList.chipsetRTX3090,
            FilterList.chipsetRTX4080,
            FilterList.chipsetRTX4090,
            FilterList.chipsetRX6500XT,
            FilterList.chipsetRX6600XT,
            FilterList.chipsetRX6800XT,
            FilterList.chipsetRX6900XT,
            FilterList.chipsetRX7900XT,
            FilterList.chipsetRX7900XTX,
            FilterList.VRAMSize4,
            FilterList.VRAMSize8,
            FilterList.VRAMSize10,
            FilterList.VRAMSize16,
            FilterList.VRAMSize20,
            FilterList.VRAMSize24,
            FilterList.numberOfHDMI1,
            FilterList.numberOfHDMI2,
            FilterList.numberOfHDMI3,
            FilterList.numberOfDP1,
            FilterList.numberOfDP2,
            FilterList.numberOfDP3,

            // endregion

            // region Storage filters

            FilterList.brandSamsung_Storage,
            FilterList.brandGigabyte_Storage,
            FilterList.brandCorsair_Storage,
            FilterList.typeNVME4,
            FilterList.typeNVME5,
            FilterList.typeSATA,
            FilterList.size250,
            FilterList.size500,
            FilterList.size1000,
            FilterList.size2000,

            // endregion

            // region PSU filters

            FilterList.brandCorsair_PSU,
            FilterList.brandSeaSonic_PSU,
            FilterList.wattage550,
            FilterList.wattage650,
            FilterList.wattage750,
            FilterList.wattage850,
            FilterList.wattage1000,
            FilterList.formFactorATX_PSU,
            FilterList.formFactorSFF_PSU,
            FilterList.length160,
            FilterList.length170,
            FilterList.length180

            // endregion
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
            IncompatibilityList.wrongSocket,
            IncompatibilityList.wrongMemoryType,
            IncompatibilityList.tooManyMemorySticks,
            IncompatibilityList.tooLittlePowerfulPSU,
            IncompatibilityList.storageNotCompatible

            // New incompatibilities must be added here.
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
            ServerFunctions.askingToReloadStore = true

            navController.navigate(BottomBarScreen.StoreScreen.route) {
                popUpTo(id = navController.graph.findStartDestination().id)
                launchSingleTop = true
            }
        }

        fun priceInFloatToString(
            number: Float,
            currency: String,
            decimalPoint: String,
            dropCurrency: Boolean = false
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

            if (!dropCurrency)
                return "$currency $wholePart$decimalPoint$decimalPart"
            else
                return "$wholePart$decimalPoint$decimalPart"
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

        fun logout(context: Context) {
            loggedInUser = null
            IncompatibilityList.checkForIncompatibilities(context = context)
        }
    }
}