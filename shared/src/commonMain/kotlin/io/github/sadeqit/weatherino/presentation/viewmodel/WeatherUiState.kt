package io.github.sadeqit.weatherino.presentation.viewmodel

import io.github.sadeqit.weatherino.domain.model.AirQuality
import io.github.sadeqit.weatherino.domain.model.Weather
import io.github.sadeqit.weatherino.domain.model.WeatherForecast

data class WeatherUiState(
    val isLoading: Boolean = false,
    val weather: Weather? = null,
    val airQuality: AirQuality? = null,
    val forecast: List<WeatherForecast> = emptyList(),
    val error: String? = null
)