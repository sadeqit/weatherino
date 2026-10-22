package io.github.sadeqit.weatherino.data.repository

import io.github.sadeqit.weatherino.data.local.AirQualityCacheDataSource
import io.github.sadeqit.weatherino.data.local.ForecastCacheDataSource
import io.github.sadeqit.weatherino.data.local.WeatherCacheDataSource
import io.github.sadeqit.weatherino.data.model.toDomain
import io.github.sadeqit.weatherino.data.remote.api.OpenWeatherApi
import io.github.sadeqit.weatherino.domain.model.AirQuality
import io.github.sadeqit.weatherino.domain.model.Weather
import io.github.sadeqit.weatherino.domain.model.WeatherForecast
import io.github.sadeqit.weatherino.domain.repository.WeatherRepository

class WeatherRepositoryImpl(
    private val api: OpenWeatherApi,
    private val cacheDataSource: WeatherCacheDataSource,
    private val airQualityCacheDataSource: AirQualityCacheDataSource,
    private val forecastCacheDataSource: ForecastCacheDataSource
): WeatherRepository {

    override suspend fun getCurrentWeather(
        latitude: Double,
        longitude: Double
    ): Weather {
        val weather = api
            .getCurrentWeather(latitude, longitude)
            .toDomain()

        val weatherToCache = weather.copy(
            latitude = latitude,
            longitude = longitude
        )

        cacheDataSource.saveWeather(weatherToCache)

        return weather
    }

    override suspend fun getCachedWeather(
        latitude: Double,
        longitude: Double
    ): Weather? {
        return cacheDataSource.getWeather(
            latitude = latitude,
            longitude = longitude
        )
    }

    override suspend fun getAirQuality(
        latitude: Double,
        longitude: Double
    ): AirQuality {
        val airQuality = api
            .getAirPollution(latitude, longitude)
            .toDomain()

        val airQualityToCache = airQuality.copy(
            latitude = latitude,
            longitude = longitude
        )

        airQualityCacheDataSource.saveAirQuality(
            airQualityToCache
        )

        return airQualityToCache
    }

    override suspend fun getForecast(
        latitude: Double,
        longitude: Double
    ): List<WeatherForecast> {
        val forecast = api
            .getForecast(latitude, longitude)
            .toDomain()

        forecastCacheDataSource.saveForecast(
            latitude = latitude,
            longitude = longitude,
            forecasts = forecast
        )

        return forecast
    }
    override suspend fun getCachedAirQuality(
        latitude: Double,
        longitude: Double
    ): AirQuality? {
        return airQualityCacheDataSource.getAirQuality(
            latitude = latitude,
            longitude = longitude
        )
    }
    override suspend fun getCachedForecast(
        latitude: Double,
        longitude: Double
    ): List<WeatherForecast> {
        return forecastCacheDataSource.getForecast(
            latitude = latitude,
            longitude = longitude
        )
    }

}