package com.caruso.pcbuilderproject.classes

import androidx.compose.ui.graphics.painter.Painter

/*
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
esp_cpuPowerHeaderNumber = 2,
usb_a_2_headerNumber = 2,
usb_a_32_gen1_headerNumber = 1,
usb_c_32_gen2_headerNumber = 1
 */

class Motherboard(
        override var id: Int,
        override var brand: String,
        override var name: String,
        override var price: Float,
        override var imagePainter: Painter,

        // Specific
        var socket: String,
        var chipset: String,
        var formFactor: String,

        // RAM Memory
        var memoryType: String,
        var memorySlotNumber: Int,

        // Internet and Bluetooth
        var maxEthernetSpeed: Float,
        var wifiVersion: String?,
        var bluetoothVersion: String?,

        // PCIE slot connectivity
        var pcie_x16_5_slotNumber: Int,
        var pcie_x16_4_slotNumber: Int,
        var pcie_x8_4_slotNumber: Int,
        var pcie_x4_4_slotNumber: Int,
        var pcie_x1_4_slotNumber: Int,

        // Storage connectivity
        var m2_nvme_5_slotNumber: Int,
        var m2_nvme_4_slotNumber: Int,
        var m2_sata_slotNumber: Int,
        var sata_portNumber: Int,

        // USB internal headers
        var usb_a_2_headerNumber: Int,
        var usb_a_32_gen1_headerNumber: Int,
        var usb_c_32_gen2_headerNumber: Int
) : Product(id, brand, name, price, imagePainter)