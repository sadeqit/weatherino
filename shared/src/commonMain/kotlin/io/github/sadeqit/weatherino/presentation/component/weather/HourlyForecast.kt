package io.github.sadeqit.weatherino.presentation.component.weather

import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.VerticalDivider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import io.github.sadeqit.weatherino.domain.model.WeatherForecast
import io.github.sadeqit.weatherino.presentation.component.glass.GlassCard
import io.github.sadeqit.weatherino.presentation.theme.GlassBorder
import io.github.sadeqit.weatherino.presentation.util.toPersianDate

@Composable
fun HourlyForecast(
    forecasts: List<WeatherForecast>,
    modifier: Modifier = Modifier
) {
    if (forecasts.isEmpty()) return

    GlassCard(
        modifier = modifier
    ) {

        Column {
            Text(
                text = "پیش‌ بینی ساعتی",
                style = MaterialTheme.typography.titleMedium,
                modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.End
            )

            HorizontalDivider(
                modifier = Modifier.padding(vertical = 10.dp),
                color = GlassBorder
            )
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .horizontalScroll(rememberScrollState()),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                forecasts.take(8).forEachIndexed { index, forecast ->

                    HourlyForecastItem(
                        forecast = forecast
                    )

                    if (index < 7) {
                        VerticalDivider(
                            modifier = Modifier.height(100.dp),
                            color = GlassBorder
                        )
                    }
                }
            }

        }
    }
}
@Composable
private fun HourlyForecastItem(
    forecast: WeatherForecast
) {
    Column(
        modifier = Modifier
            .width(100.dp)
            .padding(vertical = 4.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = forecast.dateTimeText.toPersianDate(),
            style = MaterialTheme.typography.bodySmall

        )

        Text(
            text = forecast.dateTimeText
                .substringAfter(" ")
                .take(5),
            style = MaterialTheme.typography.bodySmall
        )

        Spacer(
            modifier = Modifier.height(8.dp)
        )

        WeatherIcon(
            iconCode = forecast.weatherIcon,
            contentDescription = forecast.weatherDescription,
            modifier = Modifier
                .width(48.dp)
                .height(48.dp)
        )

        Spacer(
            modifier = Modifier.height(8.dp)
        )

        Text(
            text = "${forecast.temperature.toInt()}°",
            style = MaterialTheme.typography.titleMedium
        )
    }
}