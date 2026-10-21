package io.github.sadeqit.weatherino.data.model

import io.github.sadeqit.weatherino.data.remote.model.ForecastItemDto
import io.github.sadeqit.weatherino.data.remote.model.ForecastResponse
import io.github.sadeqit.weatherino.domain.model.WeatherForecast

fun ForecastResponse.toDomain(): List<WeatherForecast> {
    return list.map { item ->
        item.toDomain(
            timezone = city.timezone,
        )
    }
}

private fun ForecastItemDto.toDomain(
    timezone: Int,
): WeatherForecast {
    val condition = weather.firstOrNull()

    return WeatherForecast(
        timestamp = dt,
        temperature = main.temp,
        feelsLike = main.feelsLike,
        minTemperature = main.tempMin,
        maxTemperature = main.tempMax,
        humidity = main.humidity,
        pressure = main.pressure,
        visibility = visibility,
        precipitationProbability = pop ?: 0.0,
        rainThreeHours = rain?.threeHours,
        snowThreeHours = snow?.threeHours,
        windSpeed = wind?.speed,
        windDirection = wind?.deg,
        cloudiness = clouds?.all,
        weatherId = condition?.id ?: 0,
        weatherMain = condition?.main.orEmpty(),
        weatherDescription = condition?.description.orEmpty(),
        weatherIcon = condition?.icon.orEmpty(),
        timezone = timezone,
    )
}