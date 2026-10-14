package io.github.sadeqit.weatherino

import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import io.github.sadeqit.weatherino.core.common.initKoin

fun main() = application {

    initKoin()

    Window(
        onCloseRequest = ::exitApplication,
        title = "Weatherino",
    ) {
        App()
    }
}