package io.github.sadeqit.weatherino.data.local

import io.github.sadeqit.weatherino.database.AirQualityCache
import io.github.sadeqit.weatherino.database.WeatherinoDatabase
import io.github.sadeqit.weatherino.domain.model.AirQuality

class AirQualityCacheDataSource(
    database: WeatherinoDatabase
) {
    private val queries = database.airQualityCacheQueries

    suspend fun getAirQuality(
        latitude: Double,
        longitude: Double
    ): AirQuality? {
        return queries
            .getAirQuality(latitude, longitude)
            .executeAsOneOrNull()
            ?.toDomain()
    }

    suspend fun saveAirQuality(
        airQuality: AirQuality
    ) {
        queries.insertAirQuality(
            latitude = airQuality.latitude,
            longitude = airQuality.longitude,
            timestamp = airQuality.timestamp,
            aqi = airQuality.aqi.toLong(),
            carbonMonoxide = airQuality.carbonMonoxide,
            nitricOxide = airQuality.nitricOxide,
            nitrogenDioxide = airQuality.nitrogenDioxide,
            ozone = airQuality.ozone,
            sulfurDioxide = airQuality.sulfurDioxide,
            pm2_5 = airQuality.pm2_5,
            pm10 = airQuality.pm10,
            ammonia = airQuality.ammonia
        )
    }

    suspend fun deleteAirQuality(
        latitude: Double,
        longitude: Double
    ) {
        queries.deleteAirQuality(latitude, longitude)
    }
}

private fun AirQualityCache.toDomain(): AirQuality {
    return AirQuality(
        latitude = latitude,
        longitude = longitude,
        timestamp = timestamp,
        aqi = aqi.toInt(),
        carbonMonoxide = carbonMonoxide,
        nitricOxide = nitricOxide,
        nitrogenDioxide = nitrogenDioxide,
        ozone = ozone,
        sulfurDioxide = sulfurDioxide,
        pm2_5 = pm2_5,
        pm10 = pm10,
        ammonia = ammonia
    )
}