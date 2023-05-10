package com.caruso.pcbuilderproject.partslistscreen

import androidx.compose.animation.Crossfade
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import com.caruso.pcbuilderproject.ComponentImage
import com.caruso.pcbuilderproject.R
import com.caruso.pcbuilderproject.R.string.*
import com.caruso.pcbuilderproject.componentsclasses.*
import com.caruso.pcbuilderproject.componentsclasses.ComponentType.Companion.CPU
import com.caruso.pcbuilderproject.componentsclasses.ComponentType.Companion.GPU
import com.caruso.pcbuilderproject.componentsclasses.ComponentType.Companion.MOTHERBOARD
import com.caruso.pcbuilderproject.componentsclasses.ComponentType.Companion.PSU
import com.caruso.pcbuilderproject.componentsclasses.ComponentType.Companion.RAM
import com.caruso.pcbuilderproject.componentsclasses.ComponentType.Companion.STORAGE
import com.caruso.pcbuilderproject.incompatibilities.IncompatibilityList
import com.caruso.pcbuilderproject.navigation.BottomBarScreen
import com.caruso.pcbuilderproject.ui.theme.PCBuilderProjectTheme
import com.caruso.pcbuilderproject.utilities.GlobalData
import com.caruso.pcbuilderproject.utilities.GlobalData.Companion.loggedInUser
import com.caruso.pcbuilderproject.utilities.ServerFunctions

@Composable
fun ComponentPartsListItem(
    modifier: Modifier = Modifier,
    component: Component?,
    componentType: Int,
    nameSize: TextStyle = MaterialTheme.typography.titleMedium,
    navController: NavHostController?,
    snackbarHostState: SnackbarHostState?
) {
    val context = LocalContext.current
    val loadingIconVisible = remember { mutableStateOf(false) }
    val scope = rememberCoroutineScope()
    val snackbarMessage = remember { mutableStateOf("") }

    Card(
        modifier = modifier,
    ) {
        Column(
            modifier = Modifier.padding(start = 16.dp, end = 0.dp)
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                ComponentImage(
                    modifier = Modifier.size(100.dp),
                    component = component,
                    componentType = componentType
                )
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
                                text = if (component != null) {
                                    if (component is Cpu)
                                        component.brand + " " + component.series + " " + component.name
                                    else
                                        component.brand + " " + component.name
                                } else {
                                    when (componentType) {
                                        CPU -> context.getString(cpu_Text)
                                        MOTHERBOARD -> context.getString(motherboard_Text)
                                        RAM -> context.getString(ram_Text)
                                        GPU -> context.getString(gpu_Text)
                                        STORAGE -> context.getString(storage_Text)
                                        PSU -> context.getString(psu_Text)
                                        else -> context.getString(psu_Text)
                                    }
                                },
                                style = nameSize,
                                fontWeight = FontWeight.Bold,
                                maxLines = 2,
                                modifier = Modifier.padding(bottom = 5.dp)
                            )

                        }
                    }

                    Row(
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text =
                            if (component != null)
                                GlobalData.floatToStringChecker(
                                    number = component.price,
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
                                    if (component != null) {

                                        if (navController != null && snackbarHostState != null) {
                                            ServerFunctions.addToCart(
                                                username = loggedInUser?.username!!,
                                                componentId = null,
                                                componentType = component.toInt(),
                                                context = context,
                                                loadingIconVisible = loadingIconVisible,
                                                navController = navController,
                                                scope = scope,
                                                snackbarHostState = snackbarHostState,
                                                snackbarMessage = snackbarMessage
                                            )
                                        }

                                        when (component) {
                                            is Cpu -> loggedInUser?.cpuSelected = null
                                            is Motherboard ->
                                                loggedInUser?.motherboardSelected = null

                                            is Ram -> loggedInUser?.ramSelected = null
                                            is Gpu -> loggedInUser?.gpuSelected = null
                                            is Storage -> loggedInUser?.storageSelected = null
                                            is Psu -> loggedInUser?.psuSelected = null
                                        }

                                        IncompatibilityList.checkForIncompatibilities(context = context)

                                        navController?.navigate(BottomBarScreen.PartsListScreen.route) {
                                            popUpTo(id = navController.graph.findStartDestination().id)
                                            launchSingleTop = true
                                        }
                                    } else {
                                        if (navController != null) {

                                            GlobalData.changeStoreProductTypeSelected(
                                                newValue = componentType,
                                                navController = navController
                                            )
                                        }
                                    }
                                }
                            ) {
                                Row(
                                    verticalAlignment = Alignment.CenterVertically
                                ) {
                                    Crossfade(targetState = loadingIconVisible) { loadingIconVisible ->
                                        if (!loadingIconVisible.value) {
                                            if (component != null) {
                                                Icon(
                                                    imageVector = Icons.Filled.Delete,
                                                    contentDescription = null,
                                                    modifier = Modifier.padding(end = 8.dp)
                                                )
                                            } else {
                                                Icon(
                                                    imageVector = Icons.Filled.Search,
                                                    contentDescription = null,
                                                    modifier = Modifier.padding(end = 8.dp)
                                                )
                                            }
                                        } else {
                                            CircularProgressIndicator(
                                                color = MaterialTheme.colorScheme.onPrimary,
                                                strokeWidth = 2.dp,
                                                modifier = Modifier.size(MaterialTheme.typography.labelLarge.fontSize.value.dp)
                                            )
                                        }
                                    }

                                    if (component != null) {
                                        Text(text = stringResource(remove_Button))
                                    } else {
                                        Text(text = stringResource(search_Button))
                                    }
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
fun ComponentPartsListItemPreview() {
    PCBuilderProjectTheme(darkTheme = true) {
        Surface(modifier = Modifier.fillMaxWidth()) {
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                ComponentPartsListItem(
                    modifier = Modifier.fillMaxWidth(0.9f),
                    component = null,
                    componentType = CPU,
                    snackbarHostState = null,
                    navController = null
                )

                Spacer(modifier = Modifier.height(10.dp))

                ComponentPartsListItem(
                    modifier = Modifier.fillMaxWidth(0.9f),
                    componentType = CPU,
                    component = Cpu(
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
                        defaultImagePainterId = R.drawable.cpu_placeholder,
                        imagePainterLink = null
                    ),
                    snackbarHostState = null,
                    navController = null
                )
            }
        }
    }
}