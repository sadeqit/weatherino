package io.github.sadeqit.weatherino.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import io.github.sadeqit.weatherino.data.local.CityPreferences
import io.github.sadeqit.weatherino.domain.usecase.CheckNetworkConnectionUseCase
import io.github.sadeqit.weatherino.domain.usecase.GetCachedAirQualityUseCase
import io.github.sadeqit.weatherino.domain.usecase.GetCachedForecastUseCase
import io.github.sadeqit.weatherino.domain.usecase.GetCachedWeatherUseCase
import io.github.sadeqit.weatherino.domain.usecase.GetCurrentWeatherUseCase
import io.github.sadeqit.weatherino.domain.usecase.GetAirQualityUseCase
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
) : ViewModel()
{

    private val _uiState = MutableStateFlow(WeatherUiState())

    val uiState: StateFlow<WeatherUiState> = _uiState.asStateFlow()

    private var searchJob: Job? = null

    private var lastLatitude: Double? = null
    private var lastLongitude: Double? = null
    private var lastLocationName: String? = null

    fun loadWeather(
        latitude: Double,
        longitude: Double,
        locationName: String? = null
    ) {
        lastLatitude = latitude
        lastLongitude = longitude
        lastLocationName = locationName

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

                val weatherResult = async {
                    runCatching {
                        getCurrentWeather(
                            latitude,
                            longitude
                        )
                    }
                }.await()

                val airQualityResult = async {
                    runCatching {
                        getAirQuality(
                            latitude,
                            longitude
                        )
                    }
                }.await()

                val forecastResult = async {
                    runCatching {
                        getForecast(
                            latitude,
                            longitude
                        )
                    }
                }.await()

                if (
                    weatherResult.isSuccess &&
                    airQualityResult.isSuccess &&
                    forecastResult.isSuccess
                ) {
                    val weather = weatherResult.getOrThrow()
                    val airQuality = airQualityResult.getOrThrow()
                    val forecast = forecastResult.getOrThrow()

                    val finalLocationName = locationName ?: weather.locationName

                    cityPreferences.saveCity(
                        name = finalLocationName,
                        latitude = latitude,
                        longitude = longitude
                    )

                    val finalWeather = weather.copy(
                        locationName = finalLocationName
                    )

                    _uiState.value = WeatherUiState(
                        isLoading = false,
                        isOffline = false,
                        isUpdating = false,
                        weather = finalWeather,
                        airQuality = airQuality,
                        forecast = forecast,
                        error = null
                    )

                } else {

                    val exception =
                        weatherResult.exceptionOrNull()
                            ?: airQualityResult.exceptionOrNull()
                            ?: forecastResult.exceptionOrNull()

                    if (!hasCache) {
                        _uiState.value = WeatherUiState(
                            isLoading = false,
                            isOffline = false,
                            isUpdating = false,
                            error = exception?.message
                                ?: "خطا در دریافت اطلاعات هواشناسی"
                        )
                    } else {
                        _uiState.value = _uiState.value.copy(
                            isLoading = false,
                            isOffline = true,
                            isUpdating = false,
                            error = exception?.message
                        )
                    }
                }
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