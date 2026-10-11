package io.github.sadeqit.weatherino.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import io.github.sadeqit.weatherino.domain.usecase.GetAirQualityUseCase
import io.github.sadeqit.weatherino.domain.usecase.GetCurrentWeatherUseCase
import io.github.sadeqit.weatherino.domain.usecase.GetForecastUseCase
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class WeatherViewModel(
    private val getCurrentWeather: GetCurrentWeatherUseCase,
    private val getAirQuality: GetAirQualityUseCase,
    private val getForecast: GetForecastUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow(WeatherUiState())
    val uiState: StateFlow<WeatherUiState> = _uiState.asStateFlow()

    fun loadWeather(
        latitude: Double,
        longitude: Double
    ) {
        viewModelScope.launch {
            _uiState.value = WeatherUiState(isLoading = true)

            runCatching {
                val weatherDeferred = async {
                    getCurrentWeather(latitude, longitude)
                }

                val airQualityDeferred = async {
                    getAirQuality(latitude, longitude)
                }

                val forecastDeferred = async {
                    getForecast(latitude, longitude)
                }

                Triple(
                    weatherDeferred.await(),
                    airQualityDeferred.await(),
                    forecastDeferred.await()
                )
            }.onSuccess { (weather, airQuality, forecast) ->
                _uiState.value = WeatherUiState(
                    weather = weather,
                    airQuality = airQuality,
                    forecast = forecast
                )
            }.onFailure { exception ->
                _uiState.value = WeatherUiState(
                    error = exception.message ?: "Unknown error"
                )
            }
        }
    }
}