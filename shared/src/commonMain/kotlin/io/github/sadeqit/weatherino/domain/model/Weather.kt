package io.github.sadeqit.weatherino.domain.model

data class Weather(
    val locationName: String,
    val country: String,
    val latitude: Double,
    val longitude: Double,

    val temperature: Double,
    val feelsLike: Double,
    val minTemperature: Double,
    val maxTemperature: Double,

    val humidity: Int,
    val pressure: Int,
    val visibility: Int?,

    val windSpeed: Double,
    val windDirection: Int?,
    val windGust: Double?,

    val cloudiness: Int,

    val rainOneHour: Double?,
    val rainThreeHours: Double?,

    val snowOneHour: Double?,
    val snowThreeHours: Double?,

    val weatherId: Int,
    val weatherMain: String,
    val weatherDescription: String,
    val weatherIcon: String,

    val sunrise: Long?,
    val sunset: Long?,

    val timestamp: Long,
    val timezone: Int
)