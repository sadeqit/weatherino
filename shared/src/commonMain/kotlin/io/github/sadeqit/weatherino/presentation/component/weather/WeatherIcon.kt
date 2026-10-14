package io.github.sadeqit.weatherino.presentation.component.weather

import androidx.compose.foundation.Image
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import io.github.sadeqit.weatherino.resources.Res
import io.github.sadeqit.weatherino.resources.cloudy
import io.github.sadeqit.weatherino.resources.lightdrizzle
import io.github.sadeqit.weatherino.resources.night
import io.github.sadeqit.weatherino.resources.partlycloudy
import io.github.sadeqit.weatherino.resources.rainy
import io.github.sadeqit.weatherino.resources.rainywithsun
import io.github.sadeqit.weatherino.resources.sleet
import io.github.sadeqit.weatherino.resources.snow
import io.github.sadeqit.weatherino.resources.sunny
import io.github.sadeqit.weatherino.resources.thunderstorm
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.painterResource

@Composable
fun WeatherIcon(
    iconCode: String,
    contentDescription: String? = null,
    modifier: Modifier = Modifier
) {
    Image(
        painter = painterResource(iconResource(iconCode)),
        contentDescription = contentDescription,
        modifier = modifier
    )
}

private fun iconResource(iconCode: String): DrawableResource {
    return when (iconCode) {
        "01d" -> Res.drawable.sunny
        "01n" -> Res.drawable.night

        "02d", "02n" -> Res.drawable.partlycloudy

        "03d", "03n" -> Res.drawable.cloudy

        "04d", "04n" -> Res.drawable.cloudy

        "09d", "09n" -> Res.drawable.lightdrizzle

        "10d" -> Res.drawable.rainywithsun
        "10n" -> Res.drawable.rainy

        "11d", "11n" -> Res.drawable.thunderstorm

        "13d", "13n" -> Res.drawable.snow

        "50d", "50n" -> Res.drawable.sleet

        else -> Res.drawable.sunny
    }
}