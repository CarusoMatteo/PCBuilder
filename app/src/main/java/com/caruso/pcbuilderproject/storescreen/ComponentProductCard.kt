package com.caruso.pcbuilderproject.storescreen

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.*
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ExpandMore
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.caruso.pcbuilderproject.ComponentImage
import com.caruso.pcbuilderproject.R
import com.caruso.pcbuilderproject.componentsclasses.*
import com.caruso.pcbuilderproject.incompatibilities.IncompatibilityList
import com.caruso.pcbuilderproject.specslist.componentspecslist.*
import com.caruso.pcbuilderproject.ui.theme.PCBuilderProjectTheme
import com.caruso.pcbuilderproject.utilities.*
import com.caruso.pcbuilderproject.utilities.GlobalData.Companion.floatToStringChecker
import com.caruso.pcbuilderproject.utilities.GlobalData.Companion.loggedInUser
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ComponentProductCard(
    modifier: Modifier = Modifier,
    component: Component,
    nameSize: TextStyle = MaterialTheme.typography.titleMedium,
    navController: NavHostController?,
    snackbarHostState: SnackbarHostState?
) {
    var expandedState by remember { mutableStateOf(false) }
    val rotationState by animateFloatAsState(
        targetValue =
        if (expandedState) 180f
        else 0f
    )
    val snackbarMessage = remember { mutableStateOf("") }
    val scope = rememberCoroutineScope()
    val context = LocalContext.current

    val loadingIconVisible = remember { mutableStateOf(false) }

    Card(
        modifier = Modifier
            .animateContentSize(
                animationSpec = tween(
                    durationMillis = 300,
                    easing = LinearOutSlowInEasing
                )
            )
                then modifier,

        onClick = {
            expandedState = !expandedState
        }
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
                    componentType = component.toInt()
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
                                text = if (component is Cpu)
                                    component.brand + " " + component.series + " " + component.name
                                else
                                    component.brand + " " + component.name,
                                style = nameSize,
                                fontWeight = FontWeight.Bold,
                                maxLines = 2,
                                modifier = Modifier.padding(bottom = 5.dp)
                            )
                        }
                        Spacer(modifier = Modifier.width(10.dp))
                        Box(
                            modifier = Modifier.fillMaxWidth(),
                            contentAlignment = Alignment.CenterEnd,
                        ) {
                            IconButton(
                                onClick = { expandedState = !expandedState },
                                modifier = Modifier.rotate(rotationState)
                            ) {
                                Icon(
                                    imageVector = Icons.Default.ExpandMore,
                                    contentDescription = if (!expandedState) "View more" else "View less",
                                )
                            }
                        }
                    }

                    Row(
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = floatToStringChecker(
                                number = component.price,
                                currency = stringResource(R.string.currency).toCharArray()[0],
                                decimalPoint = stringResource(id = R.string.decimalPoint).toCharArray()[0]
                            ),
                            style = MaterialTheme.typography.bodyMedium
                        )

                        Box(
                            modifier = Modifier.fillMaxWidth(),
                            contentAlignment = Alignment.BottomEnd
                        ) {
                            Button(
                                onClick = {
                                    loadingIconVisible.value = true

                                    if (loggedInUser != null) {
                                        when (component) {
                                            is Cpu -> loggedInUser!!.cpuSelected = component
                                            is Motherboard -> loggedInUser!!.motherboardSelected =
                                                component

                                            is Ram -> loggedInUser!!.ramSelected = component
                                            is Gpu -> loggedInUser!!.gpuSelected = component
                                            is Storage -> loggedInUser!!.storageSelected = component
                                            is Psu -> loggedInUser!!.psuSelected = component
                                        }

                                        IncompatibilityList.checkForIncompatibilities(context = context)

                                        if (navController != null && snackbarHostState != null) {
                                            ServerFunctions.addToCart(
                                                username = loggedInUser!!.username!!,
                                                componentId = component.id,
                                                componentType = component.toInt(),
                                                context = context,
                                                loadingIconVisible = loadingIconVisible,
                                                navController = navController,
                                                scope = scope,
                                                snackbarHostState = snackbarHostState,
                                                snackbarMessage = snackbarMessage
                                            )
                                        }
                                    } else {
                                        snackbarMessage.value =
                                            context.getString(R.string.please_login_first_Message)

                                        scope.launch {
                                            snackbarHostState?.showSnackbar(
                                                snackbarMessage.value
                                            )
                                        }

                                        loadingIconVisible.value = false
                                    }
                                },
                            ) {
                                AnimatedVisibility(
                                    visible = loadingIconVisible.value
                                ) {
                                    Box(
                                        modifier = Modifier.padding(end = 8.dp)
                                    ) {
                                        CircularProgressIndicator(
                                            color = MaterialTheme.colorScheme.onPrimary,
                                            strokeWidth = 2.dp,
                                            modifier = Modifier
                                                .size(MaterialTheme.typography.labelLarge.fontSize.value.dp)
                                        )
                                    }
                                }

                                Text(text = stringResource(R.string.add_Button))
                            }
                        }
                    }
                }
            }

            if (expandedState) {
                when (component) {
                    is Cpu -> CPUSpecs(cpu = component)
                    is Motherboard -> MotherboardSpecs(motherboard = component)
                    is Ram -> RAMSpecs(ram = component)
                    // is Gpu -> TODO: GPUSpecs(gpu = component)
                    // is Storage -> TODO: StorageSpecs(storage = component)
                    // is Psu -> TODO: PSUSpecs(psu = component)
                }
            }
        }
    }
}

@Preview
@Composable
fun ComponentProductCardPreview() {
    PCBuilderProjectTheme(darkTheme = true) {
        ComponentProductCard(
            modifier = Modifier.fillMaxWidth(0.9f),
            nameSize = MaterialTheme.typography.titleMedium,
            component = Cpu(
                id = 1,
                brand = "AMD",
                series = "Ryzen 7",
                name = "7800X3D",
                price = 520.99f,
                coreNumber = 8,
                baseClockSpeed = 3.4f,
                powerConsumption = 125,
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