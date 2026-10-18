package io.github.sadeqit.weatherino.platform

import kotlinx.browser.window

actual fun openLink(url: String) {
    window.open(url, "_blank")
}