package io.github.sadeqit.weatherino.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import io.github.sadeqit.weatherino.data.local.CityPreferences
import io.github.sadeqit.weatherino.domain.usecase.CheckNetworkConnectionUseCase
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
import kotlinx.coroutines.supervisorScope
import kotlin.coroutines.cancellation.CancellationException
import kotlin.time.Clock
import kotlin.time.Duration.Companion.milliseconds

class WeatherViewModel(
    private val getCurrentWeather: GetCurrentWeatherUseCase,
    private val getCachedWeather: GetCachedWeatherUseCase,
    private val getCachedAirQuality: GetCachedAirQualityUseCase,
    private val getCachedForecast: GetCachedForecastUseCase,
    private val getAirQuality: GetAirQualityUseCase,
    private val getForecast: GetForecastUseCase,
    private val searchCitiesUseCase: SearchCitiesUseCase,
    private val cityPreferences: CityPreferences,
    private val checkNetworkConnection: CheckNetworkConnectionUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow(WeatherUiState())

    val uiState: StateFlow<WeatherUiState> = _uiState.asStateFlow()

    private var searchJob: Job? = null
    private var loadJob: Job? = null

    private var lastLatitude: Double? = null
    private var lastLongitude: Double? = null
    private var lastLocationName: String? = null

    fun loadWeather(
        latitude: Double,
        longitude: Double,
        locationName: String? = null
    ) {
        // Cancel previous loading
        loadJob?.cancel()

        lastLatitude = latitude
        lastLongitude = longitude
        lastLocationName = locationName

        loadJob = viewModelScope.launch {

            // Load cached data
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
            ).filter {
                it.timestamp >= Clock.System.now().epochSeconds
            }

            val hasCache =
                cachedWeather != null ||
                        cachedAirQuality != null ||
                        cachedForecast.isNotEmpty()

            val isConnected = runCatching {
                checkNetworkConnection()
            }.getOrDefault(false)

            if (hasCache) {
                _uiState.value = _uiState.value.copy(
                    isLoading = false,
                    isOffline = !isConnected,
                    isUpdating = isConnected,
                    weather = cachedWeather,
                    airQuality = cachedAirQuality,
                    forecast = cachedForecast,
                    error = null
                )
            } else {
                _uiState.value = WeatherUiState(
                    isLoading = true,
                    isOffline = !isConnected,
                    isUpdating = false
                )
            }

            if (!isConnected) {
                if (!hasCache) {
                    _uiState.value = WeatherUiState(
                        isLoading = false,
                        isOffline = true,
                        isUpdating = false,
                        error = "اینترنت وصل نیست"
                    )
                }

                return@launch
            }

            supervisorScope {

                // Start all requests concurrently
                val weatherDeferred = async {
                    try {
                        Result.success(getCurrentWeather(latitude, longitude))
                    } catch (exception: CancellationException) {
                        throw exception
                    } catch (exception: Exception) {
                        Result.failure(exception)
                    }
                }

                val airQualityDeferred = async {
                    try {
                        Result.success(getAirQuality(latitude, longitude))
                    } catch (exception: CancellationException) {
                        throw exception
                    } catch (exception: Exception) {
                        Result.failure(exception)
                    }
                }

                val forecastDeferred = async {
                    try {
                        Result.success(getForecast(latitude, longitude))
                    } catch (exception: CancellationException) {
                        throw exception
                    } catch (exception: Exception) {
                        Result.failure(exception)
                    }
                }

                // Collect results
                val weatherResult = weatherDeferred.await()
                val airQualityResult = airQualityDeferred.await()
                val forecastResult = forecastDeferred.await()

                // Use cached data when a request fails
                val weather =
                    weatherResult.getOrNull() ?: cachedWeather

                val airQuality =
                    airQualityResult.getOrNull() ?: cachedAirQuality

                val forecast =
                    forecastResult.getOrNull()
                        ?.filter {
                            it.timestamp >= Clock.System.now().epochSeconds
                        }
                        ?: cachedForecast

                val finalLocationName =
                    locationName ?: weather?.locationName

                // Save selected city
                if (weather != null) {
                    cityPreferences.saveCity(
                        name = finalLocationName ?: weather.locationName,
                        latitude = latitude,
                        longitude = longitude
                    )
                }

                val finalWeather = weather?.copy(
                    locationName = finalLocationName ?: weather.locationName
                )

                // Show available data independently
                val hasError = weatherResult.isFailure ||
                        airQualityResult.isFailure ||
                        forecastResult.isFailure

                _uiState.value = WeatherUiState(
                    isLoading = false,
                    isOffline = false,
                    isUpdating = false,
                    weather = finalWeather,
                    airQuality = airQuality,
                    forecast = forecast,
                    error = if (finalWeather == null && hasError) {
                        weatherResult.exceptionOrNull()?.message
                            ?: airQualityResult.exceptionOrNull()?.message
                            ?: forecastResult.exceptionOrNull()?.message
                            ?: "خطا در دریافت اطلاعات هواشناسی"
                    } else {
                        null
                    }
                )
            }
        }
    }

    fun retryWeather() {
        val latitude = lastLatitude ?: return
        val longitude = lastLongitude ?: return

        loadWeather(
            latitude = latitude,
            longitude = longitude,
            locationName = lastLocationName
        )
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
                    searchError = exception.message
                        ?: "خطا در جستجوی شهر"
                )
            }
        }
    }
}