package com.caruso.pcbuilderproject.user

import com.caruso.pcbuilderproject.componentsclasses.*

data class User(
    var username: String? = null,
    var cpuSelected: CPU? = null,
    var motherboardSelected: Motherboard? = null,
    var ramSelected: RAM? = null,
    var gpuSelected: GPU? = null,
    var storageSelected: Storage? = null,
    var psuSelected: PSU? = null,
) {
    fun clear() {
        username = null
        cpuSelected = null
        motherboardSelected = null
        ramSelected = null
        gpuSelected = null
        storageSelected = null
        psuSelected = null
    }

    override fun toString(): String {
        return username.toString()
    }
}