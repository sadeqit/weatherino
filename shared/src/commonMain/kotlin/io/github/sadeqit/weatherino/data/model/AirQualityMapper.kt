package io.github.sadeqit.weatherino.data.model

import io.github.sadeqit.weatherino.data.remote.model.AirPollutionResponse
import io.github.sadeqit.weatherino.domain.model.AirQuality

fun AirPollutionResponse.toDomain(): AirQuality {
    val data = list.firstOrNull()
        ?: error("Air pollution response contains no data")

    return AirQuality(
        latitude = coord.lat,
        longitude = coord.lon,
        timestamp = data.dt,

        aqi = data.main.aqi,

        carbonMonoxide = data.components.co,
        nitricOxide = data.components.no,
        nitrogenDioxide = data.components.no2,
        ozone = data.components.o3,
        sulfurDioxide = data.components.so2,
        pm2_5 = data.components.pm2_5,
        pm10 = data.components.pm10,
        ammonia = data.components.nh3
    )
}