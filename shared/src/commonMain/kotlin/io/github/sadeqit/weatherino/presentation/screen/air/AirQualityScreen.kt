package io.github.sadeqit.weatherino.presentation.screen.air

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBars
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.text.font.FontWeight
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
    onBackClick: () -> Unit,
) {
    WeatherBackground {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .windowInsetsPadding(WindowInsets.statusBars),
        ) {
            AirQualityTopBar(
                onBackClick = onBackClick,
            )

            CompositionLocalProvider(
                LocalLayoutDirection provides LayoutDirection.Rtl,
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .verticalScroll(rememberScrollState())
                        .padding(horizontal = 16.dp),
                    verticalArrangement = Arrangement.Top,
                ) {
                    Spacer(
                        modifier = Modifier.height(12.dp),
                    )

                    AirQualityCard(
                        airQuality = airQuality,
                    )

                    Spacer(
                        modifier = Modifier.height(16.dp),
                    )

                    GlassCard {
                        Column {
                            Text(
                                text = "آلاینده‌ها",
                                color = Color.White,
                                style = MaterialTheme.typography.titleLarge,
                            )

                            HorizontalDivider(
                                modifier = Modifier.padding(vertical = 12.dp),
                                color = GlassBorder,
                            )

                            AirPollutantItem(
                                title = "کربن مونوکسید",
                                value = "${airQuality.carbonMonoxide} μg/m³",
                            )

                            AirPollutantItem(
                                title = "نیتریک اکسید",
                                value = "${airQuality.nitricOxide} μg/m³",
                            )

                            AirPollutantItem(
                                title = "نیتروژن دی‌اکسید",
                                value = "${airQuality.nitrogenDioxide} μg/m³",
                            )

                            AirPollutantItem(
                                title = "ازن",
                                value = "${airQuality.ozone} μg/m³",
                            )

                            AirPollutantItem(
                                title = "گوگرد دی‌اکسید",
                                value = "${airQuality.sulfurDioxide} μg/m³",
                            )

                            AirPollutantItem(
                                title = "آمونیاک",
                                value = "${airQuality.ammonia} μg/m³",
                                showDivider = false,
                            )
                        }
                    }

                    Spacer(
                        modifier = Modifier.height(16.dp),
                    )
                }
            }
        }
    }
}

@Composable
private fun AirQualityTopBar(
    onBackClick: () -> Unit,
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(64.dp)
            .padding(horizontal = 12.dp),
    ) {
        IconButton(
            onClick = onBackClick,
            modifier = Modifier.align(Alignment.CenterStart),
        ) {
            Icon(
                imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                contentDescription = "بازگشت",
                tint = Color.White,
            )
        }

        Text(
            text = "جزئیات کیفیت هوا",
            modifier = Modifier.align(Alignment.Center),
            color = Color.White,
            style = MaterialTheme.typography.titleLarge,
            fontWeight = FontWeight.Bold,
        )
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
            color = Color.White,
            style = MaterialTheme.typography.bodyMedium
        )

        Spacer(
            modifier = Modifier.height(10.dp)
        )

        Text(
            text = value,
            color = Color.White,
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