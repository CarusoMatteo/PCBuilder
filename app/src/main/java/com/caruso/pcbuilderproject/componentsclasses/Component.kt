package com.caruso.pcbuilderproject.componentsclasses

abstract class Component(
    open val id: Int,
    open val brand: String,
    open val name: String,
    open val price: Float,
    open val imagePainterId: Int
) {
    open fun toInt(): Int {
        return ComponentType.NOT_VALID
    }
}