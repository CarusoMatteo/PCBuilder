package com.caruso.pcbuilderproject.classes

import androidx.compose.ui.graphics.painter.Painter

data class PSU(
    override val id: Int,               // id = 1
    override val brand: String,         // brand = "Corsair"
    override val name: String,          // name = "RM850x"
    override val price: Float,          // price = 120f
    override val imagePainter: Painter, // imagePainter = painterResource(id = R.drawable.psu_placeholder)

    // Specific
    val wattage: Int,                   // wattage = 850 [W]
    val formFactor: String,             // formFactor = "ATX"
    val length: Int,                    // length = 160 [mm]

    val ESPConnectorNumber: Int,        // ESPConnectorNumber = 3
    val PCIeConnectorNumber: Int,       // PCIeConnectorNumber = 4
    val SATAConnectorNumber: Int,       // SATAConnectorNumber = 14
) : Component(id, brand, name, price, imagePainter)