package io.github.sadeqit.weatherino.data.local

import io.github.sadeqit.weatherino.database.ForecastCache
import io.github.sadeqit.weatherino.database.WeatherinoDatabase
import io.github.sadeqit.weatherino.domain.model.WeatherForecast

class ForecastCacheDataSource(
    database: WeatherinoDatabase
) {
    private val queries = database.forecastCacheQueries

    suspend fun getForecast(
        latitude: Double,
        longitude: Double
    ): List<WeatherForecast> {
        return queries
            .getForecast(latitude, longitude)
            .executeAsList()
            .map { it.toDomain() }
    }

    suspend fun saveForecast(
        latitude: Double,
        longitude: Double,
        forecasts: List<WeatherForecast>
    ) {
        queries.transaction {
            queries.deleteForecast(latitude, longitude)

            forecasts.forEach { forecast ->
                queries.insertForecast(
                    latitude = latitude,
                    longitude = longitude,
                    timestamp = forecast.timestamp,
                    temperature = forecast.temperature,
                    feelsLike = forecast.feelsLike,
                    minTemperature = forecast.minTemperature,
                    maxTemperature = forecast.maxTemperature,
                    humidity = forecast.humidity.toLong(),
                    pressure = forecast.pressure.toLong(),
                    visibility = forecast.visibility?.toLong(),
                    precipitationProbability = forecast.precipitationProbability,
                    rainThreeHours = forecast.rainThreeHours,
                    snowThreeHours = forecast.snowThreeHours,
                    windSpeed = forecast.windSpeed,
                    windDirection = forecast.windDirection?.toLong(),
                    cloudiness = forecast.cloudiness?.toLong(),
                    weatherId = forecast.weatherId.toLong(),
                    weatherMain = forecast.weatherMain,
                    weatherDescription = forecast.weatherDescription,
                    weatherIcon = forecast.weatherIcon,
                    timezone = forecast.timezone.toLong(),
                )
            }
        }
    }

}

private fun ForecastCache.toDomain(): WeatherForecast {
    return WeatherForecast(
        timestamp = timestamp,
        temperature = temperature,
        feelsLike = feelsLike,
        minTemperature = minTemperature,
        maxTemperature = maxTemperature,
        humidity = humidity.toInt(),
        pressure = pressure.toInt(),
        visibility = visibility?.toInt(),
        precipitationProbability = precipitationProbability,
        rainThreeHours = rainThreeHours,
        snowThreeHours = snowThreeHours,
        windSpeed = windSpeed,
        windDirection = windDirection?.toInt(),
        cloudiness = cloudiness?.toInt(),
        weatherId = weatherId.toInt(),
        weatherMain = weatherMain,
        weatherDescription = weatherDescription,
        weatherIcon = weatherIcon,
        timezone = timezone.toInt(),
    )
}