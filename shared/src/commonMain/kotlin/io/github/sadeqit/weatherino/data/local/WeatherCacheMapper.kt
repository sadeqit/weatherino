package io.github.sadeqit.weatherino.data.local

import io.github.sadeqit.weatherino.database.WeatherCache
import io.github.sadeqit.weatherino.domain.model.Weather

fun WeatherCache.toDomain(): Weather {
    return Weather(
        locationName = locationName,
        country = country,
        latitude = latitude,
        longitude = longitude,
        temperature = temperature,
        feelsLike = feelsLike,
        minTemperature = minTemperature,
        maxTemperature = maxTemperature,
        humidity = humidity.toInt(),
        pressure = pressure.toInt(),
        visibility = visibility?.toInt(),
        windSpeed = windSpeed,
        windDirection = windDirection?.toInt(),
        windGust = windGust,
        cloudiness = cloudiness.toInt(),
        rainOneHour = rainOneHour,
        rainThreeHours = rainThreeHours,
        snowOneHour = snowOneHour,
        snowThreeHours = snowThreeHours,
        weatherId = weatherId.toInt(),
        weatherMain = weatherMain,
        weatherDescription = weatherDescription,
        weatherIcon = weatherIcon,
        sunrise = sunrise,
        sunset = sunset,
        timestamp = timestamp,
        timezone = timezone.toInt()
    )
}