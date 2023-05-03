package com.caruso.pcbuilderproject.componentsclasses

class ComponentsList {
    companion object {
        var cpus: MutableList<CPU> = mutableListOf()
        var motherboards: MutableList<Motherboard> = mutableListOf()
        var rams: MutableList<RAM> = mutableListOf()
        var gpus: MutableList<GPU> = mutableListOf()
        var storages: MutableList<Storage> = mutableListOf()
        var psus: MutableList<PSU> = mutableListOf()

        fun clearCPUs() {
            cpus = mutableListOf()
        }

        fun clearMotherboards() {
            motherboards = mutableListOf()
        }

        fun clearRAMs() {
            rams = mutableListOf()
        }

        fun clearGPUs() {
            gpus = mutableListOf()
        }

        fun clearStorages() {
            storages = mutableListOf()
        }

        fun clearPSUs() {
            psus = mutableListOf()
        }

    }
}