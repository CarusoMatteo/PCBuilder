package com.caruso.pcbuilderproject.accountscreen

import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.*
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ExpandMore
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.caruso.pcbuilderproject.R
import com.caruso.pcbuilderproject.R.string.*
import com.caruso.pcbuilderproject.componentsclasses.*
import com.caruso.pcbuilderproject.specslist.SpecsListItem
import com.caruso.pcbuilderproject.specslist.componentspecslist.*
import com.caruso.pcbuilderproject.ui.theme.*
import com.caruso.pcbuilderproject.user.Order
import com.caruso.pcbuilderproject.utilities.GlobalData
import java.time.LocalDate

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun OrderCard(
    modifier: Modifier = Modifier,
    order: Order,
    nameSize: TextStyle = MaterialTheme.typography.titleLarge,
    startingExpandedState: Boolean = false
) {
    var expandedState by remember { mutableStateOf(startingExpandedState) }
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
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(start = 16.dp, end = 0.dp)
        ) {
            Box(modifier = Modifier.fillMaxWidth(0.8f)) {
                Text(
                    text = "Order #${order.orderId}",
                    style = nameSize,
                    fontWeight = FontWeight.Bold
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

        if (expandedState) {
            if (order.cpu != null) {
                OrderCardListItem(
                    modifier = Modifier.padding(start = 16.dp, end = 16.dp, bottom = 10.dp),
                    component = order.cpu
                )
            }

            if (order.motherboard != null) {
                OrderCardListItem(
                    modifier = Modifier.padding(start = 16.dp, end = 16.dp, bottom = 10.dp),
                    component = order.motherboard
                )
            }

            if (order.ram != null) {
                OrderCardListItem(
                    modifier = Modifier.padding(start = 16.dp, end = 16.dp, bottom = 10.dp),
                    component = order.ram
                )
            }

            if (order.gpu != null) {
                OrderCardListItem(
                    modifier = Modifier.padding(start = 16.dp, end = 16.dp, bottom = 10.dp),
                    component = order.gpu
                )
            }

            if (order.storage != null) {
                OrderCardListItem(
                    modifier = Modifier.padding(start = 16.dp, end = 16.dp, bottom = 10.dp),
                    component = order.storage
                )
            }

            if (order.psu != null) {
                OrderCardListItem(
                    modifier = Modifier.padding(start = 16.dp, end = 16.dp, bottom = 10.dp),
                    component = order.psu
                )
            }

            Column(Modifier.fillMaxWidth()) {
                Spacer(modifier = Modifier.height(5.dp))
                Divider(
                    modifier = Modifier.padding(horizontal = 10.dp),
                    color = if (isSystemInDarkTheme())
                        md_theme_light_outlineVariant
                    else
                        md_theme_dark_outlineVariant
                )
                Spacer(modifier = Modifier.height(15.dp))
            }
        }

        SpecsListItem(
            modifier = Modifier.padding(start = 16.dp),
            leftItem = "Total",
            rightItem = GlobalData.priceInFloatToString(
                number = order.totalCost,
                currency = stringResource(currency),
                decimalPoint = stringResource(decimalPoint)
            )
        )
    }
}

@Preview
@Composable
fun OrderCardPreview() {
    PCBuilderProjectTheme(darkTheme = true) {

        Surface(modifier = Modifier.fillMaxWidth()) {
            Column(
                modifier = Modifier.padding(top = 10.dp, bottom = 10.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                OrderCard(
                    modifier = Modifier.fillMaxWidth(0.9f),
                    order = Order(
                        orderId = 1001,
                        date = LocalDate.now(),
                        totalCost = 4881.33f,
                        cpu = Cpu(
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
                        motherboard = Motherboard(
                            id = 1,
                            brand = "MSI",
                            name = "MPG Z790 CARBON",
                            price = 350.72f,
                            defaultImagePainterId = R.drawable.cpu_placeholder,
                            socket = "LGA1700",
                            chipset = "Z790",
                            formFactor = "ATX",
                            memoryType = "DDR4",
                            memorySlotNumber = 4,
                            maxEthernetSpeed = 2.5f,
                            wifiIncluded = false,
                            bluetoothIncluded = false,
                            pcie_x16_5_slotNumber = 1,
                            pcie_x16_4_slotNumber = 1,
                            pcie_x8_4_slotNumber = 0,
                            pcie_x4_4_slotNumber = 0,
                            pcie_x1_4_slotNumber = 0,
                            m2_nvme_5_slotNumber = 1,
                            m2_nvme_4_slotNumber = 4,
                            sata_portNumber = 6,
                            usb_a_2_headerNumber = 2,
                            usb_a_32_gen1_headerNumber = 1,
                            usb_c_32_gen2_headerNumber = 1,
                            imagePainterLink = null
                        ),
                        ram = Ram(
                            id = 1,
                            brand = "Corsair",
                            name = "Name",
                            price = 90.00f,
                            defaultImagePainterId = R.drawable.ram_placeholder,
                            imagePainterLink = null,
                            memoryType = "DDR4",
                            memorySpeed = 3200,
                            totalSize = 32,
                            numberOfSticks = 2
                        ),
                        gpu = Gpu(
                            id = 1,
                            brand = "Asus",
                            name = "TUF",
                            price = 1600f,
                            defaultImagePainterId = R.drawable.gpu_placeholder,
                            imagePainterLink = null,
                            chipsetBrand = "NVIDIA",
                            chipset = "GeForce RTX 4080",
                            vRamSize = 16,
                            clockSpeed = 2.205f,
                            length = 348,
                            size = 4,
                            powerConsumption = 320,
                            hdmiPortNumber = 2,
                            displayPortNumber = 3
                        ),
                        storage = Storage(
                            id = 1,
                            brand = "Samsung",
                            name = "980 Pro",
                            price = 100f,
                            defaultImagePainterId = R.drawable.storage_placeholder,
                            imagePainterLink = null,
                            storageType = "NVMe M.2 5.0",
                            storageSize = 2000
                        ),
                        psu = Psu(
                            id = 1,
                            brand = "Corsair",
                            name = "RM850x",
                            price = 120f,
                            defaultImagePainterId = R.drawable.psu_placeholder,
                            imagePainterLink = null,
                            wattage = 850,
                            formFactor = "ATX",
                            length = 160
                        )
                    ),
                    startingExpandedState = true
                )
            }
        }
    }
}