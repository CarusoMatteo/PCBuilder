package com.caruso.pcbuilderproject.classes

data class Filter(
    val name: String,
    val value: String,
    var component: String,
    var active: Boolean = false,
    var sql: String = ""
) {
    override fun toString(): String {
        return "{Name: \"${this.name}\", Value: \"${this.value}\"}"
    }
}