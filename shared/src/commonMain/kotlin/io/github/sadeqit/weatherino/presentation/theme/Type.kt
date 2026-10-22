package io.github.sadeqit.weatherino.presentation.theme

import androidx.compose.material3.Typography

import androidx.compose.runtime.Composable
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import io.github.sadeqit.weatherino.resources.Res
import io.github.sadeqit.weatherino.resources.iranbold
import io.github.sadeqit.weatherino.resources.iranreg
import org.jetbrains.compose.resources.Font


@Composable
private fun weatherinoFontFamily() = FontFamily(
    Font(
        resource = Res.font.iranreg,
        weight = FontWeight.Normal
    ),
    Font(
        resource = Res.font.iranbold,
        weight = FontWeight.Bold
    )
)

@Composable
fun weatherinoTypography(): Typography {
    val fontFamily = weatherinoFontFamily()

    return Typography().run {
        copy(
            displayLarge = displayLarge.copy(fontFamily = fontFamily),
            displayMedium = displayMedium.copy(fontFamily = fontFamily),
            displaySmall = displaySmall.copy(fontFamily = fontFamily),

            headlineLarge = headlineLarge.copy(fontFamily = fontFamily),
            headlineMedium = headlineMedium.copy(fontFamily = fontFamily),
            headlineSmall = headlineSmall.copy(fontFamily = fontFamily),

            titleLarge = titleLarge.copy(fontFamily = fontFamily),
            titleMedium = titleMedium.copy(fontFamily = fontFamily),
            titleSmall = titleSmall.copy(fontFamily = fontFamily),

            bodyLarge = bodyLarge.copy(fontFamily = fontFamily),
            bodyMedium = bodyMedium.copy(fontFamily = fontFamily),
            bodySmall = bodySmall.copy(fontFamily = fontFamily),

            labelLarge = labelLarge.copy(fontFamily = fontFamily),
            labelMedium = labelMedium.copy(fontFamily = fontFamily),
            labelSmall = labelSmall.copy(fontFamily = fontFamily)
        )
    }
}