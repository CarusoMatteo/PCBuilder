package com.caruso.pcbuilderproject.navigation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.caruso.pcbuilderproject.R.string.*
import com.caruso.pcbuilderproject.componentsclasses.ComponentType
import com.caruso.pcbuilderproject.filters.Filter
import com.caruso.pcbuilderproject.filters.FilterList
import com.caruso.pcbuilderproject.ui.theme.PCBuilderProjectTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {

            FilterList.brandAMD = Filter(stringResource(brand_Text), "AMD", ComponentType.CPU)
            FilterList.brandIntel = Filter(stringResource(brand_Text), "Intel", ComponentType.CPU)
            FilterList.seriesRyzen3 =
                Filter(stringResource(series_Text), "Ryzen 3", ComponentType.CPU)
            FilterList.seriesRyzen5 =
                Filter(stringResource(series_Text), "Ryzen 5", ComponentType.CPU)
            FilterList.seriesRyzen7 =
                Filter(stringResource(series_Text), "Ryzen 7", ComponentType.CPU)
            FilterList.seriesRyzen9 =
                Filter(stringResource(series_Text), "Ryzen 9", ComponentType.CPU)
            FilterList.seriesCoreI3 =
                Filter(stringResource(series_Text), "Core i3", ComponentType.CPU)
            FilterList.seriesCoreI5 =
                Filter(stringResource(series_Text), "Core i5", ComponentType.CPU)
            FilterList.seriesCoreI7 =
                Filter(stringResource(series_Text), "Core i7", ComponentType.CPU)
            FilterList.seriesCoreI9 =
                Filter(stringResource(series_Text), "Core i9", ComponentType.CPU)
            FilterList.architectureZen3 =
                Filter(stringResource(architecture_Text), "Zen 3", ComponentType.CPU)
            FilterList.architectureZen4 =
                Filter(stringResource(architecture_Text), "Zen 4", ComponentType.CPU)
            FilterList.architectureAlderLake =
                Filter(stringResource(architecture_Text), "Alder Lake", ComponentType.CPU)
            FilterList.architectureRocketLake =
                Filter(stringResource(architecture_Text), "Rocket Lake", ComponentType.CPU)
            FilterList.socketAM4 = Filter(stringResource(socket_Text), "AM4", ComponentType.CPU)
            FilterList.socketAM5 = Filter(stringResource(socket_Text), "AM5", ComponentType.CPU)
            FilterList.socketLGA1700 =
                Filter(stringResource(socket_Text), "LGA1700", ComponentType.CPU)

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