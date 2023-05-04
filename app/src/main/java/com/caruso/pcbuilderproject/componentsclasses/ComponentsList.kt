package com.caruso.pcbuilderproject.componentsclasses

class ComponentsList {
    companion object {
        var cpus = mutableListOf<CPU>()
        var motherboards = mutableListOf<Motherboard>()
        var rams = mutableListOf<RAM>()
        var gpus = mutableListOf<GPU>()
        var storages = mutableListOf<Storage>()
        var psus = mutableListOf<PSU>()

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