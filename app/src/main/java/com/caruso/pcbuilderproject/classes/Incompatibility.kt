package com.caruso.pcbuilderproject.classes

data class Incompatibility(
    val name: String,
    val description: String,
    var active: Boolean = false,
) {
    override fun toString(): String {
        return "{Name: \"${this.name}\", Description: \"${this.description}\", Active: \"${this.active}\"}"
    }
}
