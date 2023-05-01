package com.caruso.pcbuilderproject.incompatibilities

import com.caruso.pcbuilderproject.utilities.GlobalData.Companion.loggedInUser

abstract class IncompatibilityList {
    companion object {
        val wrongSocket =
            Incompatibility(
                "Incompatible CPU",
                "This CPU won't fit in this motherboard's socket.",
                false
            )

        fun checkForIncompatibilities() {
            // Wrong socket
            if (loggedInUser.cpuSelected != null && loggedInUser.motherboardSelected != null)
                wrongSocket.active =
                    loggedInUser.cpuSelected?.socket != loggedInUser.motherboardSelected?.socket
            else
                wrongSocket.active = false
        }
    }
}
