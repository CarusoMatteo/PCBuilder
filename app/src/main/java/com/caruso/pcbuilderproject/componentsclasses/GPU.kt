package com.caruso.pcbuilderproject.componentsclasses

data class GPU(
    override val id: Int,               // id = 1
    override val brand: String,         // brand = "Asus"
    override val name: String,          // name = "TUF"
    override val price: Float,          // price = 1600f
    override val imagePainterId: Int, // imagePainter = painterResource(id = R.drawable.gpu_placeholder)

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
) : Component(id, brand, name, price, imagePainterId) {
    override fun toInt(): Int {
        return ComponentType.GPU
    }
}