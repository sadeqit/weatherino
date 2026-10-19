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
import androidx.compose.material3.VerticalDivider
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import io.github.sadeqit.weatherino.domain.model.Weather
import io.github.sadeqit.weatherino.presentation.component.glass.GlassCard
import io.github.sadeqit.weatherino.presentation.theme.GlassBorder
import io.github.sadeqit.weatherino.presentation.util.toPersianDate

@Composable
fun WeatherExtraDetails(
    weather: Weather,
    date: String,
    modifier: Modifier = Modifier
) {
    GlassCard(
        modifier = modifier
    ) {
        CompositionLocalProvider(
            LocalLayoutDirection provides LayoutDirection.Rtl
        ) {
            Column {

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        text = "جزئیات هوا",
                        color = Color.White,
                        style = MaterialTheme.typography.titleMedium
                    )

                    Text(
                        text = date.toPersianDate(),
                        color = Color.White,
                        style = MaterialTheme.typography.titleMedium
                    )

                    Text(
                        text = weather.locationName,
                        color = Color.White,
                        style = MaterialTheme.typography.titleMedium
                    )

                }

                Spacer(
                    modifier = Modifier.height(20.dp)
                )
                HorizontalDivider(
                    modifier = Modifier.fillMaxWidth(),
                    color = GlassBorder
                )
                Spacer(
                    modifier = Modifier.height(20.dp)
                )

                WeatherDetailRow(
                    title1 = "فشار",
                    value1 = "${weather.pressure} hPa",
                    title2 = "ابر",
                    value2 = "${weather.cloudiness}%"
                )

                Spacer(
                    modifier = Modifier.height(16.dp)
                )

                WeatherDetailRow(
                    title1 = "بارش",
                    value1 = weather.rainOneHour?.let { "$it mm" } ?: "ندارد",
                    title2 = "برف",
                    value2 = weather.snowOneHour?.let { "$it mm" } ?: "ندارد"
                )

                Spacer(
                    modifier = Modifier.height(16.dp)
                )

                WeatherDetailRow(
                    title1 = "طلوع",
                    value1 = formatTime(
                        weather.sunrise,
                        weather.timezone
                    ),
                    title2 = "غروب",
                    value2 = formatTime(
                        weather.sunset,
                        weather.timezone
                    )
                )
            }
        }
    }
}

@Composable
private fun WeatherDetailRow(
    title1: String,
    value1: String,
    title2: String,
    value2: String
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(24.dp)
    ) {
        WeatherDetailItem(
            title = title1,
            value = value1,
            modifier = Modifier.weight(1f)
        )
        VerticalDivider(
            modifier = Modifier
                .height(32.dp)
                .padding(horizontal = 8.dp),
            color = GlassBorder
        )

        WeatherDetailItem(
            title = title2,
            value = value2,
            modifier = Modifier.weight(1f)
        )
    }
}

@Composable
private fun WeatherDetailItem(
    title: String,
    value: String,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            text = title,
            color = Color.White,
            style = MaterialTheme.typography.bodyMedium
        )

        Text(
            text = value,
            color = Color.White,
            style = MaterialTheme.typography.titleMedium
        )
    }
}

private fun formatTime(
    timestamp: Long?,
    timezone: Int
): String {
    if (timestamp == null) return "-"

    val localTimestamp = timestamp + timezone
    val totalMinutes = (localTimestamp / 60) % (24 * 60)

    val hours = totalMinutes / 60
    val minutes = totalMinutes % 60

    val hourText = hours.toString().padStart(2, '0')
    val minuteText = minutes.toString().padStart(2, '0')

    return "$hourText:$minuteText"
}
