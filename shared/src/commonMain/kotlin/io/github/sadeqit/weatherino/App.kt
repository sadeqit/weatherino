package io.github.sadeqit.weatherino

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import io.github.sadeqit.weatherino.presentation.viewmodel.WeatherViewModel
import org.koin.compose.viewmodel.koinViewModel

@Composable
@Preview
fun App() {
    val viewModel: WeatherViewModel = koinViewModel()
    val uiState by viewModel.uiState.collectAsState()

    LaunchedEffect(Unit) {
        viewModel.loadWeather(
            latitude = 35.6892,
            longitude = 51.3890
        )
    }

    MaterialTheme {
        WeatherArchitectureTestScreen(uiState)
    }
}

@Composable
private fun WeatherArchitectureTestScreen(
    uiState: io.github.sadeqit.weatherino.presentation.viewmodel.WeatherUiState
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        when {
            uiState.isLoading -> {
                CircularProgressIndicator()
            }

            uiState.error != null -> {
                Text(
                    text = uiState.error,
                    color = MaterialTheme.colorScheme.error
                )
            }

            uiState.weather != null -> {
                val weather = uiState.weather!!

                Text(
                    text = weather.locationName,
                    style = MaterialTheme.typography.headlineMedium
                )

                Text(
                    text = "${weather.temperature}°C",
                    style = MaterialTheme.typography.displaySmall
                )

                Text(weather.weatherDescription)

                uiState.airQuality?.let {
                    Text("AQI: ${it.aqi}")
                }

                Text(
                    text = "Forecast: ${uiState.forecast.size} items"
                )
            }
        }
    }
}