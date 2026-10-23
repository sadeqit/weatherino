package io.github.sadeqit.weatherino.presentation.component.weather

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
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
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import io.github.sadeqit.weatherino.domain.model.PrecipitationUnit
import io.github.sadeqit.weatherino.domain.model.PressureUnit
import io.github.sadeqit.weatherino.domain.model.Weather
import io.github.sadeqit.weatherino.domain.util.WeatherUnitFormatter
import io.github.sadeqit.weatherino.presentation.component.glass.GlassCard
import io.github.sadeqit.weatherino.presentation.theme.GlassBorder
import io.github.sadeqit.weatherino.presentation.util.forecastToPersianDate

@Composable
fun WeatherExtraDetails(
    weather: Weather,
    timestamp: Long,
    timezone: Int,
    pressureUnit: PressureUnit,
    precipitationUnit: PrecipitationUnit,
    modifier: Modifier = Modifier,
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
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "جزئیات هوا",
                        modifier = Modifier.weight(1f),
                        textAlign = TextAlign.Start,
                        color = Color.White,
                        style = MaterialTheme.typography.titleMedium
                    )

                    Text(
                        text = forecastToPersianDate(
                            timestamp = timestamp,
                            timezone = timezone,
                        ),
                        modifier = Modifier.weight(1f),
                        textAlign = TextAlign.Center,
                        color = Color.White,
                        style = MaterialTheme.typography.titleMedium
                    )

                    Text(
                        text = weather.locationName,
                        modifier = Modifier.weight(1f),
                        textAlign = TextAlign.End,
                        color = Color.White,
                        style = MaterialTheme.typography.titleMedium
                    )
                }

                Spacer(Modifier.height(15.dp))

                HorizontalDivider(
                    modifier = Modifier.fillMaxWidth(),
                    color = GlassBorder,
                    thickness = 2.dp
                )

                WeatherDetailRow(
                    title1 = "فشار",
                    value1 = WeatherUnitFormatter.pressure(
                        hectopascal = weather.pressure,
                        unit = pressureUnit,
                    ),
                    title2 = "ابر",
                    value2 = "${weather.cloudiness}%",
                )

                HorizontalDivider(
                    modifier = Modifier.fillMaxWidth(),
                    color = GlassBorder
                )

                WeatherDetailRow(
                    title1 = "بارش",
                    value1 = weather.rainOneHour?.let {
                        WeatherUnitFormatter.precipitation(
                            millimeters = it,
                            unit = precipitationUnit,
                        )
                    } ?: "ندارد",
                    title2 = "برف",
                    value2 = weather.snowOneHour?.let {
                        WeatherUnitFormatter.precipitation(
                            millimeters = it,
                            unit = precipitationUnit,
                        )
                    } ?: "ندارد",
                )

                HorizontalDivider(
                    modifier = Modifier.fillMaxWidth(),
                    color = GlassBorder
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
        modifier = Modifier
            .fillMaxWidth()
            .height(IntrinsicSize.Min)
            .padding(vertical = 16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        WeatherDetailItem(
            title = title1,
            value = value1,
            modifier = Modifier.weight(1f)
        )

        VerticalDivider(
            modifier = Modifier.padding(horizontal = 10.dp)
                .fillMaxHeight(),
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
            style = MaterialTheme.typography.bodyMedium
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
