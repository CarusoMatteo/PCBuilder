package com.caruso.pcbuilderproject.classes

import androidx.compose.ui.graphics.painter.Painter

class CPU(
        override var id: Int,
        override var brand: String,
        override var name: String,
        override var price: Float,
        override var imagePainter: Painter,

        // Specific
        var series: String,
        var coreNumber: Int,
        var baseClockSpeed: Float, // In GHz
        var powerConsumption: Int, // In W
        var architecture: String,
        var socket: String,
        var integratedGraphics: Boolean,
        var fanIncluded: Boolean,
) : Product(id, brand, name, price, imagePainter)