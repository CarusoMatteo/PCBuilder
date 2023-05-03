package com.caruso.pcbuilderproject.filters

import com.caruso.pcbuilderproject.componentsclasses.ComponentType

class Filter(
    val name: String = "",
    var nameNotLocalized: String = "",
    val value: String = "",
    var component: Int = ComponentType.CPU,
    var active: Boolean = false,
    var valueNotLocalized: String = value
) {
    override fun toString(): String {
        return "{Name: \"${
            if (this.name == "")
                "Empty"
            else
                this.name
        }\", Value: \"${
            if (this.value == "")
                "Empty"
            else
                this.value
        }\", Component: \"${
            ComponentType.toString(
                componentType = this.component,
                context = null
            )
        }\"}"
    }
}