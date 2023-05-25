package com.caruso.pcbuilderproject.filters

// This class contains every possible filter.
abstract class FilterList {
    companion object {

        // The Filters need to be declared here,
        // however it must be initialized in MainActivity.

        // region CPU Filters declaration

        var brandAMD_CPU: Filter = Filter()
        var brandIntel_CPU: Filter = Filter()
        var seriesRyzen5: Filter = Filter()
        var seriesRyzen7: Filter = Filter()
        var seriesRyzen9: Filter = Filter()
        var seriesCoreI3: Filter = Filter()
        var seriesCoreI5: Filter = Filter()
        var seriesCoreI7: Filter = Filter()
        var seriesCoreI9: Filter = Filter()
        var architectureZen3: Filter = Filter()
        var architectureZen4: Filter = Filter()
        var architectureAlderLake: Filter = Filter()
        var architectureRaptorLake: Filter = Filter()
        var socketAM4_CPU: Filter = Filter()
        var socketAM5_CPU: Filter = Filter()
        var socketLGA1700_CPU: Filter = Filter()
        var integratedGraphicsYes: Filter = Filter()
        var integratedGraphicsNo: Filter = Filter()
        var coolerIncludedYes: Filter = Filter()
        var coolerIncludedNo: Filter = Filter()

        // endregion

        //region Motherboard Filters declaration

        var brandAsus_Motherboard: Filter = Filter()
        var brandMSI_Motherboard: Filter = Filter()
        var socketAM4_Motherboard: Filter = Filter()
        var socketAM5_Motherboard: Filter = Filter()
        var socketLGA1700_Motherboard: Filter = Filter()
        var chipsetB550: Filter = Filter()
        var chipsetB650: Filter = Filter()
        var chipsetX570: Filter = Filter()
        var chipsetX670: Filter = Filter()
        var chipsetX670E: Filter = Filter()
        var chipsetZ690: Filter = Filter()
        var chipsetZ790: Filter = Filter()
        var formFactorATX_Motherboard: Filter = Filter()
        var formFactorMicroATX: Filter = Filter()
        var formFactorMiniITX: Filter = Filter()
        var memoryTypeDDR4_Motherboard: Filter = Filter()
        var memoryTypeDDR5_Motherboard: Filter = Filter()
        var memorySlotNumber2: Filter = Filter()
        var memorySlotNumber4: Filter = Filter()
        var maxEthernetSpeed1: Filter = Filter()
        var maxEthernetSpeed25: Filter = Filter()
        var wifiIncludedYes: Filter = Filter()
        var wifiIncludedNo: Filter = Filter()
        var bluetoothIncludedYes: Filter = Filter()
        var bluetoothIncludedNo: Filter = Filter()
        var anyPCIe_5_x16Yes: Filter = Filter()
        var anyPCIe_5_x16No: Filter = Filter()
        var anyM2_NVMe_5Yes: Filter = Filter()
        var anyM2_NVMe_5No: Filter = Filter()

        //endregion

        //region RAM Filters declaration

        var brandCorsair_RAM: Filter = Filter()
        var brandGSkill: Filter = Filter()
        var brandKingston: Filter = Filter()
        var memoryTypeDDR4_RAM: Filter = Filter()
        var memoryTypeDDR5_RAM: Filter = Filter()
        var totalSize8: Filter = Filter()
        var totalSize16: Filter = Filter()
        var totalSize32: Filter = Filter()
        var totalSize64: Filter = Filter()
        var numberOfSticks1: Filter = Filter()
        var numberOfSticks2: Filter = Filter()
        var numberOfSticks4: Filter = Filter()
        var memorySpeed3200: Filter = Filter()
        var memorySpeed3600: Filter = Filter()
        var memorySpeed5600: Filter = Filter()
        var memorySpeed6000: Filter = Filter()
        var memorySpeed6600: Filter = Filter()

        //endregion

        //region GPU Filters declaration

        var brandMSI_GPU: Filter = Filter()
        var brandAsus_GPU: Filter = Filter()
        var brandGigabyte: Filter = Filter()
        var chipsetBrandNVIDIA: Filter = Filter()
        var chipsetBrandAMD: Filter = Filter()
        var chipsetRTX3050: Filter = Filter()
        var chipsetRTX3060: Filter = Filter()
        var chipsetRTX3080: Filter = Filter()
        var chipsetRTX3090: Filter = Filter()
        var chipsetRTX4080: Filter = Filter()
        var chipsetRTX4090: Filter = Filter()
        var chipsetRX6500XT: Filter = Filter()
        var chipsetRX6600XT: Filter = Filter()
        var chipsetRX6800XT: Filter = Filter()
        var chipsetRX6900XT: Filter = Filter()
        var chipsetRX7900XT: Filter = Filter()
        var chipsetRX7900XTX: Filter = Filter()
        var VRAMSize4: Filter = Filter()
        var VRAMSize8: Filter = Filter()
        var VRAMSize10: Filter = Filter()
        var VRAMSize16: Filter = Filter()
        var VRAMSize20: Filter = Filter()
        var VRAMSize24: Filter = Filter()
        var numberOfHDMI1: Filter = Filter()
        var numberOfHDMI2: Filter = Filter()
        var numberOfHDMI3: Filter = Filter()
        var numberOfDP1: Filter = Filter()
        var numberOfDP2: Filter = Filter()
        var numberOfDP3: Filter = Filter()

        //endregion

        //region Storage Filters declaration

        var brandSamsung_Storage: Filter = Filter()
        var brandGigabyte_Storage: Filter = Filter()
        var brandCorsair_Storage: Filter = Filter()
        var typeNVME4: Filter = Filter()
        var typeNVME5: Filter = Filter()
        var typeSATA: Filter = Filter()
        var size250: Filter = Filter()
        var size500: Filter = Filter()
        var size1000: Filter = Filter()
        var size2000: Filter = Filter()

        //endregion

        //region PSU Filters declaration

        var brandCorsair_PSU: Filter = Filter()
        var brandSeaSonic_PSU: Filter = Filter()
        var wattage550: Filter = Filter()
        var wattage650: Filter = Filter()
        var wattage750: Filter = Filter()
        var wattage850: Filter = Filter()
        var wattage1000: Filter = Filter()
        var formFactorATX_PSU: Filter = Filter()
        var formFactorSFF_PSU: Filter = Filter()
        var length160: Filter = Filter()
        var length170: Filter = Filter()
        var length180: Filter = Filter()

        //endregion
    }
}