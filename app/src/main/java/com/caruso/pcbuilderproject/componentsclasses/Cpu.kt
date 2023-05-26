package com.caruso.pcbuilderproject.componentsclasses

import android.util.Log
import com.caruso.pcbuilderproject.R
import org.json.JSONObject

class Cpu(
    override val id: Int,               // id = 1
    override val brand: String,         // brand = "AMD"
    override val name: String,          // name = "7800X3D"
    override val price: Float,          // price = 520.99f
    override val defaultImagePainterId: Int, // imagePainter = painterResource(id = R.drawable.cpu_placeholder)
    override val imagePainterLink: String?,

    // Specific
    val series: String,                 // series = "Ryzen 7"
    val coreNumber: Int,                // coreNumber = 8
    val baseClockSpeed: Float,          // baseClockSpeed = 3.4f [GHz],
    val powerConsumption: Int,          // powerConsumption = 125 [W]
    val architecture: String,           // architecture = "Zen 4"
    val socket: String,                 // socket = "AM5"
    val integratedGraphics: Boolean,    // integratedGraphics = true
    val fanIncluded: Boolean,           // fanIncluded = false
) : Component(id, brand, name, price, defaultImagePainterId, imagePainterLink) {
    override fun toInt(): Int {
        return ComponentType.CPU
    }

    override fun toString(): String {
        return "CPU: " + toStringDropType()
    }

    override fun toStringDropType(): String {
        return "$brand $series $name"
    }

    companion object {
        fun toCPU(jsonObject: JSONObject): Cpu {
            if (jsonObject.getString("ImageURL") == "null") {
                Log.e("To CPU", "Image URL is null.")
            }

            return Cpu(
                id = jsonObject.getString("IdCPU")
                    .toInt(),
                brand = jsonObject.getString("Brand"),
                series = jsonObject.getString("Series"),
                name = jsonObject.getString("Name"),
                price = jsonObject.getString("Price")
                    .toFloat(),
                defaultImagePainterId = R.drawable.cpu_placeholder,
                imagePainterLink = if (jsonObject.getString("ImageURL") != "null")
                    "/Images/CPU/" + jsonObject.getString("ImageURL")
                else
                    null,
                coreNumber = jsonObject.getString("NumberOfCores")
                    .toInt(),
                baseClockSpeed = jsonObject.getString("ClockSpeed")
                    .toFloat(),
                powerConsumption = jsonObject.getString("TDP")
                    .toInt(),
                architecture = jsonObject.getString("Architecture"),
                socket = jsonObject.getString("Socket"),
                integratedGraphics = jsonObject.getString("IntegratedGraphics") == "1",
                fanIncluded = jsonObject.getString("CoolerIncluded") == "1"
            )
        }
    }
}