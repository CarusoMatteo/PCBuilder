package com.caruso.pcbuilderproject.componentsclasses

data class Motherboard(
    override var id: Int,               // id = 1
    override var brand: String,         // brand = "MSI"
    override var name: String,          // name = "MPG Z790 CARBON"
    override var price: Float,          // price = 350.72f
    override var imagePainterId: Int, // imagePainter = painterResource(id = R.drawable.motherboard_placeholder)

    // Specific
    var socket: String,                 // socket = "LGA1700"
    var chipset: String,                // chipset = "Z790"
    var formFactor: String,             // formFactor = "ATX"

    // RAM Memory
    var memoryType: String,             // memoryType = "DDR4"
    var memorySlotNumber: Int,          // memorySlotNumber = 4

    // Internet and Bluetooth
    var maxEthernetSpeed: Float,        // maxEthernetSpeed = 2.5f
    var wifiIncluded: Boolean,           // wifiVersion = null
    var bluetoothIncluded: Boolean,      // bluetoothVersion = null,

    // PCIE slot connectivity
    var pcie_x16_5_slotNumber: Int,     // pcie_x16_5_slotNumber = 1
    var pcie_x16_4_slotNumber: Int,     // pcie_x16_4_slotNumber = 1
    var pcie_x8_4_slotNumber: Int,      // pcie_x8_4_slotNumber = 0
    var pcie_x4_4_slotNumber: Int,      // pcie_x4_4_slotNumber = 0
    var pcie_x1_4_slotNumber: Int,      // pcie_x1_4_slotNumber = 0

    // Storage connectivity
    var m2_nvme_5_slotNumber: Int,      // m2_nvme_5_slotNumber = 1
    var m2_nvme_4_slotNumber: Int,      // m2_nvme_4_slotNumber = 4
    var sata_portNumber: Int,           // sata_portNumber = 6

    // USB internal headers
    var usb_a_2_headerNumber: Int,      // usb_a_2_headerNumber = 2
    var usb_a_32_gen1_headerNumber: Int,// usb_a_32_gen1_headerNumber = 1
    var usb_c_32_gen2_headerNumber: Int // usb_c_32_gen2_headerNumber = 1
) : Component(id, brand, name, price, imagePainterId)