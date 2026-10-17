package io.github.sadeqit.weatherino.presentation.screen.air

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import io.github.sadeqit.weatherino.domain.model.AirQuality
import io.github.sadeqit.weatherino.presentation.component.air.AirQualityCard
import io.github.sadeqit.weatherino.presentation.component.background.WeatherBackground
import io.github.sadeqit.weatherino.presentation.component.glass.GlassCard
import io.github.sadeqit.weatherino.presentation.theme.GlassBorder

@Composable
fun AirQualityScreen(
    airQuality: AirQuality,
    onBackClick: () -> Unit
) {
    WeatherBackground {
        CompositionLocalProvider(
            LocalLayoutDirection provides LayoutDirection.Rtl
        ) {

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .verticalScroll(rememberScrollState())
                    .padding(16.dp),
                verticalArrangement = Arrangement.Top
            ) {

                Row(
                    modifier = Modifier.fillMaxWidth().padding(top = 20.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "جزئیات کیفیت هوا",
                        style = MaterialTheme.typography.headlineMedium
                    )

                    Spacer(
                        modifier = Modifier.weight(1f)
                    )

                    IconButton(
                        onClick = onBackClick
                    ) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowForward,
                            contentDescription = "بازگشت"
                        )
                    }
                }

                Spacer(
                    modifier = Modifier.height(24.dp)
                )

                AirQualityCard(airQuality = airQuality)

                Spacer(
                    modifier = Modifier.height(16.dp)
                )

                GlassCard {
                    Column {
                        Text(
                            text = "آلاینده‌ ها",
                            style = MaterialTheme.typography.titleLarge
                        )

                        HorizontalDivider(
                            modifier = Modifier.padding(vertical = 12.dp),
                            color = GlassBorder
                        )

                        AirPollutantItem(
                            title = "کربن مونوکسید",
                            value = "${airQuality.carbonMonoxide} μg/m³"
                        )

                        AirPollutantItem(
                            title = "نیتریک اکسید",
                            value = "${airQuality.nitricOxide} μg/m³"
                        )

                        AirPollutantItem(
                            title = "نیتروژن دی‌اکسید",
                            value = "${airQuality.nitrogenDioxide} μg/m³"
                        )

                        AirPollutantItem(
                            title = "ازن",
                            value = "${airQuality.ozone} μg/m³"
                        )

                        AirPollutantItem(
                            title = "گوگرد دی‌اکسید",
                            value = "${airQuality.sulfurDioxide} μg/m³"
                        )

                        AirPollutantItem(
                            title = "آمونیاک",
                            value = "${airQuality.ammonia} μg/m³",
                            showDivider = false
                        )
                    }
                }
            }
        }
    }
}

@Composable
private fun AirPollutantItem(
    title: String,
    value: String,
    showDivider: Boolean = true
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 6.dp)
    ) {
        Text(
            text = title,
            style = MaterialTheme.typography.bodyMedium
        )

        Spacer(
            modifier = Modifier.height(10.dp)
        )

        Text(
            text = value,
            style = MaterialTheme.typography.bodySmall
        )

        if (showDivider) {
            HorizontalDivider(
                modifier = Modifier.padding(top = 12.dp),
                color = GlassBorder
            )
        }
    }
}