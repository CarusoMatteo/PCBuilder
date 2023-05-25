package com.caruso.pcbuilderproject.navigation

import android.content.Context
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import com.caruso.pcbuilderproject.R.string.*
import com.caruso.pcbuilderproject.componentsclasses.ComponentType.Companion.CPU
import com.caruso.pcbuilderproject.componentsclasses.ComponentType.Companion.GPU
import com.caruso.pcbuilderproject.componentsclasses.ComponentType.Companion.MOTHERBOARD
import com.caruso.pcbuilderproject.componentsclasses.ComponentType.Companion.PSU
import com.caruso.pcbuilderproject.componentsclasses.ComponentType.Companion.RAM
import com.caruso.pcbuilderproject.componentsclasses.ComponentType.Companion.STORAGE
import com.caruso.pcbuilderproject.filters.Filter
import com.caruso.pcbuilderproject.filters.FilterList
import com.caruso.pcbuilderproject.ui.theme.PCBuilderProjectTheme
import com.caruso.pcbuilderproject.utilities.GlobalData
import com.caruso.pcbuilderproject.utilities.ServerFunctions

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val context = LocalContext.current

            initializeFilters(context = context)

            if (GlobalData.loggedInUser != null) {
                ServerFunctions.selectUser(
                    GlobalData.loggedInUser!!.username,
                    context = context,
                    snackbarMessage = remember { mutableStateOf("") }
                )
            }


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

    FilterList.brandAMD_CPU = Filter(
        name = context.getString(brand_Text),
        value = "AMD",
        component = CPU,
        nameNotLocalized = "brand"
    )
    FilterList.brandIntel_CPU = Filter(
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
    FilterList.coolerIncludedYes = Filter(
        name = context.getString(coolerIncluded_Text),
        value = context.getString(yes_Text),
        component = CPU,
        nameNotLocalized = "coolerIncluded",
        valueNotLocalized = "Yes"
    )
    FilterList.coolerIncludedNo = Filter(
        name = context.getString(coolerIncluded_Text),
        value = context.getString(no_Text),
        component = CPU,
        nameNotLocalized = "coolerIncluded",
        valueNotLocalized = "No"
    )

    // endregion

    //region Motherboard Filters initialization

    FilterList.brandAsus_Motherboard = Filter(
        name = context.getString(brand_Text),
        value = "Asus",
        component = MOTHERBOARD,
        nameNotLocalized = "brand"
    )
    FilterList.brandMSI_Motherboard = Filter(
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
    FilterList.formFactorATX_Motherboard = Filter(
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
    FilterList.memoryTypeDDR4_Motherboard = Filter(
        name = context.getString(memoryType_Text),
        value = "DDR4",
        component = MOTHERBOARD,
        nameNotLocalized = "memoryType"
    )
    FilterList.memoryTypeDDR5_Motherboard = Filter(
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

    //region RAM Filters initialization

    FilterList.brandCorsair_RAM = Filter(
        name = context.getString(brand_Text),
        value = "Corsair",
        component = RAM,
        nameNotLocalized = "brand",
    )
    FilterList.brandGSkill = Filter(
        name = context.getString(brand_Text),
        value = "G.Skill",
        component = RAM,
        nameNotLocalized = "brand",
        valueNotLocalized = "GSkill"
    )
    FilterList.brandKingston = Filter(
        name = context.getString(brand_Text),
        value = "Kingston",
        component = RAM,
        nameNotLocalized = "brand",
    )
    FilterList.memoryTypeDDR4_RAM = Filter(
        name = context.getString(memoryType_Text),
        value = "DDR4",
        component = RAM,
        nameNotLocalized = "memoryType",
    )
    FilterList.memoryTypeDDR5_RAM = Filter(
        name = context.getString(memoryType_Text),
        value = "DDR5",
        component = RAM,
        nameNotLocalized = "memoryType",
    )
    FilterList.totalSize8 = Filter(
        name = context.getString(totalSize_Text),
        value = "8",
        component = RAM,
        nameNotLocalized = "totalSize",
    )
    FilterList.totalSize16 = Filter(
        name = context.getString(totalSize_Text),
        value = "16",
        component = RAM,
        nameNotLocalized = "totalSize",
    )
    FilterList.totalSize32 = Filter(
        name = context.getString(totalSize_Text),
        value = "32",
        component = RAM,
        nameNotLocalized = "totalSize",
    )
    FilterList.totalSize64 = Filter(
        name = context.getString(totalSize_Text),
        value = "64",
        component = RAM,
        nameNotLocalized = "totalSize",
    )
    FilterList.numberOfSticks1 = Filter(
        name = context.getString(numberOfSticks_Text),
        value = "1",
        component = RAM,
        nameNotLocalized = "numberOfSticks",
    )
    FilterList.numberOfSticks2 = Filter(
        name = context.getString(numberOfSticks_Text),
        value = "2",
        component = RAM,
        nameNotLocalized = "numberOfSticks",
    )
    FilterList.numberOfSticks4 = Filter(
        name = context.getString(numberOfSticks_Text),
        value = "4",
        component = RAM,
        nameNotLocalized = "numberOfSticks",
    )
    FilterList.memorySpeed3200 = Filter(
        name = context.getString(memorySpeed_Text),
        value = "3200",
        component = RAM,
        nameNotLocalized = "memorySpeed",
    )
    FilterList.memorySpeed3600 = Filter(
        name = context.getString(memorySpeed_Text),
        value = "3600",
        component = RAM,
        nameNotLocalized = "memorySpeed",
    )
    FilterList.memorySpeed5600 = Filter(
        name = context.getString(memorySpeed_Text),
        value = "5600",
        component = RAM,
        nameNotLocalized = "memorySpeed",
    )
    FilterList.memorySpeed6000 = Filter(
        name = context.getString(memorySpeed_Text),
        value = "6000",
        component = RAM,
        nameNotLocalized = "memorySpeed",
    )
    FilterList.memorySpeed6600 = Filter(
        name = context.getString(memorySpeed_Text),
        value = "6600",
        component = RAM,
        nameNotLocalized = "memorySpeed",
    )

    //endregion

    //region GPU Filters initialization

    FilterList.brandMSI_GPU = Filter(
        name = context.getString(brand_Text),
        value = "MSI",
        component = GPU,
        nameNotLocalized = "brand",
    )
    FilterList.brandAsus_GPU = Filter(
        name = context.getString(brand_Text),
        value = "Asus",
        component = GPU,
        nameNotLocalized = "brand"
    )
    FilterList.brandGigabyte = Filter(
        name = context.getString(brand_Text),
        value = "Gigabyte",
        component = GPU,
        nameNotLocalized = "brand",
    )
    FilterList.chipsetBrandNVIDIA = Filter(
        name = context.getString(chipsetBrand_Text),
        value = "NVIDIA GeForce",
        component = GPU,
        nameNotLocalized = "chipsetBrand",
        valueNotLocalized = "NVIDIA"
    )
    FilterList.chipsetBrandAMD = Filter(
        name = context.getString(chipsetBrand_Text),
        value = "AMD Radeon",
        component = GPU,
        nameNotLocalized = "chipsetBrand",
        valueNotLocalized = "AMD"
    )
    FilterList.chipsetRTX3050 = Filter(
        name = context.getString(chipset_Text),
        value = "RTX 3050",
        component = GPU,
        nameNotLocalized = "chipset",
    )
    FilterList.chipsetRTX3060 = Filter(
        name = context.getString(chipset_Text),
        value = "RTX 3060",
        component = GPU,
        nameNotLocalized = "chipset",
    )
    FilterList.chipsetRTX3080 = Filter(
        name = context.getString(chipset_Text),
        value = "RTX 3080",
        component = GPU,
        nameNotLocalized = "chipset",
    )
    FilterList.chipsetRTX3090 = Filter(
        name = context.getString(chipset_Text),
        value = "RTX 3090",
        component = GPU,
        nameNotLocalized = "chipset",
    )
    FilterList.chipsetRTX4080 = Filter(
        name = context.getString(chipset_Text),
        value = "RTX 4080",
        component = GPU,
        nameNotLocalized = "chipset",
    )
    FilterList.chipsetRTX4090 = Filter(
        name = context.getString(chipset_Text),
        value = "RTX 4090",
        component = GPU,
        nameNotLocalized = "chipset",
    )
    FilterList.chipsetRX6500XT = Filter(
        name = context.getString(chipset_Text),
        value = "RX 6500 XT",
        component = GPU,
        nameNotLocalized = "chipset",
    )
    FilterList.chipsetRX6600XT = Filter(
        name = context.getString(chipset_Text),
        value = "RX 6600 XT",
        component = GPU,
        nameNotLocalized = "chipset",
    )
    FilterList.chipsetRX6800XT = Filter(
        name = context.getString(chipset_Text),
        value = "RX 6800 XT",
        component = GPU,
        nameNotLocalized = "chipset",
    )
    FilterList.chipsetRX6900XT = Filter(
        name = context.getString(chipset_Text),
        value = "RX 6900 XT",
        component = GPU,
        nameNotLocalized = "chipset",
    )
    FilterList.chipsetRX7900XT = Filter(
        name = context.getString(chipset_Text),
        value = "RX 7900 XT",
        component = GPU,
        nameNotLocalized = "chipset",
    )
    FilterList.chipsetRX7900XTX = Filter(
        name = context.getString(chipset_Text),
        value = "RX 7900 XT",
        component = GPU,
        nameNotLocalized = "chipset",
    )
    FilterList.VRAMSize4 = Filter(
        name = context.getString(vramSize_Text),
        value = "4",
        component = GPU,
        nameNotLocalized = "VRAMSize",
    )
    FilterList.VRAMSize8 = Filter(
        name = context.getString(vramSize_Text),
        value = "8",
        component = GPU,
        nameNotLocalized = "VRAMSize",
    )
    FilterList.VRAMSize10 = Filter(
        name = context.getString(vramSize_Text),
        value = "10",
        component = GPU,
        nameNotLocalized = "VRAMSize",
    )
    FilterList.VRAMSize16 = Filter(
        name = context.getString(vramSize_Text),
        value = "16",
        component = GPU,
        nameNotLocalized = "VRAMSize",
    )
    FilterList.VRAMSize20 = Filter(
        name = context.getString(vramSize_Text),
        value = "20",
        component = GPU,
        nameNotLocalized = "VRAMSize",
    )
    FilterList.VRAMSize24 = Filter(
        name = context.getString(vramSize_Text),
        value = "24",
        component = GPU,
        nameNotLocalized = "VRAMSize",
    )
    FilterList.numberOfHDMI1 = Filter(
        name = context.getString(numberOfHdmiPorts_Text),
        value = "1",
        component = GPU,
        nameNotLocalized = "numberOfHDMI",
    )
    FilterList.numberOfHDMI2 = Filter(
        name = context.getString(numberOfHdmiPorts_Text),
        value = "2",
        component = GPU,
        nameNotLocalized = "numberOfHDMI",
    )
    FilterList.numberOfHDMI3 = Filter(
        name = context.getString(numberOfHdmiPorts_Text),
        value = "3",
        component = GPU,
        nameNotLocalized = "numberOfHDMI",
    )
    FilterList.numberOfDP1 = Filter(
        name = context.getString(numberOfDisplayPorts_Text),
        value = "1",
        component = GPU,
        nameNotLocalized = "numberOfDP",
    )
    FilterList.numberOfDP2 = Filter(
        name = context.getString(numberOfDisplayPorts_Text),
        value = "2",
        component = GPU,
        nameNotLocalized = "numberOfDP",
    )
    FilterList.numberOfDP3 = Filter(
        name = context.getString(numberOfDisplayPorts_Text),
        value = "3",
        component = GPU,
        nameNotLocalized = "numberOfDP",
    )

    //endregion

    //region Storage Filters initialization

    FilterList.brandSamsung_Storage = Filter(
        name = context.getString(brand_Text),
        value = "Samsung",
        component = STORAGE,
        nameNotLocalized = "brand",
    )
    FilterList.brandGigabyte_Storage = Filter(
        name = context.getString(brand_Text),
        value = "Gigabyte",
        component = STORAGE,
        nameNotLocalized = "brand"
    )
    FilterList.brandCorsair_Storage = Filter(
        name = context.getString(brand_Text),
        value = "Corsair",
        component = STORAGE,
        nameNotLocalized = "brand",
    )
    FilterList.typeNVME4 = Filter(
        name = context.getString(type_text),
        value = "NVMe M.2 4.0",
        component = STORAGE,
        nameNotLocalized = "type",
        valueNotLocalized = "NVME4"
    )
    FilterList.typeNVME5 = Filter(
        name = context.getString(type_text),
        value = "NVMe M.2 5.0",
        component = STORAGE,
        nameNotLocalized = "type",
        valueNotLocalized = "NVME5"
    )
    FilterList.typeSATA = Filter(
        name = context.getString(type_text),
        value = "SATA",
        component = STORAGE,
        nameNotLocalized = "type",
    )
    FilterList.size250 = Filter(
        name = context.getString(storageSize_Text),
        value = "250",
        component = STORAGE,
        nameNotLocalized = "size",
    )
    FilterList.size500 = Filter(
        name = context.getString(storageSize_Text),
        value = "500",
        component = STORAGE,
        nameNotLocalized = "size",
    )
    FilterList.size1000 = Filter(
        name = context.getString(storageSize_Text),
        value = "1000",
        component = STORAGE,
        nameNotLocalized = "size",
    )
    FilterList.size2000 = Filter(
        name = context.getString(storageSize_Text),
        value = "2000",
        component = STORAGE,
        nameNotLocalized = "size",
    )

    //endregion

    //region PSU Filters initialization

    FilterList.brandCorsair_PSU = Filter(
        name = context.getString(brand_Text),
        value = "Corsair",
        component = PSU,
        nameNotLocalized = "brand",
    )
    FilterList.brandSeaSonic_PSU = Filter(
        name = context.getString(brand_Text),
        value = "SeaSonic",
        component = PSU,
        nameNotLocalized = "brand",
    )
    FilterList.wattage550 = Filter(
        name = context.getString(wattage_Text),
        value = "550",
        component = PSU,
        nameNotLocalized = "wattage",
    )
    FilterList.wattage650 = Filter(
        name = context.getString(wattage_Text),
        value = "650",
        component = PSU,
        nameNotLocalized = "wattage",
    )
    FilterList.wattage750 = Filter(
        name = context.getString(wattage_Text),
        value = "750",
        component = PSU,
        nameNotLocalized = "wattage",
    )
    FilterList.wattage850 = Filter(
        name = context.getString(wattage_Text),
        value = "850",
        component = PSU,
        nameNotLocalized = "wattage",
    )
    FilterList.wattage1000 = Filter(
        name = context.getString(wattage_Text),
        value = "1000",
        component = PSU,
        nameNotLocalized = "wattage",
    )
    FilterList.formFactorATX_PSU = Filter(
        name = context.getString(formFactor_Text),
        value = "ATX",
        component = PSU,
        nameNotLocalized = "formFactor",
    )
    FilterList.formFactorSFF_PSU = Filter(
        name = context.getString(formFactor_Text),
        value = "SFF",
        component = PSU,
        nameNotLocalized = "formFactor",
    )
    FilterList.length160 = Filter(
        name = context.getString(length_Text),
        value = "160",
        component = PSU,
        nameNotLocalized = "length",
    )
    FilterList.length170 = Filter(
        name = context.getString(length_Text),
        value = "170",
        component = PSU,
        nameNotLocalized = "length",
    )
    FilterList.length180 = Filter(
        name = context.getString(length_Text),
        value = "180",
        component = PSU,
        nameNotLocalized = "length",
    )

    //endregion
}