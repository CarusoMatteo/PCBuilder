package com.caruso.pcbuilderproject.filters

import com.caruso.pcbuilderproject.componentsclasses.ComponentType

data class Filter(
    val name: String = "",
    val value: String = "",
    var component: Int = ComponentType.CPU,
    var active: Boolean = false,
    var sql: String = ""
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