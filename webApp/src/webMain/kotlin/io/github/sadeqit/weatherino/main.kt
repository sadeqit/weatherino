package io.github.sadeqit.weatherino

import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.window.ComposeViewport
import io.github.sadeqit.weatherino.core.common.initKoin

@OptIn(ExperimentalComposeUiApi::class)
fun main() {
    initKoin()

    ComposeViewport {
        App(
            onExit = {}
        )
    }
}