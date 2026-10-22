package io.github.sadeqit.weatherino.domain.usecase

import io.github.sadeqit.weatherino.domain.model.WeatherForecast
import io.github.sadeqit.weatherino.domain.repository.WeatherRepository

class GetForecastUseCase(
    private val repository: WeatherRepository
) {
    suspend operator fun invoke(
        latitude: Double,
        longitude: Double
    ): List<WeatherForecast> {
        return repository.getForecast(latitude, longitude)
    }
}