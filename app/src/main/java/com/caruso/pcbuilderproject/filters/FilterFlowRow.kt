package com.caruso.pcbuilderproject.filters

import androidx.compose.animation.Crossfade
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.caruso.pcbuilderproject.componentsclasses.ComponentType
import com.caruso.pcbuilderproject.ui.theme.PCBuilderProjectTheme
import com.caruso.pcbuilderproject.utilities.GlobalData

@OptIn(ExperimentalMaterial3Api::class, ExperimentalLayoutApi::class)
@Composable
fun FilterFlowRow(
    modifier: Modifier = Modifier,
    component: Int,
    name: String
) {
    FlowRow(
        horizontalArrangement = Arrangement.spacedBy(10.dp),
        modifier = modifier
    ) {
        GlobalData.filterList.filter { it.component == component && it.name == name }
            .forEach { item ->
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
                    }
                )
            }
    }
}

@Preview
@Composable
fun FilterFlowRowPreview() {
    PCBuilderProjectTheme(darkTheme = true) {
        Surface {
            FilterFlowRow(
                component = ComponentType.CPU,
                name = "Series"
            )
        }
    }
}