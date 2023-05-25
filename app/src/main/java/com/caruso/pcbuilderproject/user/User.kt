package com.caruso.pcbuilderproject.user

import android.util.Log
import com.caruso.pcbuilderproject.componentsclasses.*

data class User(
    var username: String,
    var cpuSelected: Cpu? = null,
    var motherboardSelected: Motherboard? = null,
    var ramSelected: Ram? = null,
    var gpuSelected: Gpu? = null,
    var storageSelected: Storage? = null,
    var psuSelected: Psu? = null,
) {
    override fun toString(): String {
        return username
    }

    fun toStringComplete(): String {
        return buildString {
            append("$username:\n")
            append("$cpuSelected;\n")
            append("$motherboardSelected;\n")
            append("$ramSelected;\n")
            append("$gpuSelected;\n")
            append("$storageSelected;\n")
            append(psuSelected)
        }
    }

    fun getTotalWattage(): Int {
        Log.d("Get Total Wattage", "----------------------------")
        Log.d(
            "Get Total Wattage",
            "Attempting to calculate total wattage for user $this."
        )

        var wattage = 0

        if (cpuSelected != null) {
            wattage += cpuSelected!!.powerConsumption
            Log.d(
                "Get Total Wattage",
                "$cpuSelected needs ${cpuSelected!!.powerConsumption} W."
            )
        }
        if (gpuSelected != null) {
            wattage += gpuSelected!!.powerConsumption
            Log.d(
                "Get Total Wattage",
                "$gpuSelected needs ${gpuSelected!!.powerConsumption} W."
            )
        }

        Log.d(
            "Get Total Wattage",
            "Total wattage is $wattage W."
        )
        Log.d("Get Total Wattage", "----------------------------")

        return wattage
    }


    fun getTotalPrice(): Float {
        var total = 0f

        if (cpuSelected != null)
            total += cpuSelected!!.price
        if (motherboardSelected != null)
            total += motherboardSelected!!.price
        if (ramSelected != null)
            total += ramSelected!!.price
        if (gpuSelected != null)
            total += gpuSelected!!.price
        if (storageSelected != null)
            total += storageSelected!!.price
        if (psuSelected != null)
            total += psuSelected!!.price

        return total
    }
}