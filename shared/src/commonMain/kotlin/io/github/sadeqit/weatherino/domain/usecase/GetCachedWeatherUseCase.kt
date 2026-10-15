package io.github.sadeqit.weatherino.domain.usecase

import io.github.sadeqit.weatherino.domain.model.Weather
import io.github.sadeqit.weatherino.domain.repository.WeatherRepository

class GetCachedWeatherUseCase(
    private val repository: WeatherRepository
) {
    suspend operator fun invoke(
        latitude: Double,
        longitude: Double
    ): Weather? {
        return repository.getCachedWeather(
            latitude = latitude,
            longitude = longitude
        )
    }
}