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

        // endregion

        // TODO: When an incompatibility is added it must be initialized here,
        //  the relative condition for activation
        //  and the localized (String Resource) name and description must be put here
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
                        // Log.d("Wrong Socket Check", "The sockets are the same.")
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

            }

            Log.d("Check Incompatibilities", "----------------------------")
        }
    }
}
