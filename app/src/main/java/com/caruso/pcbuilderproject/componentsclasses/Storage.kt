package com.caruso.pcbuilderproject.componentsclasses

import android.util.Log
import com.caruso.pcbuilderproject.R
import org.json.JSONObject

data class Storage(
    override val id: Int,               // id = 1
    override val brand: String,         // brand = "Samsung"
    override val name: String,          // name = "980 Pro"
    override val price: Float,          // price = 100f
    override val defaultImagePainterId: Int, // imagePainter = painterResource(id = R.drawable.storage_placeholder)
    override val imagePainterLink: String?,

    // Specific
    val storageType: String,            // storageType = "NVMe M.2 5.0"
    val storageSize: Int,               // storageSize = 2000 [GB]
) : Component(id, brand, name, price, defaultImagePainterId, imagePainterLink) {
    override fun toInt(): Int {
        return ComponentType.STORAGE
    }

    override fun toString(): String {
        return "Storage: $brand $name"
    }

    companion object {
        fun toStorage(jsonObject: JSONObject): Storage {
            if (jsonObject.getString("ImageURL") == "null") {
                Log.e("To Storage", "Image URL is null.")
            }

            return Storage(
                id = jsonObject.getString("IdStorage")
                    .toInt(),
                brand = jsonObject.getString("Brand"),
                name = jsonObject.getString("Name"),
                price = jsonObject.getString("Price")
                    .toFloat(),
                defaultImagePainterId = R.drawable.storage_placeholder,
                imagePainterLink = if (jsonObject.getString("ImageURL") != "null")
                    "/Images/Storage/" + jsonObject.getString("ImageURL")
                else
                    null,
                storageType = jsonObject.getString("Type"),
                storageSize = jsonObject.getString("Size")
                    .toInt(),
            )
        }
    }
}