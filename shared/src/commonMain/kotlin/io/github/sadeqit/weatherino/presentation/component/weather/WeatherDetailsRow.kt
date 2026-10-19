package io.github.sadeqit.weatherino.presentation.component.weather

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.VerticalDivider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import io.github.sadeqit.weatherino.presentation.component.glass.GlassCard
import io.github.sadeqit.weatherino.presentation.theme.GlassBorder

@Composable
fun WeatherDetailsRow(
    humidity: Int,
    windSpeed: Double,
    visibility: Int?,
    modifier: Modifier = Modifier
) {
    GlassCard(
        modifier = modifier
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceAround,
            verticalAlignment = Alignment.CenterVertically
        ) {
            WeatherDetailItem(
                title = "رطوبت",
                value = "$humidity٪"
            )
            VerticalDivider(
                modifier = Modifier
                    .height(50.dp)
                    .padding(horizontal = 8.dp),
                color = GlassBorder
            )
            WeatherDetailItem(
                title = "باد",
                value = "$windSpeed m/s"
            )
            VerticalDivider(
                modifier = Modifier
                    .height(50.dp)
                    .padding(horizontal = 8.dp),
                color = GlassBorder
            )
            WeatherDetailItem(
                title = "دید",
                value = visibility?.let {
                    "${it / 1000.0} km"
                } ?: "-"
            )
        }
    }
}

@Composable
private fun WeatherDetailItem(
    title: String,
    value: String
) {
    androidx.compose.foundation.layout.Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = value,
            color = Color.White,
            style = MaterialTheme.typography.titleMedium
        )


        Text(
            text = title,
            color = Color.White,
            style = MaterialTheme.typography.bodyMedium
        )
    }
}