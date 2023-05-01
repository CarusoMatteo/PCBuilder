package com.caruso.pcbuilderproject.componentsclasses

import androidx.compose.ui.graphics.painter.Painter

open class Component(
    open val id: Int,
    open val brand: String,
    open val name: String,
    open val price: Float,
    open val imagePainter: Painter
)