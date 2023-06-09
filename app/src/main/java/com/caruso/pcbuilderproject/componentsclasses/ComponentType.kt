package com.caruso.pcbuilderproject.componentsclasses

import android.content.Context
import com.caruso.pcbuilderproject.R.string.*

abstract class ComponentType {
    companion object {
        const val CPU = 0
        const val MOTHERBOARD = 1
        const val RAM = 2
        const val GPU = 3
        const val STORAGE = 4
        const val PSU = 5
        const val NOT_VALID = -1

        fun toString(componentType: Int, context: Context?): String {
            if (context != null) {
                return when (componentType) {
                    CPU -> context.getString(cpu_Text)
                    MOTHERBOARD -> context.getString(motherboard_Text)
                    RAM -> context.getString(ram_Text)
                    GPU -> context.getString(gpu_Text)
                    STORAGE -> context.getString(storage_Text)
                    PSU -> context.getString(psu_Text)
                    else -> "Not valid"
                }
            } else {
                return when (componentType) {
                    CPU -> "CPU"
                    MOTHERBOARD -> "Motherboard"
                    RAM -> "RAM"
                    GPU -> "GPU"
                    STORAGE -> "Storage"
                    PSU -> "PSU"
                    else -> "Not valid"
                }
            }
        }

        fun toStringPlural(componentType: Int, context: Context?): String {
            if (context != null) {
                return when (componentType) {
                    CPU -> context.getString(cpus_Text)
                    MOTHERBOARD -> context.getString(motherboards_Text)
                    RAM -> context.getString(rams_Text)
                    GPU -> context.getString(gpus_Text)
                    STORAGE -> context.getString(storages_Text)
                    PSU -> context.getString(psus_Text)
                    else -> "Not valid"
                }
            } else {
                return when (componentType) {
                    CPU -> "CPUs"
                    MOTHERBOARD -> "Motherboards"
                    RAM -> "RAMs"
                    GPU -> "GPUs"
                    STORAGE -> "Storages"
                    PSU -> "PSUs"
                    else -> "Not valid"
                }
            }
        }

        fun isValid(componentType: Int): Boolean {
            return (componentType == 0) || (componentType == 1) ||
                    (componentType == 2) || (componentType == 3) ||
                    (componentType == 4) || (componentType == 5)
        }
    }
}