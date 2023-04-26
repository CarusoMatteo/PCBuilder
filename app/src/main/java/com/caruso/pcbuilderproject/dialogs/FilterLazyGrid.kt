package com.caruso.pcbuilderproject.dialogs

import androidx.compose.animation.Crossfade
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.caruso.pcbuilderproject.classes.GlobalData
import com.caruso.pcbuilderproject.ui.theme.PCBuilderProjectTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FilterLazyGrid(
    modifier: Modifier = Modifier,
    component: String,
    name: String
) {
    LazyVerticalGrid(
        columns = GridCells.Adaptive(100.dp),
        modifier = modifier
    ) {
        items(items = GlobalData.filterList.filter {
            it.component == component && it.name == name
        }) { item ->
            var filterActive by remember {
                mutableStateOf(item.active)
            }

            FilterChip(
                selected = filterActive,
                label = {
                    Text(
                        item.value
                    )
                },
                leadingIcon = {
                    Crossfade(targetState = filterActive) { activeTarget ->
                        if (activeTarget) {
                            Icon(
                                imageVector = Icons.Default.Check,
                                contentDescription = "Selected"
                            )
                        } else {
                            Icon(
                                imageVector = Icons.Default.Add,
                                contentDescription = "Add"
                            )
                        }
                    }
                },
                onClick = {
                    filterActive = !filterActive
                    item.active = !item.active
                },
                modifier = Modifier.padding(end = 10.dp)
            )
        }
    }
}

@Preview
@Composable
fun FilterLazyGridPreview() {
    PCBuilderProjectTheme(darkTheme = true) {
        Surface {
            FilterLazyGrid(
                component = "CPU",
                name = "Series"
            )
        }
    }
}