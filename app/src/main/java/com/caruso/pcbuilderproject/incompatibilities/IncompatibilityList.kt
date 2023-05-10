package com.caruso.pcbuilderproject.incompatibilities

import android.content.Context
import android.util.Log
import com.caruso.pcbuilderproject.R.string.*
import com.caruso.pcbuilderproject.utilities.GlobalData.Companion.loggedInUser

abstract class IncompatibilityList {
    companion object {

        // region Incompatibilities declaration
        var wrongSocket = Incompatibility()

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
                            context.getString(incompatible_cpu_description1) +
                                    loggedInUser?.cpuSelected?.socket +
                                    context.getString(
                                        incompatible_cpu_description2
                                    ) +
                                    loggedInUser?.motherboardSelected?.socket + (context.getString(
                                incompatible_cpu_description3
                            ))

                        wrongSocket.active = true
                    } else {
                        Log.d("Wrong Socket Check", "The sockets are the same.")
                        wrongSocket.active = false
                    }
                } else {
                    wrongSocket.active = false
                }

                // endregion

            }

            Log.d("Check Incompatibilities", "----------------------------")
        }
    }
}
