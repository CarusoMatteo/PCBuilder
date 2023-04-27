package com.caruso.pcbuilderproject.classes

import androidx.compose.ui.graphics.painter.Painter

data class Storage(
    override val id: Int,               // id = 1
    override val brand: String,         // brand = "Samsung"
    override val name: String,          // name = "980 Pro"
    override val price: Float,          // price = 100f
    override val imagePainter: Painter, // imagePainter = painterResource(id = R.drawable.storage_placeholder)

    // Specific
    val storageType: String,            // storageType = "NVMe M.2 5.0"
    val storageSize: Int,               // storageSize = 2000
) : Component(id, brand, name, price, imagePainter)