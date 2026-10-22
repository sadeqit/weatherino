package io.github.sadeqit.weatherino.presentation.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.runtime.Composable

private val WeatherinoColorScheme = darkColorScheme(
    primary = WeatherBlue,
    onPrimary = TextPrimary,
    background = BackgroundDark,
    onBackground = TextPrimary,
    surface = GlassWhite,
    onSurface = TextPrimary
)

@Composable
fun WeatherinoTheme(
    content: @Composable () -> Unit
) {
    MaterialTheme(
        colorScheme = WeatherinoColorScheme,
        typography = weatherinoTypography(),
        content = content
    )
}