package com.caruso.pcbuilderproject.classes

import com.caruso.pcbuilderproject.classes.GlobalData.Companion.loggedInUser

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
