package com.caruso.pcbuilderproject.componentsclasses

data class RAM(
    override val id: Int,               // id = 1
    override val brand: String,         // brand = "Corsair"
    override val name: String,          // name = "Vengeance RGB"
    override val price: Float,          // price = 520.99f
    override val imagePainterId: Int, // imagePainter = painterResource(id = R.drawable.ram_placeholder)

    // Specific
    val memoryType: String,             // memoryType = "DDR5"
    val memorySpeed: Int,               // memorySpeed = 6000 [MT/s]
    val totalSize: Int,                 // totalSize = 32 [GB]
    val numberOfSticks: Int,            // numberOfSticks = 2
) : Component(id, brand, name, price, imagePainterId){
    override fun toInt(): Int {
        return ComponentType.RAM
    }
}