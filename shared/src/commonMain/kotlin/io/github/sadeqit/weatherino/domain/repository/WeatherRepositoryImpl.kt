package io.github.sadeqit.weatherino.domain.repository

import io.github.sadeqit.weatherino.data.model.toDomain
import io.github.sadeqit.weatherino.data.remote.api.OpenWeatherApi
import io.github.sadeqit.weatherino.domain.model.AirQuality
import io.github.sadeqit.weatherino.domain.model.Weather
import io.github.sadeqit.weatherino.domain.model.WeatherForecast

class WeatherRepositoryImpl(
    private val api: OpenWeatherApi
) : WeatherRepository {

    override suspend fun getCurrentWeather(
        latitude: Double,
        longitude: Double
    ): Weather {
        return api
            .getCurrentWeather(latitude, longitude)
            .toDomain()
    }

    override suspend fun getAirQuality(
        latitude: Double,
        longitude: Double
    ): AirQuality {
        return api
            .getAirPollution(latitude, longitude)
            .toDomain()
    }

    override suspend fun getForecast(
        latitude: Double,
        longitude: Double
    ): List<WeatherForecast> {
        return api
            .getForecast(latitude, longitude)
            .toDomain()
    }
}