package com.caruso.pcbuilderproject.componentsclasses

abstract class Component(
    open val id: Int,
    open val brand: String,
    open val name: String,
    open val price: Float,
    open val defaultImagePainterId: Int,
    open val imagePainterLink: String?
) {
    open fun toInt(): Int {
        return ComponentType.NOT_VALID
    }

    override fun toString(): String {
        return "Component: " + toStringDropType()
    }

    open fun toStringDropType(): String {
        return "$brand $name"
    }
}