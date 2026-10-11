package io.github.sadeqit.weatherino.data.remote.model

import kotlinx.serialization.Serializable

@Serializable
data class AirPollutionResponse(
    val coord: CoordinatesDto,
    val list: List<AirPollutionDataDto>
)

@Serializable
data class AirPollutionDataDto(
    val dt: Long,
    val main: AirQualityIndexDto,
    val components: AirPollutionComponentsDto
)

@Serializable
data class AirQualityIndexDto(
    val aqi: Int
)

@Serializable
data class AirPollutionComponentsDto(
    val co: Double,
    val no: Double,
    val no2: Double,
    val o3: Double,
    val so2: Double,
    val pm2_5: Double,
    val pm10: Double,
    val nh3: Double
)