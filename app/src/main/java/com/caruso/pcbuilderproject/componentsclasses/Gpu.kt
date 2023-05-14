package com.caruso.pcbuilderproject.componentsclasses

import android.util.Log
import com.caruso.pcbuilderproject.R
import org.json.JSONObject

data class Gpu(
    override val id: Int,               // id = 1
    override val brand: String,         // brand = "Asus"
    override val name: String,          // name = "TUF"
    override val price: Float,          // price = 1600f
    override val defaultImagePainterId: Int, // imagePainter = painterResource(id = R.drawable.gpu_placeholder)
    override val imagePainterLink: String?,

    // Specific
    val chipsetBrand: String,           // chipsetBrand = "NVIDIA"
    val chipset: String,                // chipset = "GeForce RTX 4080"
    val vRamSize: Int,                  // vRamSize = 16 [GB]
    val clockSpeed: Float,              // clockSpeed = 2.205 [GHz]
    val length: Int,                    // length = 348 [mm]
    val size: Int,                      // size = 4 [slots]
    val powerConsumption: Int,          // powerConsumption = 320 [W]
    val hdmiPortNumber: Int,            // hdmiPortNumber = 2
    val displayPortNumber: Int,         // displayPortNumber = 3
) : Component(id, brand, name, price, defaultImagePainterId, imagePainterLink) {
    override fun toInt(): Int {
        return ComponentType.GPU
    }

    override fun toString(): String {
        return "GPU: $brand $name"
    }

    companion object {
        fun toGPU(jsonObject: JSONObject): Gpu {
            if (jsonObject.getString("ImageURL") == "null") {
                Log.e("To GPU", "Image URL is null.")
            }

            return Gpu(
                id = jsonObject.getString("IdGPU").toInt(),
                brand = jsonObject.getString("Brand"),
                name = jsonObject.getString("Name"),
                price = jsonObject.getString("Price")
                    .toFloat(),
                defaultImagePainterId = R.drawable.gpu_placeholder,
                imagePainterLink = if (jsonObject.getString("ImageURL") != "null")
                    "/Images/GPU/" + jsonObject.getString("ImageURL")
                else
                    null,
                chipsetBrand = jsonObject.getString("ChipsetBrand"),
                chipset = jsonObject.getString("Chipset"),
                vRamSize = jsonObject.getString("VRAMSize")
                    .toInt(),
                clockSpeed = jsonObject.getString("ClockSpeed")
                    .toFloat(),
                length = jsonObject.getString("Length")
                    .toInt(),
                size = jsonObject.getString("Size").toInt(),
                powerConsumption = jsonObject.getString("TDP")
                    .toInt(),
                hdmiPortNumber = jsonObject.getString("NumberOfHDMI")
                    .toInt(),
                displayPortNumber = jsonObject.getString("NumberOfDisplayPort").toInt()
            )
        }
    }
}