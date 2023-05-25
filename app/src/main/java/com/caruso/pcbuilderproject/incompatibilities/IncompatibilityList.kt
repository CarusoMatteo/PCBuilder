package com.caruso.pcbuilderproject.incompatibilities

import android.content.Context
import android.util.Log
import com.caruso.pcbuilderproject.R.string.*
import com.caruso.pcbuilderproject.utilities.GlobalData.Companion.loggedInUser

abstract class IncompatibilityList {
    companion object {

        // region Incompatibilities declaration
        var wrongSocket = Incompatibility()
        var wrongMemoryType = Incompatibility()
        var tooManyMemorySticks = Incompatibility()
        var tooLittlePowerfulPSU = Incompatibility()
        var storageNotCompatible = Incompatibility()
        // endregion

        // When an incompatibility is added it must be initialized here,
        // the relative condition for activation
        // and the localized name (the string resource) and description must be put here
        fun checkForIncompatibilities(context: Context) {

            Log.d("Check Incompatibilities", "----------------------------")

            if (loggedInUser != null) {

                // region Test for Wrong socket

                // If both a CPU and a Motherboard have been selected
                if (loggedInUser?.cpuSelected != null && loggedInUser?.motherboardSelected != null) {

                    if (loggedInUser?.cpuSelected?.socket != loggedInUser?.motherboardSelected?.socket) {
                        wrongSocket.name = context.getString(incompatible_cpu)
                        wrongSocket.description =
                            buildString {
                                append(context.getString(incompatible_cpu_description1))
                                append(loggedInUser?.cpuSelected?.socket)
                                append(context.getString(incompatible_cpu_description2))
                                append(loggedInUser?.motherboardSelected?.socket)
                                append((context.getString(incompatible_cpu_description3)))
                            }

                        wrongSocket.active = true
                    } else {
                        Log.d("Wrong Socket Check", "The sockets are the same.")
                        wrongSocket.active = false
                    }
                } else {
                    wrongSocket.active = false
                }

                // endregion

                // region Test for Wrong memory type

                if (loggedInUser?.motherboardSelected != null && loggedInUser?.ramSelected != null) {
                    if (loggedInUser?.ramSelected?.memoryType != loggedInUser?.motherboardSelected?.memoryType) {
                        wrongMemoryType.name = context.getString(incompatible_ram)
                        wrongMemoryType.description =
                            buildString {
                                append(context.getString(incompatible_ram_description1))
                                append(loggedInUser?.motherboardSelected?.memoryType)
                                append(context.getString(incompatible_ram_description2))
                                append(loggedInUser?.ramSelected?.memoryType)
                                append(context.getString(incompatible_ram_description3))
                            }

                        wrongMemoryType.active = true
                    } else {
                        Log.d("Wrong Memory Type Check", "The memory types are the same.")
                        wrongMemoryType.active = false
                    }
                } else {
                    wrongMemoryType.active = false
                }

                // endregion

                // region Test for Too many memory sticks

                if (loggedInUser?.motherboardSelected != null && loggedInUser?.ramSelected != null) {
                    if (loggedInUser?.motherboardSelected?.memorySlotNumber!! < loggedInUser?.ramSelected?.numberOfSticks!!) {
                        tooManyMemorySticks.name = context.getString(too_many_ram_sticks)
                        tooManyMemorySticks.description =
                            buildString {
                                append(context.getString(too_many_ram_sticks_description1))
                                append(loggedInUser?.motherboardSelected?.memorySlotNumber)
                                append(context.getString(too_many_ram_sticks_description2))
                                append(loggedInUser?.ramSelected?.numberOfSticks)
                                append(context.getString(too_many_ram_sticks_description3))
                            }

                        tooManyMemorySticks.active = true
                    } else {
                        Log.d("Too Many Memory Sticks Check", "There are enough memory slots.")
                        tooManyMemorySticks.active = false
                    }
                } else {
                    tooManyMemorySticks.active = false
                }

                // endregion

                // region Test for Too little powerful PSU

                if (loggedInUser?.psuSelected != null) {
                    if (loggedInUser?.getTotalWattage()!! > loggedInUser?.psuSelected!!.wattage) {
                        tooLittlePowerfulPSU.name = context.getString(too_little_powerful_PSU)
                        tooLittlePowerfulPSU.description =
                            buildString {
                                append(context.getString(too_little_powerful_PSU_description1))
                                append(loggedInUser?.getTotalWattage())
                                append(context.getString(too_little_powerful_PSU_description2))
                            }
                        tooLittlePowerfulPSU.active = true

                    } else {
                        Log.d("Too Little Powerful PSU Check", "The PSU is powerful enough.")
                        tooLittlePowerfulPSU.active = false
                    }
                } else {
                    tooLittlePowerfulPSU.active = false
                }

                // endregion

                // region Test for Storage not compatible

                if (loggedInUser?.motherboardSelected != null && loggedInUser?.storageSelected != null) {
                    when (loggedInUser?.storageSelected!!.storageType) {
                        "NVMe M.2 4.0" -> {
                            if (loggedInUser?.motherboardSelected!!.m2_nvme_4_slotNumber == 0) {
                                storageNotCompatible.name =
                                    context.getString(storage_not_compatible)
                                storageNotCompatible.description =
                                    buildString {
                                        append(context.getString(storage_not_compatible_description1))
                                        append(loggedInUser?.storageSelected!!.storageType)
                                        append(context.getString(storage_not_compatible_description2))
                                    }

                                storageNotCompatible.active = true
                            } else {
                                Log.d("Storage Not Compatible Check", "The storage is compatible.")
                                storageNotCompatible.active = false
                            }
                        }

                        "NVMe M.2 5.0" -> {
                            if (loggedInUser?.motherboardSelected!!.m2_nvme_5_slotNumber == 0) {
                                storageNotCompatible.name =
                                    context.getString(storage_not_compatible)
                                storageNotCompatible.description =
                                    buildString {
                                        append(context.getString(storage_not_compatible_description1))
                                        append(loggedInUser?.storageSelected!!.storageType)
                                        append(context.getString(storage_not_compatible_description2))
                                    }

                                storageNotCompatible.active = true
                            } else {
                                Log.d("Storage Not Compatible Check", "The storage is compatible.")
                                storageNotCompatible.active = false
                            }
                        }

                        "SATA" -> {
                            if (loggedInUser?.motherboardSelected!!.sata_portNumber == 0) {
                                storageNotCompatible.name =
                                    context.getString(storage_not_compatible)
                                storageNotCompatible.description =
                                    buildString {
                                        append(context.getString(storage_not_compatible_description1))
                                        append(loggedInUser?.storageSelected!!.storageType)
                                        append(context.getString(storage_not_compatible_description2))
                                    }

                                storageNotCompatible.active = true
                            } else {
                                Log.d("Storage Not Compatible Check", "The storage is compatible.")
                                storageNotCompatible.active = false
                            }
                        }

                        else -> {
                            Log.e(
                                "Storage Not Compatible Check",
                                "A storage type was not accounted for."
                            )
                            storageNotCompatible.active = false
                        }
                    }
                } else {
                    storageNotCompatible.active = false
                }

                // endregion

            }

            Log.d("Check Incompatibilities", "----------------------------")
        }
    }
}