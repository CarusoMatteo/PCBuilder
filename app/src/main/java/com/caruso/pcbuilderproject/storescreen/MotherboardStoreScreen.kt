package com.caruso.pcbuilderproject.storescreen

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.caruso.pcbuilderproject.R
import com.caruso.pcbuilderproject.classes.Motherboard

@Composable
fun MotherboardStoreScreen(
        paddingValues: PaddingValues
) {
    Column(
            modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues)
                    .padding(bottom = 80.dp)//, start = 16.dp, end = 16.dp)
    ) {

        Spacer(modifier = Modifier.height(10.dp))

        Column(
                modifier = Modifier
                        .fillMaxSize()
                        .verticalScroll(rememberScrollState()),
                horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.height(10.dp))

            MotherboardProductCard(
                    modifier = Modifier.fillMaxWidth(0.9f),
                    nameSize = MaterialTheme.typography.titleMedium,
                    product = Motherboard(
                            id = 1,
                            brand = "MSI",
                            name = "MPG Z790 CARBON",
                            price = 350.72f,
                            imagePainter = painterResource(id = R.drawable.cpu),
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
                            imagePainter = painterResource(id = R.drawable.cpu),
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

            Spacer(modifier = Modifier.height(10.dp))

        }
    }
}