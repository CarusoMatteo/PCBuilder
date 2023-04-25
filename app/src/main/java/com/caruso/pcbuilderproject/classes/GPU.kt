package com.caruso.pcbuilderproject.classes

import androidx.compose.ui.graphics.painter.Painter

class GPU(
    override val id: Int,               // id = 1
    override val brand: String,         // brand = "Asus"
    override val name: String,          // name = "TUF"
    override val price: Float,          // price = 1600f
    override val imagePainter: Painter, // imagePainter = painterResource(id = R.drawable.gpu_placeholder)

    // Specific
    val chipsetBrand: String,           // chipsetBrand = "NVIDIA"
    val chipset: String,                // chipset = "GeForce RTX 4080"
    val vRamSize: Int,                  // vRamSize = 16 [GB]
    val clockSpeed: Int,                // clockSpeed = 2205 [GHz]
    val length: Int,                    // length = 348 [mm]
    val size: Int,                      // size = 4 [slots]
    val powerConsumption: Int,          // powerConsumption = 320 [W]
    val hdmiPortNumber: Int,            // hdmiPortNumber = 2
    val displayPortNumber: Int,         // displayPortNumber = 3

    val _12VHPWR_headerNumber: Int,     // _12VoltHighPower_headerNumber = 1
    val _8Pin_headerNumber: Int,        // _8Pin_headerNumber = 0
    val _6Pin_headerNumber: Int         // _6Pin_headerNumber = 0
) : Product(id, brand, name, price, imagePainter)