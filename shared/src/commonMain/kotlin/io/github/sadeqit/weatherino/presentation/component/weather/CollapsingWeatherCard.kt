package io.github.sadeqit.weatherino.presentation.component.weather

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.spring
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.togetherWith
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import io.github.sadeqit.weatherino.domain.model.TemperatureUnit
import io.github.sadeqit.weatherino.domain.model.Weather
import io.github.sadeqit.weatherino.domain.util.WeatherUnitFormatter
import io.github.sadeqit.weatherino.presentation.component.glass.GlassCard
import io.github.sadeqit.weatherino.presentation.util.toPersianWeatherDescription

@Composable
fun CollapsingWeatherCard(
    weather: Weather,
    collapseProgress: Float,
    temperatureUnit: TemperatureUnit,
    modifier: Modifier = Modifier,
) {
    val temperature = WeatherUnitFormatter.temperature(
        celsius = weather.temperature,
        unit = temperatureUnit,
    )
    val temperatureValue = WeatherUnitFormatter.temperatureValue(
        celsius = weather.temperature,
        unit = temperatureUnit,
    )

    val weatherDescription = weather.weatherId.toPersianWeatherDescription()

    val iconSize by animateDpAsState(
        targetValue = 110.dp - (50.dp * collapseProgress),
        animationSpec = spring(),
        label = "weatherIconSize"
    )

    val cardPadding by animateDpAsState(
        targetValue = 10.dp - (2.dp * collapseProgress),
        animationSpec = spring(),
        label = "weatherCardPadding"
    )

    GlassCard(
        modifier = modifier.fillMaxWidth()
    ) {
        AnimatedContent(
            targetState = collapseProgress >= 0.5f,
            transitionSpec = {
                fadeIn() togetherWith fadeOut()
            },
            label = "weatherCardContent"
        ) { isCollapsed ->

            if (!isCollapsed) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(
                            start = cardPadding,
                            end = cardPadding,
                            top = 0.dp,
                            bottom = cardPadding
                        ),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    WeatherIcon(
                        iconCode = weather.weatherIcon,
                        contentDescription = weatherDescription,
                        modifier = Modifier.size(iconSize)
                    )

                    Spacer(modifier = Modifier.height(8.dp))

                    Text(
                        text = temperatureValue,
                        color = Color.White,
                        style = MaterialTheme.typography.displayMedium
                    )

                    Spacer(modifier = Modifier.height(4.dp))

                    Text(
                        text = weatherDescription,
                        color = Color.White,
                        style = MaterialTheme.typography.titleLarge
                    )

                    Spacer(modifier = Modifier.height(8.dp))

                    Text(
                        text = "\u2066$temperature\u2069 دمای هوا ",
                        color = Color.White,
                        style = MaterialTheme.typography.bodyLarge
                    )
                }
            } else {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(cardPadding),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    WeatherIcon(
                        iconCode = weather.weatherIcon,
                        contentDescription = weatherDescription,
                        modifier = Modifier.size(iconSize)
                    )

                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(
                            text = temperatureValue,
                            color = Color.White,
                            style = MaterialTheme.typography.headlineLarge
                        )

                        Text(
                            text = weatherDescription,
                            color = Color.White,
                            style = MaterialTheme.typography.bodyMedium
                        )
                    }

                    Text(
                        text = "\u2066$temperature\u2069 دمای هوا ",
                        color = Color.White,
                        style = MaterialTheme.typography.bodyMedium
                    )
                }
            }
        }
    }
}