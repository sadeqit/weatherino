package io.github.sadeqit.weatherino.data.model

import io.github.sadeqit.weatherino.data.remote.model.CurrentWeatherResponse
import io.github.sadeqit.weatherino.domain.model.Weather

fun CurrentWeatherResponse.toDomain(): Weather {
    val condition = weather.firstOrNull()

    return Weather(
        locationName = name,
        country = sys?.country.orEmpty(),
        latitude = coord.lat,
        longitude = coord.lon,

        temperature = main.temp,
        feelsLike = main.feelsLike,
        minTemperature = main.tempMin,
        maxTemperature = main.tempMax,

        humidity = main.humidity,
        pressure = main.pressure,
        visibility = visibility,

        windSpeed = wind?.speed ?: 0.0,
        windDirection = wind?.deg,
        windGust = wind?.gust,

        cloudiness = clouds?.all ?: 0,

        rainOneHour = rain?.oneHour,
        rainThreeHours = rain?.threeHours,

        snowOneHour = snow?.oneHour,
        snowThreeHours = snow?.threeHours,

        weatherId = condition?.id ?: 0,
        weatherMain = condition?.main.orEmpty(),
        weatherDescription = condition?.description.orEmpty(),
        weatherIcon = condition?.icon.orEmpty(),

        sunrise = sys?.sunrise,
        sunset = sys?.sunset,

        timestamp = dt,
        timezone = timezone
    )
}