package com.caruso.pcbuilderproject.incompatibilities

data class Incompatibility(
    val name: String,
    val description: String,
    var active: Boolean = false,
) {
    override fun toString(): String {
        return "{Name: \"${this.name}\", Description: \"${this.description}\", Active: \"${this.active}\"}"
    }
}
