package io.github.sadeqit.weatherino

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import io.github.sadeqit.weatherino.presentation.navigation.AppNavigation
import io.github.sadeqit.weatherino.presentation.theme.WeatherinoTheme
import io.github.sadeqit.weatherino.presentation.viewmodel.WeatherViewModel
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun App() {
    val viewModel: WeatherViewModel = koinViewModel()
    val uiState by viewModel.uiState.collectAsState()

    LaunchedEffect(Unit) {
        viewModel.loadWeather(
            latitude = 35.6892,
            longitude = 51.3890
        )
    }

    WeatherinoTheme {
        Box(
            modifier = Modifier.fillMaxSize()
        ) {
            AppNavigation(
                uiState = uiState
            )
        }
    }
}