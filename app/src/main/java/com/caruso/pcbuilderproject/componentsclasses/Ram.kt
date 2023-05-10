package com.caruso.pcbuilderproject.componentsclasses

import android.util.Log
import com.caruso.pcbuilderproject.R
import org.json.JSONObject

data class Ram(
    override val id: Int,               // id = 1
    override val brand: String,         // brand = "Corsair"
    override val name: String,          // name = "Vengeance RGB"
    override val price: Float,          // price = 520.99f
    override val defaultImagePainterId: Int, // imagePainter = painterResource(id = R.drawable.ram_placeholder)
    override val imagePainterLink: String?,

    // Specific
    val memoryType: String,             // memoryType = "DDR5"
    val memorySpeed: Int,               // memorySpeed = 6000 [MT/s]
    val totalSize: Int,                 // totalSize = 32 [GB]
    val numberOfSticks: Int,            // numberOfSticks = 2
) : Component(id, brand, name, price, defaultImagePainterId, imagePainterLink) {
    override fun toInt(): Int {
        return ComponentType.RAM
    }

    override fun toString(): String {
        return "RAM: $brand $name"
    }

    companion object {
        fun toRAM(jsonObject: JSONObject): Ram {
            if (jsonObject.getString("ImageURL") == "null") {
                Log.e("To RAM", "Image URL is null.")
            }

            return Ram(
                id = jsonObject.getString("IdRAM").toInt(),
                brand = jsonObject.getString("Brand"),
                name = jsonObject.getString("Name"),
                price = jsonObject.getString("Price")
                    .toFloat(),
                defaultImagePainterId = R.drawable.ram_placeholder,
                imagePainterLink = if (jsonObject.getString("ImageURL") != "null")
                    "/Images/RAM/" + jsonObject.getString("ImageURL")
                else
                    null,
                memoryType = jsonObject.getString("RAMType"),
                memorySpeed = jsonObject.getString("Speed")
                    .toInt(),
                totalSize = jsonObject.getString("TotalSize")
                    .toInt(),
                numberOfSticks = jsonObject.getString("NumberOfSticks")
                    .toInt(),
            )
        }
    }
}