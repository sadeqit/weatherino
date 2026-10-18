package io.github.sadeqit.weatherino.platform

import java.awt.Desktop
import java.net.URI

actual fun rateApp() {
    Desktop.getDesktop().browse(
        URI("https://cafebazaar.ir/app/io.github.sadeqit.weatherino")
    )
}