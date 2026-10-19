package io.github.sadeqit.weatherino.presentation.component.weather

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import io.github.sadeqit.weatherino.domain.model.WeatherForecast
import io.github.sadeqit.weatherino.presentation.component.glass.GlassCard
import io.github.sadeqit.weatherino.presentation.theme.GlassBorder
import io.github.sadeqit.weatherino.presentation.util.toPersianDate


@Composable
fun DailyForecast(
    forecasts: List<WeatherForecast>,
    modifier: Modifier = Modifier
) {
    if (forecasts.isEmpty()) return

    val dailyForecasts = forecasts
        .groupBy { it.dateTimeText.substringBefore(" ") }
        .values
        .take(5)

    GlassCard(
        modifier = modifier
    ) {
        CompositionLocalProvider(
            LocalLayoutDirection provides LayoutDirection.Rtl
        ) {
            Column {
                Text(
                    text = "پیش‌بینی ۵ روز آینده",
                    color = Color.White,
                    style = MaterialTheme.typography.titleMedium
                )
                HorizontalDivider(
                    modifier = Modifier.padding(vertical = 10.dp),
                    color = GlassBorder
                )

                dailyForecasts.forEachIndexed { index, dayForecasts ->
                    val first = dayForecasts.first()
                    val minTemperature = dayForecasts.minOf { it.minTemperature }
                    val maxTemperature = dayForecasts.maxOf { it.maxTemperature }

                    DailyForecastItem(
                        date = first.dateTimeText.toPersianDate(),
                        iconCode = first.weatherIcon,
                        description = first.weatherDescription,
                        minTemperature = minTemperature,
                        maxTemperature = maxTemperature
                    )

                    if (index < dailyForecasts.lastIndex) {
                        Spacer(modifier = Modifier.height(12.dp))

                        HorizontalDivider(
                            color = GlassBorder
                        )

                        Spacer(modifier = Modifier.height(12.dp))
                    }
                }
            }
        }
    }
}

@Composable
private fun DailyForecastItem(
    date: String,
    iconCode: String,
    description: String,
    minTemperature: Double,
    maxTemperature: Double
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            text = date,
            color = Color.White,
            style = MaterialTheme.typography.bodyMedium
        )

        WeatherIcon(
            iconCode = iconCode,
            contentDescription = description,
            modifier = Modifier
                .padding(horizontal = 8.dp)
                .height(42.dp)
        )

        Column(
            horizontalAlignment = Alignment.End
        ) {
            Text(
                text = "بیشینه ${maxTemperature.toInt()}°",
                color = Color.White,
                style = MaterialTheme.typography.titleMedium
            )

            Text(
                text = "کمینه ${minTemperature.toInt()}°",
                color = Color.White,
                style = MaterialTheme.typography.titleMedium
            )
        }
    }
}

