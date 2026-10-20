package io.github.sadeqit.weatherino.presentation.component.air

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.rounded.ArrowForward
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
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
import io.github.sadeqit.weatherino.domain.model.AirQuality
import io.github.sadeqit.weatherino.presentation.component.glass.GlassCard
import io.github.sadeqit.weatherino.presentation.theme.GlassBorder
import kotlin.math.roundToInt


@Composable
fun AirQualityCard(
    airQuality: AirQuality,
    onClick: (() -> Unit)? = null,
    @Suppress("ModifierParameter") modifier: Modifier = Modifier
) {
    GlassCard(
        modifier = modifier.then(
            if (onClick != null) {
                Modifier.clickable(onClick = onClick)
            } else {
                Modifier
            }
        )
    ) {
        CompositionLocalProvider(
            LocalLayoutDirection provides LayoutDirection.Rtl
        ) {
            Column {
                val aqi = calibrateAqi(
                    calculateAqi(
                        pm2_5 = airQuality.pm2_5,
                        pm10 = airQuality.pm10
                    )
                )

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        text = "کیفیت هوا",
                        color = Color.White,
                        style = MaterialTheme.typography.titleMedium
                    )

                    if (onClick != null) {
                        Row(
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text(
                                text = "جزئیات بیشتر",
                                color = Color.White,
                                style = MaterialTheme.typography.bodyMedium
                            )

                            Spacer(
                                modifier = Modifier.width(4.dp)
                            )

                            Icon(
                                imageVector = Icons.AutoMirrored.Rounded.ArrowForward,
                                contentDescription = "جزئیات بیشتر",
                                tint = Color.White
                            )
                        }
                    }
                }

                HorizontalDivider(
                    modifier = Modifier.padding(vertical = 10.dp),
                    color = GlassBorder
                )
                Column(
                    modifier = Modifier.fillMaxWidth(),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.Center,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = "شاخص کیفیت هوا:",
                            color = Color.White,
                            style = MaterialTheme.typography.bodyMedium
                        )
                        Spacer(modifier = Modifier.width(10.dp))
                        Text(
                            text = aqi.toString(),
                            color = Color.White,
                            style = MaterialTheme.typography.bodyMedium
                        )

                    }
                    HorizontalDivider(
                        modifier = Modifier
                            .padding(vertical = 10.dp)
                            .width(150.dp),
                        color = GlassBorder
                    )
                    Text(
                        text = airQualityDescription(aqi),
                        color = Color.White,
                        style = MaterialTheme.typography.bodyMedium
                    )
                }
            }
        }
    }
}

private fun calibrateAqi(rawAqi: Int): Int {
    return (rawAqi * 1.60)
        .roundToInt()
        .coerceIn(0, 500)
}

private fun airQualityDescription(aqi: Int): String {
    return when {
        aqi <= 50 -> "کیفیت هوا خوب است"
        aqi <= 100 -> "کیفیت هوا قابل قبول است"
        aqi <= 150 -> "کیفیت هوا ناسالم برای افراد حساس است"
        aqi <= 200 -> "کیفیت هوا ناسالم است"
        aqi <= 300 -> "کیفیت هوا بسیار ناسالم است"
        else -> "خطرناک"
    }
}

private fun calculateAqi(
    pm2_5: Double,
    pm10: Double
): Int {
    val pm25Aqi = calculatePm25Aqi(pm2_5)
    val pm10Aqi = calculatePm10Aqi(pm10)

    return maxOf(pm25Aqi, pm10Aqi)
}

private fun calculatePm25Aqi(pm25: Double): Int {
    val value = pm25.coerceAtLeast(0.0)

    return when {
        value <= 12.0 -> interpolate(value, 0.0, 12.0, 0, 50)
        value <= 35.4 -> interpolate(value, 12.1, 35.4, 51, 100)
        value <= 55.4 -> interpolate(value, 35.5, 55.4, 101, 150)
        value <= 150.4 -> interpolate(value, 55.5, 150.4, 151, 200)
        value <= 250.4 -> interpolate(value, 150.5, 250.4, 201, 300)
        value <= 350.4 -> interpolate(value, 250.5, 350.4, 301, 400)
        value <= 500.4 -> interpolate(value, 350.5, 500.4, 401, 500)
        else -> 500
    }
}

private fun calculatePm10Aqi(pm10: Double): Int {
    val value = pm10.coerceAtLeast(0.0)

    return when {
        value <= 54.0 -> interpolate(value, 0.0, 54.0, 0, 50)
        value <= 154.0 -> interpolate(value, 55.0, 154.0, 51, 100)
        value <= 254.0 -> interpolate(value, 155.0, 254.0, 101, 150)
        value <= 354.0 -> interpolate(value, 255.0, 354.0, 151, 200)
        value <= 424.0 -> interpolate(value, 355.0, 424.0, 201, 300)
        value <= 504.0 -> interpolate(value, 425.0, 504.0, 301, 500)
        else -> 500
    }
}

private fun interpolate(
    concentration: Double,
    concentrationLow: Double,
    concentrationHigh: Double,
    aqiLow: Int,
    aqiHigh: Int
): Int {
    return (
            ((aqiHigh - aqiLow) /
                    (concentrationHigh - concentrationLow)) *
                    (concentration - concentrationLow) +
                    aqiLow
            ).roundToInt()
}