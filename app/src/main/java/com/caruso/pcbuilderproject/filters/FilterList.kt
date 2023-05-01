package com.caruso.pcbuilderproject.filters

import com.caruso.pcbuilderproject.componentsclasses.ComponentType.Companion.CPU

// This class contains every possible filter.
abstract class FilterList {
    companion object {
        //region CPU Filters

        var brandAMD: Filter = Filter("Brand", "AMD", CPU)
        var brandIntel: Filter = Filter("Brand", "Intel", CPU)
        var seriesRyzen3: Filter = Filter("Series", "Ryzen 3", CPU)
        var seriesRyzen5: Filter = Filter("Series", "Ryzen 5", CPU)
        var seriesRyzen7: Filter = Filter("Series", "Ryzen 7", CPU)
        var seriesRyzen9: Filter = Filter("Series", "Ryzen 9", CPU)
        var seriesCoreI3: Filter = Filter("Series", "Core i3", CPU)
        var seriesCoreI5: Filter = Filter("Series", "Core i5", CPU)
        var seriesCoreI7: Filter = Filter("Series", "Core i7", CPU)
        var seriesCoreI9: Filter = Filter("Series", "Core i9", CPU)
        var architectureZen3: Filter = Filter("Architecture", "Zen 3", CPU)
        var architectureZen4: Filter = Filter("Architecture", "Zen 4", CPU)
        var architectureAlderLake: Filter = Filter("Architecture", "Alder Lake", CPU)
        var architectureRocketLake: Filter = Filter("Architecture", "Rocket Lake", CPU)
        var socketAM4: Filter = Filter("Socket", "AM4", CPU)
        var socketAM5: Filter = Filter("Socket", "AM5", CPU)
        var socketLGA1700: Filter = Filter("Socket", "LGA1700", CPU)

        //endregion

        //region Motherboard Filters

        //endregion

    }
}