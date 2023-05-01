package com.caruso.pcbuilderproject.classes

import com.caruso.pcbuilderproject.classes.ComponentType.Companion.CPU

// This class contains every possible filter.
class FilterList {
    companion object {
        //region CPU Filters

        val brandAMD: Filter = Filter("Brand", "AMD", CPU)
        val brandIntel: Filter = Filter("Brand", "Intel", CPU)
        val seriesRyzen3: Filter = Filter("Series", "Ryzen 3", CPU)
        val seriesRyzen5: Filter = Filter("Series", "Ryzen 5", CPU)
        val seriesRyzen7: Filter = Filter("Series", "Ryzen 7", CPU)
        val seriesRyzen9: Filter = Filter("Series", "Ryzen 9", CPU)
        val seriesCoreI3: Filter = Filter("Series", "Core i3", CPU)
        val seriesCoreI5: Filter = Filter("Series", "Core i5", CPU)
        val seriesCoreI7: Filter = Filter("Series", "Core i7", CPU)
        val seriesCoreI9: Filter = Filter("Series", "Core i9", CPU)
        val architectureZen3: Filter = Filter("Architecture", "Zen 3", CPU)
        val architectureZen4: Filter = Filter("Architecture", "Zen 4", CPU)
        val architectureAlderLake: Filter = Filter("Architecture", "Alder Lake", CPU)
        val architectureRocketLake: Filter = Filter("Architecture", "Rocket Lake", CPU)
        val socketAM4: Filter = Filter("Socket", "AM4", CPU)
        val socketAM5: Filter = Filter("Socket", "AM5", CPU)
        val socketLGA1700: Filter = Filter("Socket", "LGA1700", CPU)

        //endregion

        //region Motherboard Filters

        //endregion

    }
}