package com.caruso.pcbuilderproject.filters.componentfilter

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import com.caruso.pcbuilderproject.R.string.*
import com.caruso.pcbuilderproject.componentsclasses.ComponentType.Companion.CPU
import com.caruso.pcbuilderproject.filters.FilterLazyGrid
import com.caruso.pcbuilderproject.filters.FilterListHeader
import com.caruso.pcbuilderproject.ui.theme.PCBuilderProjectTheme

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun CPUFilterDialog(
    filterDialogOpen: MutableState<Boolean>
) {
    //val context = LocalContext.current

    Dialog(
        properties = DialogProperties(usePlatformDefaultWidth = false),
        onDismissRequest = {
            filterDialogOpen.value = false
        },
    ) {
        Scaffold(
            modifier = Modifier
                .fillMaxSize(1f),
            topBar = {
                TopAppBar(
                    navigationIcon = {
                        IconButton(onClick = {
                            filterDialogOpen.value = false
                        }) {
                            Icon(
                                imageVector = Icons.Default.ArrowBack,
                                contentDescription = "Close"
                            )
                        }
                    },
                    title = { Text(text = "Filters") }
                )
            }
        ) { paddingValues ->
            Column(
                modifier = Modifier
                    .padding(paddingValues)
                    .padding(20.dp)
            ) {
                FilterListHeader(
                    text = stringResource(brand_Text)
                )

                FilterLazyGrid(
                    component = CPU,
                    name = "Brand"
                )

                Divider(modifier = Modifier.padding(top = 15.dp, bottom = 15.dp))

                FilterListHeader(
                    text = stringResource(series_Text)
                )

                FilterLazyGrid(
                    component = CPU,
                    name = "Series"
                )

                Divider(modifier = Modifier.padding(top = 15.dp, bottom = 15.dp))

            }
        }
    }
}

@Preview
@Composable
fun CPUFilterDialogPreview() {
    PCBuilderProjectTheme(darkTheme = true) {
        CPUFilterDialog(
            filterDialogOpen = remember {
                mutableStateOf(true)
            }
        )
    }
}