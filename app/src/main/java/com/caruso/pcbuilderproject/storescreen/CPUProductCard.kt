package com.caruso.pcbuilderproject.storescreen

import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.*
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ExpandMore
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.caruso.pcbuilderproject.R
import com.caruso.pcbuilderproject.classes.CPU
import com.caruso.pcbuilderproject.classes.GlobalData.Companion.floatToStringChecker
import com.caruso.pcbuilderproject.specslist.CPUSpecs
import com.caruso.pcbuilderproject.ui.theme.PCBuilderProjectTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CPUProductCard(
        modifier: Modifier = Modifier,
        product: CPU,
        nameSize: TextStyle = MaterialTheme.typography.titleMedium,
) {
    var expandedState by remember { mutableStateOf(false) }
    val rotationState by animateFloatAsState(
            targetValue =
            if (expandedState) 180f
            else 0f
    )

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
                Image(
                        painter = product.imagePainter,
                        contentDescription = "CPU Image",
                        modifier = Modifier.size(100.dp)
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
                                    text = product.brand + " " + product.series + " " + product.name,
                                    style = nameSize,
                                    fontWeight = FontWeight.Bold,
                                    maxLines = 2
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
                                        number = product.price,
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
                                    onClick = { /*TODO*/ },
                            ) {
                                Text(text = stringResource(R.string.add_Button))
                            }
                        }
                    }
                }
            }


            if (expandedState) {
                CPUSpecs(
                        cpu = product
                )
            }
        }
    }
}

@Preview
@Composable
fun CPUProductCardPreview() {
    PCBuilderProjectTheme(darkTheme = false) {
        CPUProductCard(
                modifier = Modifier.fillMaxWidth(0.9f),
                nameSize = MaterialTheme.typography.titleMedium,
                product = CPU(
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
                        imagePainter = painterResource(id = R.drawable.cpu)
                )
        )
    }
}