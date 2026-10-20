@file:Suppress("DEPRECATION")  // این خط رو می‌تونی حذف کنی

package io.github.sadeqit.weatherino

import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import org.jetbrains.compose.resources.painterResource
import io.github.sadeqit.weatherino.core.common.initKoin
import io.github.sadeqit.weatherino.resources.Res
import io.github.sadeqit.weatherino.resources.icon

fun main() = application {
    initKoin()

    Window(
        onCloseRequest = ::exitApplication,
        title = "Weatherino",
        icon = painterResource(Res.drawable.icon)
    ) {
        App(
            onExit = ::exitApplication
        )
    }
}