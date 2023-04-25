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
import com.caruso.pcbuilderproject.classes.GlobalData.Companion.floatToStringChecker
import com.caruso.pcbuilderproject.classes.Motherboard
import com.caruso.pcbuilderproject.specslist.MotherboardSpecs
import com.caruso.pcbuilderproject.ui.theme.PCBuilderProjectTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MotherboardProductCard(
    modifier: Modifier = Modifier,
    product: Motherboard,
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
                                text = product.brand + " " + product.name,
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
                MotherboardSpecs(
                    motherboard = product
                )
            }
        }
    }
}


@Preview
@Composable
fun MotherboardProductCardPreview() {
    PCBuilderProjectTheme(darkTheme = false) {
        Column(
            modifier = Modifier.fillMaxWidth()
        ) {
            MotherboardProductCard(
                modifier = Modifier.fillMaxWidth(0.9f),
                nameSize = MaterialTheme.typography.titleMedium,
                product = Motherboard(
                    id = 1,
                    brand = "MSI",
                    name = "MPG Z790 CARBON",
                    price = 350.72f,
                    imagePainter = painterResource(id = R.drawable.motherboard_placeholder),
                    socket = "LGA1700",
                    chipset = "Z790",
                    formFactor = "ATX",
                    memoryType = "DDR4",
                    memorySlotNumber = 4,
                    maxEthernetSpeed = 2.5f,
                    wifiVersion = null,
                    bluetoothVersion = null,
                    pcie_x16_5_slotNumber = 1,
                    pcie_x16_4_slotNumber = 1,
                    pcie_x8_4_slotNumber = 0,
                    pcie_x4_4_slotNumber = 0,
                    pcie_x1_4_slotNumber = 0,
                    m2_nvme_5_slotNumber = 1,
                    m2_nvme_4_slotNumber = 4,
                    m2_sata_slotNumber = 0,
                    sata_portNumber = 6,
                    usb_a_2_headerNumber = 2,
                    usb_a_32_gen1_headerNumber = 1,
                    usb_c_32_gen2_headerNumber = 1
                )
            )

            Spacer(modifier = Modifier.height(10.dp))

            MotherboardProductCard(
                modifier = Modifier.fillMaxWidth(0.9f),
                nameSize = MaterialTheme.typography.titleMedium,
                product = Motherboard(
                    id = 2,
                    brand = "MSI",
                    name = "MPG X670E CARBON WIFI",
                    price = 480.00f,
                    imagePainter = painterResource(id = R.drawable.motherboard_placeholder),
                    socket = "AM5",
                    chipset = "X670E",
                    formFactor = "ATX",
                    memoryType = "DDR5",
                    memorySlotNumber = 4,
                    maxEthernetSpeed = 2.5f,
                    wifiVersion = "6E",
                    bluetoothVersion = "5.3",
                    pcie_x16_5_slotNumber = 2,
                    pcie_x16_4_slotNumber = 1,
                    pcie_x8_4_slotNumber = 0,
                    pcie_x4_4_slotNumber = 0,
                    pcie_x1_4_slotNumber = 0,
                    m2_nvme_5_slotNumber = 2,
                    m2_nvme_4_slotNumber = 2,
                    m2_sata_slotNumber = 0,
                    sata_portNumber = 6,
                    usb_a_2_headerNumber = 2,
                    usb_a_32_gen1_headerNumber = 2,
                    usb_c_32_gen2_headerNumber = 1
                )
            )
        }
    }
}