package io.github.sadeqit.weatherino.platform


import java.awt.Desktop
import java.net.URI

actual fun openLink(url: String) {
    try {
        if (Desktop.isDesktopSupported()) {
            Desktop.getDesktop().browse(URI(url))
        }
    } catch (_: Exception) {
        // Link cannot be opened
    }
}