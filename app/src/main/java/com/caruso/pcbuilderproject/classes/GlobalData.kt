package com.caruso.pcbuilderproject.classes

import android.content.*

abstract class GlobalData {
    companion object {
        const val dynamicColorActive = false

        // Contains the Username of the user currently logged-in, otherwise is null
        var loggedInUsername: String? = null

        const val ngrokServerLinkPrefix = "https://"
        var ngrokServerLink = "ad69-93-40-210-133"
        const val ngrokServerLinkSuffix = ".ngrok-free.app"

        fun floatToStringChecker(
            number: Float,
            currency: Char = '€',
            decimalPoint: Char = '.'
        ): String {
            val numberString = number.toString()

            val numberStringArray = numberString.split('.')

            val wholePart = numberStringArray[0]
            var decimalPart = numberStringArray[1]

            if (decimalPart.isEmpty()) {
                decimalPart = "00"
            } else if (decimalPart.length == 1) {
                decimalPart += "0"
            } else if (decimalPart.length > 2) {
                decimalPart = decimalPart.dropLast(decimalPart.length - 2)
            }

            return "$currency $wholePart$decimalPoint$decimalPart"
        }

        fun copyToClipboard(
            context: Context,
            text: String
        ) {
            val clipboardManager =
                context.getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
            val clip = ClipData.newPlainText("Server link", text)
            clipboardManager.setPrimaryClip(clip)
        }
    }
}