package com.caruso.pcbuilderproject.componentsclasses

abstract class ComponentType {
    companion object {
        const val CPU = 0
        const val MOTHERBOARD = 1
        const val RAM = 2
        const val GPU = 3
        const val STORAGE = 4
        const val PSU = 5
    }
}