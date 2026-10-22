package io.github.sadeqit.weatherino.data.remote.api

import io.github.sadeqit.weatherino.data.remote.model.AirPollutionResponse
import io.github.sadeqit.weatherino.data.remote.model.CurrentWeatherResponse
import io.github.sadeqit.weatherino.data.remote.model.ForecastResponse
import io.github.sadeqit.weatherino.data.remote.model.GeocodingResponse

interface OpenWeatherApi {

    suspend fun getCurrentWeather(
        latitude: Double,
        longitude: Double
    ): CurrentWeatherResponse

    suspend fun getAirPollution(
        latitude: Double,
        longitude: Double
    ): AirPollutionResponse

    suspend fun getForecast(
        latitude: Double,
        longitude: Double
    ): ForecastResponse

    suspend fun searchCities(
        query: String,
        limit: Int = 5
    ): List<GeocodingResponse>
}