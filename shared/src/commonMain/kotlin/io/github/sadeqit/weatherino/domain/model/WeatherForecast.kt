package io.github.sadeqit.weatherino.domain.model

data class WeatherForecast(
    val timestamp: Long,
    val temperature: Double,
    val feelsLike: Double,
    val minTemperature: Double,
    val maxTemperature: Double,
    val humidity: Int,
    val pressure: Int,
    val visibility: Int?,
    val precipitationProbability: Double,
    val rainThreeHours: Double?,
    val snowThreeHours: Double?,
    val windSpeed: Double?,
    val windDirection: Int?,
    val cloudiness: Int?,
    val weatherId: Int,
    val weatherMain: String,
    val weatherDescription: String,
    val weatherIcon: String,
    val timezone: Int,
)