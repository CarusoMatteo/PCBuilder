package com.caruso.pcbuilderproject.componentsclasses

import android.util.Log
import com.caruso.pcbuilderproject.R
import org.json.JSONObject

data class Motherboard(
    override var id: Int,               // id = 1
    override var brand: String,         // brand = "MSI"
    override var name: String,          // name = "MPG Z790 CARBON"
    override var price: Float,          // price = 350.72f
    override var defaultImagePainterId: Int, // imagePainter = painterResource(id = R.drawable.motherboard_placeholder)
    override val imagePainterLink: String?,

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
) : Component(id, brand, name, price, defaultImagePainterId, imagePainterLink) {
    override fun toInt(): Int {
        return ComponentType.MOTHERBOARD
    }

    override fun toString(): String {
        return "Motherboard: " + toStringDropType()
    }

    override fun toStringDropType(): String {
        return "$brand $name"
    }

    companion object {
        fun toMotherboard(jsonObject: JSONObject): Motherboard {
            if (jsonObject.getString("ImageURL") == "null") {
                Log.e("To Motherboard", "Image URL is null.")
            }

            return Motherboard(
                id = jsonObject.getString("IdMotherboard")
                    .toInt(),
                brand = jsonObject.getString("Brand"),
                name = jsonObject.getString("Name"),
                price = jsonObject.getString("Price")
                    .toFloat(),
                defaultImagePainterId = R.drawable.motherboard_placeholder,
                imagePainterLink = if (jsonObject.getString("ImageURL") != "null")
                    "/Images/Motherboard/" + jsonObject.getString("ImageURL")
                else
                    null,
                socket = jsonObject.getString("Socket"),
                chipset = jsonObject.getString("Chipset"),
                formFactor = jsonObject.getString("FormFactor"),
                memoryType = jsonObject.getString("RAMType"),
                memorySlotNumber = jsonObject.getString("NumberOfRAMSlots")
                    .toInt(),
                maxEthernetSpeed = jsonObject.getString("MaxEthernetSpeed")
                    .toFloat(),
                wifiIncluded = jsonObject.getString("WifiIncluded") == "1",
                bluetoothIncluded = jsonObject.getString("BluetoothIncluded") == "1",
                pcie_x16_5_slotNumber = jsonObject.getString(
                    "PCIe_x16_5"
                ).toInt(),
                pcie_x16_4_slotNumber = jsonObject.getString(
                    "PCIe_x16_4"
                ).toInt(),
                pcie_x8_4_slotNumber = jsonObject.getString("PCIe_x8")
                    .toInt(),
                pcie_x4_4_slotNumber = jsonObject.getString("PCIe_x4")
                    .toInt(),
                pcie_x1_4_slotNumber = jsonObject.getString("PCIe_x1")
                    .toInt(),
                m2_nvme_5_slotNumber = jsonObject.getString("M2_5")
                    .toInt(),
                m2_nvme_4_slotNumber = jsonObject.getString("M2_4")
                    .toInt(),
                sata_portNumber = jsonObject.getString("NumberOfSATA")
                    .toInt(),
                usb_a_2_headerNumber = jsonObject.getString("USB_2")
                    .toInt(),
                usb_a_32_gen1_headerNumber = jsonObject.getString(
                    "USB_32_1"
                ).toInt(),
                usb_c_32_gen2_headerNumber = jsonObject.getString(
                    "USB_32_2"
                ).toInt()
            )
        }
    }
}