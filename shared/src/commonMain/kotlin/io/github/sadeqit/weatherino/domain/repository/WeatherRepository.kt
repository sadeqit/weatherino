package io.github.sadeqit.weatherino.domain.repository

import io.github.sadeqit.weatherino.domain.model.AirQuality
import io.github.sadeqit.weatherino.domain.model.Weather
import io.github.sadeqit.weatherino.domain.model.WeatherForecast

interface WeatherRepository {

    suspend fun getCurrentWeather(
        latitude: Double,
        longitude: Double
    ): Weather

    suspend fun getAirQuality(
        latitude: Double,
        longitude: Double
    ): AirQuality

    suspend fun getForecast(
        latitude: Double,
        longitude: Double
    ): List<WeatherForecast>

    suspend fun getCachedWeather(
        latitude: Double,
        longitude: Double
    ): Weather?


    suspend fun getCachedAirQuality(
        latitude: Double,
        longitude: Double
    ): AirQuality?

    suspend fun getCachedForecast(
        latitude: Double,
        longitude: Double
    ): List<WeatherForecast>
}