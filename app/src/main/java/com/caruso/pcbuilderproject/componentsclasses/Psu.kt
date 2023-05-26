package com.caruso.pcbuilderproject.componentsclasses

import android.util.Log
import com.caruso.pcbuilderproject.R
import org.json.JSONObject

data class Psu(
    override val id: Int,               // id = 1
    override val brand: String,         // brand = "Corsair"
    override val name: String,          // name = "RM850x"
    override val price: Float,          // price = 120f
    override val defaultImagePainterId: Int, // imagePainter = painterResource(id = R.drawable.psu_placeholder)
    override val imagePainterLink: String?,

    // Specific
    val wattage: Int,                   // wattage = 850 [W]
    val formFactor: String,             // formFactor = "ATX"
    val length: Int,                    // length = 160 [mm]
) : Component(id, brand, name, price, defaultImagePainterId, imagePainterLink) {
    override fun toInt(): Int {
        return ComponentType.PSU
    }

    override fun toString(): String {
        return "PSU: " + toStringDropType()
    }

    override fun toStringDropType(): String {
        return "$brand $name $wattage W"
    }

    companion object {
        fun toPSU(jsonObject: JSONObject): Psu {
            if (jsonObject.getString("ImageURL") == "null") {
                Log.e("To PSU", "Image URL is null.")
            }

            return Psu(
                id = jsonObject.getString("IdPSU").toInt(),
                brand = jsonObject.getString("Brand"),
                name = jsonObject.getString("Name"),
                price = jsonObject.getString("Price")
                    .toFloat(),
                defaultImagePainterId = R.drawable.psu_placeholder,
                imagePainterLink = if (jsonObject.getString("ImageURL") != "null")
                    "/Images/PSU/" + jsonObject.getString("ImageURL")
                else
                    null,
                wattage = jsonObject.getString("Wattage")
                    .toInt(),
                formFactor = jsonObject.getString("FormFactor"),
                length = jsonObject.getString("Length")
                    .toInt()
            )
        }
    }
}