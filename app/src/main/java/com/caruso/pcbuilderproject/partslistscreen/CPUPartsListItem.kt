package com.caruso.pcbuilderproject.partslistscreen

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import com.caruso.pcbuilderproject.R
import com.caruso.pcbuilderproject.R.string.*
import com.caruso.pcbuilderproject.classes.CPU
import com.caruso.pcbuilderproject.classes.GlobalData
import com.caruso.pcbuilderproject.navigation.BottomBarScreen
import com.caruso.pcbuilderproject.ui.theme.PCBuilderProjectTheme

@Composable
fun CPUPartsListItem(
    modifier: Modifier = Modifier,
    cpu: CPU? = GlobalData.loggedInUser.cpuSelected,
    nameSize: TextStyle = MaterialTheme.typography.titleMedium,
    navController: NavHostController? = null
) {
    Card(
        modifier = modifier,
    ) {
        Column(
            modifier = Modifier.padding(start = 16.dp, end = 0.dp)
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                if (cpu != null) {
                    Image(
                        painter = cpu.imagePainter,
                        contentDescription = "CPU Image",
                        modifier = Modifier.size(100.dp)
                    )
                } else {
                    Image(
                        painter = painterResource(id = R.drawable.cpu_placeholder),
                        contentDescription = "CPU Image",
                        modifier = Modifier.size(100.dp)
                    )
                }

                Column(
                    modifier = Modifier
                        .padding(all = 20.dp),
                    verticalArrangement = Arrangement.SpaceAround
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Box(modifier = Modifier.fillMaxWidth(0.8f)) {
                            Text(
                                text = if (cpu != null) {
                                    cpu.brand + " " + cpu.series + " " + cpu.name
                                } else {
                                    stringResource(cpu_Text)
                                },
                                style = nameSize,
                                fontWeight = FontWeight.Bold,
                                maxLines = 2
                            )
                        }
                    }

                    Row(
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text =
                            if (cpu != null)
                                GlobalData.floatToStringChecker(
                                    number = cpu.price,
                                    currency = stringResource(currency).toCharArray()[0],
                                )
                            else
                                stringResource(empty_Text),
                            style = MaterialTheme.typography.bodyMedium
                        )

                        Spacer(modifier = Modifier.width(5.dp))

                        Box(
                            modifier = Modifier.fillMaxWidth(),
                            contentAlignment = Alignment.BottomEnd
                        ) {
                            Button(
                                onClick = {
                                    if (cpu != null) {
                                        GlobalData.loggedInUser.cpuSelected = null

                                        navController?.navigate(BottomBarScreen.PartsListScreen.route) {
                                            popUpTo(id = navController.graph.findStartDestination().id)
                                            launchSingleTop = true
                                        }
                                    } else {
                                        if (navController != null) {
                                            GlobalData.changeStoreProductTypeSelected(
                                                newValue = 1,
                                                navController = navController
                                            )
                                        }
                                    }
                                }
                            ) {
                                if (cpu != null) {
                                    Icon(
                                        imageVector = Icons.Filled.Delete,
                                        contentDescription = null,
                                        modifier = Modifier.padding(end = 8.dp)
                                    )
                                    Text(text = stringResource(remove_Button))
                                } else {
                                    Icon(
                                        imageVector = Icons.Filled.Add,
                                        contentDescription = null,
                                        modifier = Modifier.padding(end = 8.dp)
                                    )
                                    Text(text = stringResource(add_Button))
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}

@Preview
@Composable
fun CPUPartsListItemPreview() {
    PCBuilderProjectTheme(darkTheme = true) {
        Surface(modifier = Modifier.fillMaxWidth()) {
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                CPUPartsListItem(
                    modifier = Modifier.fillMaxWidth(0.9f),
                    cpu = null
                )

                Spacer(modifier = Modifier.height(10.dp))

                CPUPartsListItem(
                    modifier = Modifier.fillMaxWidth(0.9f),
                    cpu = CPU(
                        id = 1,
                        brand = "AMD",
                        series = "Ryzen 7",
                        name = "7800X3D",
                        price = 520.99f,
                        coreNumber = 8,
                        baseClockSpeed = 4.2f,
                        powerConsumption = 120,
                        architecture = "Zen 4",
                        socket = "AM5",
                        integratedGraphics = true,
                        fanIncluded = false,
                        imagePainter = painterResource(id = R.drawable.cpu_placeholder)
                    )
                )
            }
        }
    }
}