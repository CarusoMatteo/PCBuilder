package com.caruso.pcbuilderproject.componentsclasses

class ComponentsList {
    companion object {
        var cpus = mutableListOf<Cpu>()
        var motherboards = mutableListOf<Motherboard>()
        var rams = mutableListOf<Ram>()
        var gpus = mutableListOf<Gpu>()
        var storages = mutableListOf<Storage>()
        var psus = mutableListOf<Psu>()

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