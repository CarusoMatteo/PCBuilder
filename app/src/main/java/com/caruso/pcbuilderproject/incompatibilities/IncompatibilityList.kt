package com.caruso.pcbuilderproject.incompatibilities

import android.content.Context
import android.util.Log
import com.caruso.pcbuilderproject.R.string.*
import com.caruso.pcbuilderproject.utilities.GlobalData.Companion.loggedInUser

abstract class IncompatibilityList {
    companion object {
        var wrongSocket =
            Incompatibility(
                "Incompatible CPU",
                "This CPU won't fit in this motherboard's socket.",
                false
            )

        fun checkForIncompatibilities(context: Context) {
            // Test for Wrong socket

            // If both a CPU and a Motherboard have been selected
            if (loggedInUser.cpuSelected != null && loggedInUser.motherboardSelected != null) {

                if (loggedInUser.cpuSelected?.socket != loggedInUser.motherboardSelected?.socket) {
                    wrongSocket.name = context.getString(incompatible_cpu)
                    wrongSocket.description =
                        context.getString(incompatible_cpu_description1) +
                                loggedInUser.cpuSelected?.socket +
                                context.getString(
                                    incompatible_cpu_description2
                                ) +
                                loggedInUser.motherboardSelected?.socket + (context.getString(
                            incompatible_cpu_description3
                        ))

                    wrongSocket.active = true
                } else {Log.d("WrongSocketCheck", "The sockets are the same.")
                    wrongSocket.active = false
                }
            } else {
                wrongSocket.active = false
            }
        }
    }
}
