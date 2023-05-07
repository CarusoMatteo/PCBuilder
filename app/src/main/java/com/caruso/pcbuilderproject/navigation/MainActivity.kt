package com.caruso.pcbuilderproject.navigation

import android.content.Context
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import com.caruso.pcbuilderproject.R.string.*
import com.caruso.pcbuilderproject.componentsclasses.ComponentType.Companion.CPU
import com.caruso.pcbuilderproject.componentsclasses.ComponentType.Companion.MOTHERBOARD
import com.caruso.pcbuilderproject.filters.Filter
import com.caruso.pcbuilderproject.filters.FilterList
import com.caruso.pcbuilderproject.ui.theme.PCBuilderProjectTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {

            initializeFilters(context = LocalContext.current)

            PCBuilderProjectTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                ) {
                    MainScreen()
                }
            }
        }
    }
}

fun initializeFilters(context: Context) {
    // Filters that are added need to be initialized here

    //region CPU Filters initialization

    FilterList.brandAMD = Filter(
        name = context.getString(brand_Text),
        value = "AMD",
        component = CPU,
        nameNotLocalized = "brand"
    )
    FilterList.brandIntel = Filter(
        name = context.getString(brand_Text),
        value = "Intel",
        component = CPU,
        nameNotLocalized = "brand"
    )
    FilterList.seriesRyzen5 = Filter(
        name = context.getString(series_Text),
        value = "Ryzen 5",
        component = CPU,
        nameNotLocalized = "series"
    )
    FilterList.seriesRyzen7 = Filter(
        name = context.getString(series_Text),
        value = "Ryzen 7",
        component = CPU,
        nameNotLocalized = "series"
    )
    FilterList.seriesRyzen9 = Filter(
        name = context.getString(series_Text),
        value = "Ryzen 9",
        component = CPU,
        nameNotLocalized = "series"
    )
    FilterList.seriesCoreI3 = Filter(
        name = context.getString(series_Text),
        value = "Core i3",
        component = CPU,
        nameNotLocalized = "series"
    )
    FilterList.seriesCoreI5 = Filter(
        name = context.getString(series_Text),
        value = "Core i5",
        component = CPU,
        nameNotLocalized = "series"
    )
    FilterList.seriesCoreI7 = Filter(
        name = context.getString(series_Text),
        value = "Core i7",
        component = CPU,
        nameNotLocalized = "series"
    )
    FilterList.seriesCoreI9 = Filter(
        name = context.getString(series_Text),
        value = "Core i9",
        component = CPU,
        nameNotLocalized = "series"
    )
    FilterList.architectureZen3 = Filter(
        name = context.getString(architecture_Text),
        value = "Zen 3",
        component = CPU,
        nameNotLocalized = "architecture"
    )
    FilterList.architectureZen4 = Filter(
        name = context.getString(architecture_Text),
        value = "Zen 4",
        component = CPU,
        nameNotLocalized = "architecture"
    )
    FilterList.architectureAlderLake = Filter(
        name = context.getString(architecture_Text),
        value = "Alder Lake",
        component = CPU,
        nameNotLocalized = "architecture"
    )
    FilterList.architectureRaptorLake = Filter(
        name = context.getString(architecture_Text),
        value = "Raptor Lake",
        component = CPU,
        nameNotLocalized = "architecture"
    )
    FilterList.socketAM4_CPU = Filter(
        name = context.getString(socket_Text),
        value = "AM4",
        component = CPU,
        nameNotLocalized = "socket"
    )
    FilterList.socketAM5_CPU = Filter(
        name = context.getString(socket_Text),
        value = "AM5",
        component = CPU,
        nameNotLocalized = "socket"
    )
    FilterList.socketLGA1700_CPU = Filter(
        name = context.getString(socket_Text),
        value = "LGA1700",
        component = CPU,
        nameNotLocalized = "socket"
    )
    FilterList.integratedGraphicsYes = Filter(
        name = context.getString(integratedGraphics_Text),
        value = context.getString(yes_Text),
        component = CPU,
        nameNotLocalized = "integratedGraphics",
        valueNotLocalized = "Yes"
    )
    FilterList.integratedGraphicsNo = Filter(
        name = context.getString(integratedGraphics_Text),
        value = context.getString(no_Text),
        component = CPU,
        nameNotLocalized = "integratedGraphics",
        valueNotLocalized = "No"
    )

    // endregion

    //region Motherboard Filters initialization

    FilterList.brandAsus = Filter(
        name = context.getString(brand_Text),
        value = "Asus",
        component = MOTHERBOARD,
        nameNotLocalized = "brand"
    )
    FilterList.brandMSI = Filter(
        name = context.getString(brand_Text),
        value = "MSI",
        component = MOTHERBOARD,
        nameNotLocalized = "brand"
    )
    FilterList.socketAM4_Motherboard = Filter(
        name = context.getString(socket_Text),
        value = "AM4",
        component = MOTHERBOARD,
        nameNotLocalized = "socket"
    )
    FilterList.socketAM5_Motherboard = Filter(
        name = context.getString(socket_Text),
        value = "AM5",
        component = MOTHERBOARD,
        nameNotLocalized = "socket"
    )
    FilterList.socketLGA1700_Motherboard = Filter(
        name = context.getString(socket_Text),
        value = "LGA1700",
        component = MOTHERBOARD,
        nameNotLocalized = "socket"
    )
    FilterList.chipsetB550 = Filter(
        name = context.getString(chipset_Text),
        value = "B550",
        component = MOTHERBOARD,
        nameNotLocalized = "chipset"
    )
    FilterList.chipsetB650 = Filter(
        name = context.getString(chipset_Text),
        value = "B650",
        component = MOTHERBOARD,
        nameNotLocalized = "chipset"
    )
    FilterList.chipsetX570 = Filter(
        name = context.getString(chipset_Text),
        value = "X570",
        component = MOTHERBOARD,
        nameNotLocalized = "chipset"
    )
    FilterList.chipsetX670 = Filter(
        name = context.getString(chipset_Text),
        value = "X670",
        component = MOTHERBOARD,
        nameNotLocalized = "chipset"
    )
    FilterList.chipsetX670E = Filter(
        name = context.getString(chipset_Text),
        value = "X670E",
        component = MOTHERBOARD,
        nameNotLocalized = "chipset"
    )
    FilterList.chipsetZ690 = Filter(
        name = context.getString(chipset_Text),
        value = "Z690",
        component = MOTHERBOARD,
        nameNotLocalized = "chipset"
    )
    FilterList.chipsetZ790 = Filter(
        name = context.getString(chipset_Text),
        value = "Z790",
        component = MOTHERBOARD,
        nameNotLocalized = "chipset"
    )
    FilterList.formFactorATX = Filter(
        name = context.getString(formFactor_Text),
        value = "ATX",
        component = MOTHERBOARD,
        nameNotLocalized = "formFactor"
    )
    FilterList.formFactorMicroATX = Filter(
        name = context.getString(formFactor_Text),
        value = "Micro ATX",
        component = MOTHERBOARD,
        nameNotLocalized = "formFactor"
    )
    FilterList.formFactorMiniITX = Filter(
        name = context.getString(formFactor_Text),
        value = "Mini ITX",
        component = MOTHERBOARD,
        nameNotLocalized = "formFactor"
    )
    FilterList.memoryTypeDDR4 = Filter(
        name = context.getString(memoryType_Text),
        value = "DDR4",
        component = MOTHERBOARD,
        nameNotLocalized = "memoryType"
    )
    FilterList.memoryTypeDDR5 = Filter(
        name = context.getString(memoryType_Text),
        value = "DDR5",
        component = MOTHERBOARD,
        nameNotLocalized = "memoryType"
    )
    FilterList.memorySlotNumber2 = Filter(
        name = context.getString(memorySlotNumber_Text),
        value = "2",
        component = MOTHERBOARD,
        nameNotLocalized = "memorySlotNumber"
    )
    FilterList.memorySlotNumber4 = Filter(
        name = context.getString(memorySlotNumber_Text),
        value = "4",
        component = MOTHERBOARD,
        nameNotLocalized = "memorySlotNumber"
    )
    FilterList.maxEthernetSpeed1 = Filter(
        name = context.getString(maxEthernetSpeed),
        value = "1",
        component = MOTHERBOARD,
        nameNotLocalized = "maxEthernetSpeed"
    )
    FilterList.maxEthernetSpeed25 = Filter(
        name = context.getString(maxEthernetSpeed),
        value = "2${context.getString(decimalPoint)}5",
        component = MOTHERBOARD,
        nameNotLocalized = "maxEthernetSpeed",
        valueNotLocalized = "25"
    )
    FilterList.wifiIncludedYes = Filter(
        name = context.getString(wifiIncluded_Text),
        value = context.getString(yes_Text),
        component = MOTHERBOARD,
        nameNotLocalized = "wifiIncluded",
        valueNotLocalized = "Yes"
    )
    FilterList.wifiIncludedNo = Filter(
        name = context.getString(wifiIncluded_Text),
        value = context.getString(no_Text),
        component = MOTHERBOARD,
        nameNotLocalized = "wifiIncluded",
        valueNotLocalized = "No"
    )
    FilterList.bluetoothIncludedYes = Filter(
        name = context.getString(bluetoothIncluded_Text),
        value = context.getString(yes_Text),
        component = MOTHERBOARD,
        nameNotLocalized = "bluetoothIncluded",
        valueNotLocalized = "Yes"
    )
    FilterList.bluetoothIncludedNo = Filter(
        name = context.getString(bluetoothIncluded_Text),
        value = context.getString(no_Text),
        component = MOTHERBOARD,
        nameNotLocalized = "bluetoothIncluded",
        valueNotLocalized = "No"
    )
    FilterList.anyPCIe_5_x16Yes = Filter(
        name = context.getString(anyPcie5ExpansionSlot_Text),
        value = context.getString(yes_Text),
        component = MOTHERBOARD,
        nameNotLocalized = "anyPCIe_5_x16",
        valueNotLocalized = "Yes"
    )
    FilterList.anyPCIe_5_x16No = Filter(
        name = context.getString(anyPcie5ExpansionSlot_Text),
        value = context.getString(no_Text),
        component = MOTHERBOARD,
        nameNotLocalized = "anyPCIe_5_x16",
        valueNotLocalized = "No"
    )
    FilterList.anyM2_NVMe_5Yes = Filter(
        name = context.getString(anyNvmeM25Slot_Text),
        value = context.getString(yes_Text),
        component = MOTHERBOARD,
        nameNotLocalized = "anyM2_NVMe_5",
        valueNotLocalized = "Yes"
    )
    FilterList.anyM2_NVMe_5No = Filter(
        name = context.getString(anyNvmeM25Slot_Text),
        value = context.getString(no_Text),
        component = MOTHERBOARD,
        nameNotLocalized = "anyM2_NVMe_5",
        valueNotLocalized = "No"
    )

    //endregion

    //region TODO: RAM Filters initialization

    //endregion

    //region TODO: GPU Filters initialization

    //endregion

    //region TODO: Storage Filters initialization

    //endregion

    //region TODO: PSU Filters initialization

    //endregion


}