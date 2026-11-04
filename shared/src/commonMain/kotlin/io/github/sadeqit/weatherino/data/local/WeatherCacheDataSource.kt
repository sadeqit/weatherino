package io.github.sadeqit.weatherino.data.local

import io.github.sadeqit.weatherino.core.dispatcher.databaseDispatcher
import io.github.sadeqit.weatherino.database.WeatherinoDatabase
import io.github.sadeqit.weatherino.domain.model.Weather
import kotlinx.coroutines.withContext

class WeatherCacheDataSource(
    database: WeatherinoDatabase
) {

    private val queries = database.weatherCacheQueries

    suspend fun getWeather(
        latitude: Double,
        longitude: Double
    ): Weather? = withContext(databaseDispatcher) {
        queries
            .getWeather(
                latitude.toCacheKey(),
                longitude.toCacheKey()
            )
            .executeAsOneOrNull()
            ?.toDomain()
    }

    suspend fun saveWeather(
        weather: Weather
    ) {
        withContext(databaseDispatcher) {
            queries.insertWeather(
                latitude = weather.latitude.toCacheKey(),
                longitude = weather.longitude.toCacheKey(),
                locationName = weather.locationName,
                country = weather.country,
                temperature = weather.temperature,
                feelsLike = weather.feelsLike,
                minTemperature = weather.minTemperature,
                maxTemperature = weather.maxTemperature,
                humidity = weather.humidity.toLong(),
                pressure = weather.pressure.toLong(),
                visibility = weather.visibility?.toLong(),
                windSpeed = weather.windSpeed,
                windDirection = weather.windDirection?.toLong(),
                windGust = weather.windGust,
                cloudiness = weather.cloudiness.toLong(),
                rainOneHour = weather.rainOneHour,
                rainThreeHours = weather.rainThreeHours,
                snowOneHour = weather.snowOneHour,
                snowThreeHours = weather.snowThreeHours,
                weatherId = weather.weatherId.toLong(),
                weatherMain = weather.weatherMain,
                weatherDescription = weather.weatherDescription,
                weatherIcon = weather.weatherIcon,
                sunrise = weather.sunrise,
                sunset = weather.sunset,
                timestamp = weather.timestamp,
                timezone = weather.timezone.toLong()
            )
        }
    }

    suspend fun deleteWeather(
        latitude: Double,
        longitude: Double
    ) {
        withContext(databaseDispatcher) {
            queries.deleteWeather(
                latitude.toCacheKey(),
                longitude.toCacheKey()
            )
        }
    }
}