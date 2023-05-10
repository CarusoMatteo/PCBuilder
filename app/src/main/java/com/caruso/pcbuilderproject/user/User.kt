package com.caruso.pcbuilderproject.user

import com.caruso.pcbuilderproject.componentsclasses.*

data class User(
    var username: String? = null,
    var cpuSelected: Cpu? = null,
    var motherboardSelected: Motherboard? = null,
    var ramSelected: Ram? = null,
    var gpuSelected: Gpu? = null,
    var storageSelected: Storage? = null,
    var psuSelected: Psu? = null,
) {
    override fun toString(): String {
        return "$username"
    }

    fun toStringComplete(): String {
        return "$username:\n" +
                "$cpuSelected;\n" +
                "$motherboardSelected;\n" +
                "$ramSelected;\n" +
                "$gpuSelected;\n" +
                "$storageSelected;\n" +
                "$psuSelected"
    }
}