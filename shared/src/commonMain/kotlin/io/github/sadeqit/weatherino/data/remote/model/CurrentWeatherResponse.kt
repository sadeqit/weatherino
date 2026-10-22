package io.github.sadeqit.weatherino.data.remote.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CurrentWeatherResponse(
    val coord: CoordinatesDto,
    val weather: List<WeatherConditionDto>,
    val main: MainWeatherDto,
    val visibility: Int? = null,
    val wind: WindDto? = null,
    val clouds: CloudsDto? = null,
    val rain: RainDto? = null,
    val snow: SnowDto? = null,
    val sys: SystemInfoDto? = null,
    val dt: Long,
    val timezone: Int,
    val id: Long,
    val name: String,
    val cod: Int
)

@Serializable
data class CoordinatesDto(
    val lon: Double,
    val lat: Double
)

@Serializable
data class WeatherConditionDto(
    val id: Int,
    val main: String,
    val description: String,
    val icon: String
)

@Serializable
data class MainWeatherDto(
    val temp: Double,
    @SerialName("feels_like")
    val feelsLike: Double,
    @SerialName("temp_min")
    val tempMin: Double,
    @SerialName("temp_max")
    val tempMax: Double,
    val pressure: Int,
    val humidity: Int,
    @SerialName("sea_level")
    val seaLevel: Int? = null,
    @SerialName("grnd_level")
    val groundLevel: Int? = null
)

@Serializable
data class WindDto(
    val speed: Double,
    val deg: Int,
    val gust: Double? = null
)

@Serializable
data class CloudsDto(
    val all: Int
)

@Serializable
data class RainDto(
    @SerialName("1h")
    val oneHour: Double? = null,
    @SerialName("3h")
    val threeHours: Double? = null
)

@Serializable
data class SnowDto(
    @SerialName("1h")
    val oneHour: Double? = null,
    @SerialName("3h")
    val threeHours: Double? = null
)

@Serializable
data class SystemInfoDto(
    val type: Int? = null,
    val id: Long? = null,
    val country: String? = null,
    val sunrise: Long? = null,
    val sunset: Long? = null
)