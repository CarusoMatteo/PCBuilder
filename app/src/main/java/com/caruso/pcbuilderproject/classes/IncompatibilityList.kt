package com.caruso.pcbuilderproject.classes

class IncompatibilityList {
    companion object {
        val wrongSocket =
            Incompatibility(
                "Incompatible CPU",
                "This CPU won't fit in this motherboard's socket.",
                true
            )
    }
}
