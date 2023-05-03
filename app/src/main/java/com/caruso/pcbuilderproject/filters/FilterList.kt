package com.caruso.pcbuilderproject.filters

// This class contains every possible filter.
abstract class FilterList {
    companion object {

        // The Filters need to be declared here,
        // however it must be initialized in MainActivity.

        // region CPU Filters declaration

        var brandAMD: Filter = Filter()
        var brandIntel: Filter = Filter()
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
        var socketAM4: Filter = Filter()
        var socketAM5: Filter = Filter()
        var socketLGA1700: Filter = Filter()
        var integratedGraphicsYes: Filter = Filter()
        var integratedGraphicsNo: Filter = Filter()

        // endregion

        //region TODO: Motherboard Filters declaration

        //endregion

        //region TODO: RAM Filters declaration

        //endregion

        //region TODO: GPU Filters declaration

        //endregion

        //region TODO: Storage Filters declaration

        //endregion

        //region TODO: PSU Filters declaration

        //endregion
    }
}