package io.github.sadeqit.weatherino.domain.usecase

import io.github.sadeqit.weatherino.domain.model.WeatherForecast
import io.github.sadeqit.weatherino.domain.repository.WeatherRepository

class GetCachedForecastUseCase(
    private val repository: WeatherRepository
) {
    suspend operator fun invoke(
        latitude: Double,
        longitude: Double
    ): List<WeatherForecast> {
        return repository.getCachedForecast(
            latitude = latitude,
            longitude = longitude
        )
    }
}