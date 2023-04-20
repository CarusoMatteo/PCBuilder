package com.caruso.pcbuilderproject.classes

import androidx.compose.ui.graphics.painter.Painter

open class Product(
        open var id: Int,
        open var brand: String,
        open var name: String,
        open var price: Float,
        open var imagePainter: Painter
)