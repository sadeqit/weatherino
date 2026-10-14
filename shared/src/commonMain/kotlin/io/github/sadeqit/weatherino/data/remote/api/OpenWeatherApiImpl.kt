package io.github.sadeqit.weatherino.data.remote.api

import io.github.sadeqit.weatherino.BuildKonfig
import io.github.sadeqit.weatherino.data.remote.model.AirPollutionResponse
import io.github.sadeqit.weatherino.data.remote.model.CurrentWeatherResponse
import io.github.sadeqit.weatherino.data.remote.model.ForecastResponse
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.parameter

class OpenWeatherApiImpl(
    private val httpClient: HttpClient
) : OpenWeatherApi
{

    companion object {
        private const val BASE_URL = "https://api.openweathermap.org"
    }

    override suspend fun getCurrentWeather(
        latitude: Double,
        longitude: Double
    ): CurrentWeatherResponse {
        return httpClient.get("$BASE_URL/data/2.5/weather") {
            parameter("lat", latitude)
            parameter("lon", longitude)
            parameter("appid", BuildKonfig.OPENWEATHER_API_KEY)
            parameter("units", "metric")
            parameter("lang", "fa")
        }.body()
    }

    override suspend fun getAirPollution(
        latitude: Double,
        longitude: Double
    ): AirPollutionResponse {
        return httpClient.get("$BASE_URL/data/2.5/air_pollution") {
            parameter("lat", latitude)
            parameter("lon", longitude)
            parameter("appid", BuildKonfig.OPENWEATHER_API_KEY)
        }.body()
    }

    override suspend fun getForecast(
        latitude: Double,
        longitude: Double
    ): ForecastResponse {
        return httpClient.get("$BASE_URL/data/2.5/forecast") {
            parameter("lat", latitude)
            parameter("lon", longitude)
            parameter("appid", BuildKonfig.OPENWEATHER_API_KEY)
            parameter("units", "metric")
            parameter("lang", "fa")
        }.body()
    }
}