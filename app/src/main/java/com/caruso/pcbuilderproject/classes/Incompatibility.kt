package com.caruso.pcbuilderproject.classes

data class Incompatibility(
    val name: String,
    val description: String,
    var active: Boolean = false,
)
