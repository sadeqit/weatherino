package io.github.sadeqit.weatherino.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import io.github.sadeqit.weatherino.data.local.CityPreferences
import io.github.sadeqit.weatherino.domain.usecase.GetAirQualityUseCase
import io.github.sadeqit.weatherino.domain.usecase.GetCachedAirQualityUseCase
import io.github.sadeqit.weatherino.domain.usecase.GetCachedForecastUseCase
import io.github.sadeqit.weatherino.domain.usecase.GetCachedWeatherUseCase
import io.github.sadeqit.weatherino.domain.usecase.GetCurrentWeatherUseCase
import io.github.sadeqit.weatherino.domain.usecase.GetForecastUseCase
import io.github.sadeqit.weatherino.domain.usecase.SearchCitiesUseCase
import kotlinx.coroutines.Job
import kotlinx.coroutines.async
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlin.time.Duration.Companion.milliseconds

class WeatherViewModel(
    private val getCurrentWeather: GetCurrentWeatherUseCase,
    private val getCachedWeather: GetCachedWeatherUseCase,
    private val getCachedAirQuality: GetCachedAirQualityUseCase,
    private val getCachedForecast: GetCachedForecastUseCase,
    private val getAirQuality: GetAirQualityUseCase,
    private val getForecast: GetForecastUseCase,
    private val searchCitiesUseCase: SearchCitiesUseCase,
    private val cityPreferences: CityPreferences
) : ViewModel() {

    private val _uiState = MutableStateFlow(WeatherUiState())
    val uiState: StateFlow<WeatherUiState> = _uiState.asStateFlow()

    private var searchJob: Job? = null

    fun loadWeather(
        latitude: Double,
        longitude: Double,
        locationName: String? = null
    ) {
        viewModelScope.launch {
            val cachedWeather = getCachedWeather(
                latitude = latitude,
                longitude = longitude
            )

            val cachedAirQuality = getCachedAirQuality(
                latitude = latitude,
                longitude = longitude
            )

            val cachedForecast = getCachedForecast(
                latitude = latitude,
                longitude = longitude
            )

            if (
                cachedWeather != null ||
                cachedAirQuality != null ||
                cachedForecast.isNotEmpty()
            ) {
                _uiState.value = _uiState.value.copy(
                    isLoading = false,
                    weather = cachedWeather,
                    airQuality = cachedAirQuality,
                    forecast = cachedForecast,
                    error = null
                )
            } else {
                _uiState.value = WeatherUiState(isLoading = true)
            }

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
                locationName?.let {
                    cityPreferences.saveCity(
                        name = it,
                        latitude = latitude,
                        longitude = longitude
                    )
                }

                val finalWeather = locationName?.let {
                    weather.copy(locationName = it)
                } ?: weather

                _uiState.value = WeatherUiState(
                    weather = finalWeather,
                    airQuality = airQuality,
                    forecast = forecast
                )
            }.onFailure { exception ->
                if (
                    cachedWeather == null &&
                    cachedAirQuality == null &&
                    cachedForecast.isEmpty()
                ) {
                    _uiState.value = WeatherUiState(
                        error = exception.message ?: "Unknown error"
                    )
                } else {
                    _uiState.value = _uiState.value.copy(
                        error = exception.message
                    )
                }
            }
        }
    }


    fun searchCities(query: String) {
        _uiState.value = _uiState.value.copy(
            searchQuery = query
        )

        searchJob?.cancel()

        if (query.isBlank()) {
            _uiState.value = _uiState.value.copy(
                isSearchingCities = false,
                searchCities = emptyList(),
                searchError = null
            )
            return
        }

        searchJob = viewModelScope.launch {
            delay(400.milliseconds)

            _uiState.value = _uiState.value.copy(
                isSearchingCities = true,
                searchError = null
            )

            runCatching {
                searchCitiesUseCase(query)
            }.onSuccess { cities ->
                _uiState.value = _uiState.value.copy(
                    isSearchingCities = false,
                    searchCities = cities,
                    searchError = null
                )
            }.onFailure { exception ->
                _uiState.value = _uiState.value.copy(
                    isSearchingCities = false,
                    searchCities = emptyList(),
                    searchError = exception.message ?: "خطا در جستجوی شهر"
                )
            }
        }
    }


}
/*
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
) : ViewModel()
{

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
}*/
