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
import com.caruso.pcbuilderproject.componentsclasses.ComponentType
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
        component = ComponentType.CPU
    )
    FilterList.brandIntel = Filter(
        name = context.getString(brand_Text),
        value = "Intel",
        component = ComponentType.CPU
    )
    FilterList.seriesRyzen3 = Filter(
        name = context.getString(series_Text),
        value = "Ryzen 3",
        component = ComponentType.CPU
    )
    FilterList.seriesRyzen5 = Filter(
        name = context.getString(series_Text),
        value = "Ryzen 5",
        component = ComponentType.CPU
    )
    FilterList.seriesRyzen7 = Filter(
        name = context.getString(series_Text),
        value = "Ryzen 7",
        component = ComponentType.CPU
    )
    FilterList.seriesRyzen9 = Filter(
        name = context.getString(series_Text),
        value = "Ryzen 9",
        component = ComponentType.CPU
    )
    FilterList.seriesCoreI3 = Filter(
        name = context.getString(series_Text),
        value = "Core i3",
        component = ComponentType.CPU
    )
    FilterList.seriesCoreI5 = Filter(
        name = context.getString(series_Text),
        value = "Core i5",
        component = ComponentType.CPU
    )
    FilterList.seriesCoreI7 = Filter(
        name = context.getString(series_Text),
        value = "Core i7",
        component = ComponentType.CPU
    )
    FilterList.seriesCoreI9 = Filter(
        name = context.getString(series_Text),
        value = "Core i9",
        component = ComponentType.CPU
    )
    FilterList.architectureZen3 = Filter(
        name = context.getString(architecture_Text),
        value = "Zen 3",
        component = ComponentType.CPU
    )
    FilterList.architectureZen4 = Filter(
        name = context.getString(architecture_Text),
        value = "Zen 4",
        component = ComponentType.CPU
    )
    FilterList.architectureAlderLake = Filter(
        name = context.getString(architecture_Text),
        value = "Alder Lake",
        component = ComponentType.CPU
    )
    FilterList.architectureRocketLake = Filter(
        name = context.getString(architecture_Text),
        value = "Rocket Lake",
        component = ComponentType.CPU
    )
    FilterList.socketAM4 = Filter(
        name = context.getString(socket_Text),
        value = "AM4",
        component = ComponentType.CPU
    )
    FilterList.socketAM5 = Filter(
        name = context.getString(socket_Text),
        value = "AM5",
        component = ComponentType.CPU
    )
    FilterList.socketLGA1700 = Filter(
        name = context.getString(socket_Text),
        value = "LGA1700",
        component = ComponentType.CPU
    )

    // endregion

    //region TODO: Motherboard Filters initialization

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