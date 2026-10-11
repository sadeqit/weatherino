package io.github.sadeqit.weatherino.domain.usecase

import io.github.sadeqit.weatherino.domain.model.Weather
import io.github.sadeqit.weatherino.domain.repository.WeatherRepository

class GetCurrentWeatherUseCase(
    private val repository: WeatherRepository
) {
    suspend operator fun invoke(
        latitude: Double,
        longitude: Double
    ): Weather {
        return repository.getCurrentWeather(latitude, longitude)
    }
}