package io.github.sadeqit.weatherino.domain.usecase

import io.github.sadeqit.weatherino.domain.model.AirQuality
import io.github.sadeqit.weatherino.domain.repository.WeatherRepository

class GetAirQualityUseCase(
    private val repository: WeatherRepository
) {
    suspend operator fun invoke(
        latitude: Double,
        longitude: Double
    ): AirQuality {
        return repository.getAirQuality(latitude, longitude)
    }
}