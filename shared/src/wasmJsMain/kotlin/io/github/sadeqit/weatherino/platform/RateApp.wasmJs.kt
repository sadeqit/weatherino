package io.github.sadeqit.weatherino.platform

import kotlinx.browser.window

actual fun rateApp() {
    window.open(
        "https://cafebazaar.ir/app/io.github.sadeqit.weatherino",
        "_blank"
    )
}