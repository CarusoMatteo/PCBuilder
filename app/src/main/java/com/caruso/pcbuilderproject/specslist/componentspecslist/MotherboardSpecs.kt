package com.caruso.pcbuilderproject.specslist.componentspecslist

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.caruso.pcbuilderproject.R
import com.caruso.pcbuilderproject.R.string.*
import com.caruso.pcbuilderproject.componentsclasses.Motherboard
import com.caruso.pcbuilderproject.specslist.SpecListItem
import com.caruso.pcbuilderproject.ui.theme.PCBuilderProjectTheme

@Composable
fun MotherboardSpecs(
    motherboard: Motherboard
) {
    Column {
        SpecListItem(
            leftItem = stringResource(socket_Text),
            rightItem = motherboard.socket
        )
        Divider(
            Modifier.padding(bottom = 10.dp, end = 16.dp),
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )
        SpecListItem(
            leftItem = stringResource(chipset_Label),
            rightItem = motherboard.chipset
        )
        Divider(
            Modifier.padding(bottom = 10.dp, end = 16.dp),
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )
        SpecListItem(
            leftItem = stringResource(formFactor_Label),
            rightItem = motherboard.formFactor
        )
        Divider(
            Modifier.padding(bottom = 10.dp, end = 16.dp),
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )
        SpecListItem(
            leftItem = stringResource(memoryType_Label),
            rightItem = motherboard.memoryType
        )
        Divider(
            Modifier.padding(bottom = 10.dp, end = 16.dp),
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )
        SpecListItem(
            leftItem = stringResource(memorySlotNumber_Label),
            rightItem = motherboard.memorySlotNumber.toString()
        )
        Divider(
            Modifier.padding(bottom = 10.dp, end = 16.dp),
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )
        SpecListItem(
            leftItem = stringResource(maxEthernetSpeed),
            rightItem = motherboard.maxEthernetSpeed.toString()
        )

        if (motherboard.wifiVersion == null) {
            Divider(
                Modifier.padding(bottom = 10.dp, end = 16.dp),
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )

            SpecListItem(
                leftItem = stringResource(wifiIncluded_Label),
                rightItem = stringResource(id = no_Label)
            )
        } else {
            Divider(
                Modifier.padding(bottom = 10.dp, end = 16.dp),
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )

            SpecListItem(
                leftItem = stringResource(wifiVersion_Label),
                rightItem = motherboard.wifiVersion.toString()
            )
        }

        if (motherboard.bluetoothVersion == null) {
            Divider(
                Modifier.padding(bottom = 10.dp, end = 16.dp),
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )

            SpecListItem(
                leftItem = stringResource(bluetoothIncluded_Label),
                rightItem = stringResource(id = no_Label)
            )
        } else {
            Divider(
                Modifier.padding(bottom = 10.dp, end = 16.dp),
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )

            SpecListItem(
                leftItem = stringResource(bluetoothVersion_Label),
                rightItem = motherboard.bluetoothVersion.toString()
            )
        }

        if (motherboard.pcie_x16_5_slotNumber > 0) {
            Divider(
                Modifier.padding(bottom = 10.dp, end = 16.dp),
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )

            SpecListItem(
                leftItem = stringResource(pcie_x16_5_0_slots_Label),
                rightItem = motherboard.pcie_x16_5_slotNumber.toString()
            )
        }

        if (motherboard.pcie_x16_4_slotNumber > 0) {
            Divider(
                Modifier.padding(bottom = 10.dp, end = 16.dp),
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )

            SpecListItem(
                leftItem = stringResource(pcie_x16_4_0_slots_Label),
                rightItem = motherboard.pcie_x16_4_slotNumber.toString()
            )
        }

        if (motherboard.pcie_x8_4_slotNumber > 0) {
            Divider(
                Modifier.padding(bottom = 10.dp, end = 16.dp),
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )

            SpecListItem(
                leftItem = stringResource(pcie_x8_4_0_slots_Label),
                rightItem = motherboard.pcie_x8_4_slotNumber.toString()
            )
        }

        if (motherboard.pcie_x4_4_slotNumber > 0) {
            Divider(
                Modifier.padding(bottom = 10.dp, end = 16.dp),
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )

            SpecListItem(
                leftItem = stringResource(pcie_x4_4_0_slots_Label),
                rightItem = motherboard.pcie_x4_4_slotNumber.toString()
            )
        }

        if (motherboard.pcie_x1_4_slotNumber > 0) {
            Divider(
                Modifier.padding(bottom = 10.dp, end = 16.dp),
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )

            SpecListItem(
                leftItem = stringResource(pcie_x1_4_0_slots_Label),
                rightItem = motherboard.pcie_x1_4_slotNumber.toString()
            )
        }

        if (motherboard.m2_nvme_5_slotNumber > 0) {
            Divider(
                Modifier.padding(bottom = 10.dp, end = 16.dp),
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )

            SpecListItem(
                leftItem = stringResource(m_2_nvme_5_0_slots_Label),
                rightItem = motherboard.m2_nvme_5_slotNumber.toString()
            )
        }

        if (motherboard.m2_nvme_4_slotNumber > 0) {
            Divider(
                Modifier.padding(bottom = 10.dp, end = 16.dp),
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )

            SpecListItem(
                leftItem = stringResource(m_2_nvme_4_0_slots_Label),
                rightItem = motherboard.m2_nvme_4_slotNumber.toString()
            )
        }

        if (motherboard.m2_sata_slotNumber > 0) {
            Divider(
                Modifier.padding(bottom = 10.dp, end = 16.dp),
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )

            SpecListItem(
                leftItem = stringResource(m_2_sata_slots_Label),
                rightItem = motherboard.m2_sata_slotNumber.toString()
            )
        }

        Divider(
            Modifier.padding(bottom = 10.dp, end = 16.dp),
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )

        SpecListItem(
            leftItem = stringResource(sata_ports_Label),
            rightItem = motherboard.sata_portNumber.toString()
        )

        Divider(
            Modifier.padding(bottom = 10.dp, end = 16.dp),
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )

        SpecListItem(
            leftItem = stringResource(usb_a_2_0_headers_Label),
            rightItem = motherboard.usb_a_2_headerNumber.toString()
        )

        Divider(
            Modifier.padding(bottom = 10.dp, end = 16.dp),
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )

        SpecListItem(
            leftItem = stringResource(usb_a_3_2_gen_1_headers_Label),
            rightItem = motherboard.usb_a_32_gen1_headerNumber.toString()
        )

        Divider(
            Modifier.padding(bottom = 10.dp, end = 16.dp),
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )

        SpecListItem(
            leftItem = stringResource(usb_c_3_2_gen_2_headers_Label),
            rightItem = motherboard.usb_c_32_gen2_headerNumber.toString()
        )
    }
}

@Preview
@Composable
fun MotherboardSpecsPreview() {
    PCBuilderProjectTheme(darkTheme = true) {
        Card(
            modifier = Modifier
                .fillMaxWidth(0.9f)
        ) {
            Column(modifier = Modifier.padding(start = 16.dp, end = 16.dp)) {
                MotherboardSpecs(
                    motherboard = Motherboard(
                        id = 1,
                        brand = "MSI",
                        name = "MPG Z790 CARBON",
                        price = 350.72f,
                        imagePainter = painterResource(id = R.drawable.cpu_placeholder),
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
            }
        }
    }
}