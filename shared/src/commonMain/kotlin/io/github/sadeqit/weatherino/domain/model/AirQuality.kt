package io.github.sadeqit.weatherino.domain.model

data class AirQuality(
    val latitude: Double,
    val longitude: Double,
    val timestamp: Long,

    val aqi: Int,

    val carbonMonoxide: Double,
    val nitricOxide: Double,
    val nitrogenDioxide: Double,
    val ozone: Double,
    val sulfurDioxide: Double,
    val pm2_5: Double,
    val pm10: Double,
    val ammonia: Double
)