package com.caruso.pcbuilderproject.classes

import androidx.compose.ui.graphics.painter.Painter

class CPU(
    override val id: Int,               // id = 1
    override val brand: String,         // brand = "AMD"
    override val name: String,          // name = "7800X3D"
    override val price: Float,          // price = 520.99f
    override val imagePainter: Painter, // imagePainter = painterResource(id = R.drawable.cpu_placeholder)

    // Specific
    val series: String,                 // series = "Ryzen 7"
    val coreNumber: Int,                // coreNumber = 8
    val baseClockSpeed: Float,          // baseClockSpeed = 3.4f [GHz],
    val powerConsumption: Int,          // powerConsumption = 125 [W]
    val architecture: String,           // architecture = "Zen 4"
    val socket: String,                 // socket = "AM5"
    val integratedGraphics: Boolean,    // integratedGraphics = true
    val fanIncluded: Boolean,           // fanIncluded = false
) : Product(id, brand, name, price, imagePainter)