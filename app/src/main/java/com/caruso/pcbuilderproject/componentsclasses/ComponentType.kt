package com.caruso.pcbuilderproject.componentsclasses

abstract class ComponentType {
    companion object {
        const val CPU = 0
        const val MOTHERBOARD = 1
        const val RAM = 2
        const val GPU = 3
        const val STORAGE = 4
        const val PSU = 5

        fun toString(componentType: Int): String {
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
}