package io.github.sadeqit.weatherino.presentation.component.background

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import io.github.sadeqit.weatherino.presentation.theme.WeatherBlue
import io.github.sadeqit.weatherino.presentation.theme.WeatherBlueDark

@Composable
fun WeatherBackground(
    content: @Composable () -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                Brush.verticalGradient(
                    colors = listOf(
                        WeatherBlue,
                        WeatherBlueDark

                    )
                )
            )
    ) {
        content()
    }
}