package com.caruso.pcbuilderproject.componentsclasses

data class Storage(
    override val id: Int,               // id = 1
    override val brand: String,         // brand = "Samsung"
    override val name: String,          // name = "980 Pro"
    override val price: Float,          // price = 100f
    override val imagePainterId: Int, // imagePainter = painterResource(id = R.drawable.storage_placeholder)

    // Specific
    val storageType: String,            // storageType = "NVMe M.2 5.0"
    val storageSize: Int,               // storageSize = 2000
) : Component(id, brand, name, price, imagePainterId){
    override fun toInt(): Int {
        return ComponentType.STORAGE
    }
}