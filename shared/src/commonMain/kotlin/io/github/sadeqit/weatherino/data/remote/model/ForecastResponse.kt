package io.github.sadeqit.weatherino.data.remote.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ForecastResponse(
    val cod: String,
    val message: Int,
    val cnt: Int,
    val list: List<ForecastItemDto>,
    val city: ForecastCityDto
)

@Serializable
data class ForecastItemDto(
    val dt: Long,
    val main: ForecastMainDto,
    val weather: List<WeatherConditionDto>,
    val clouds: CloudsDto? = null,
    val wind: WindDto? = null,
    val visibility: Int? = null,
    val pop: Double? = null,
    val rain: ForecastRainDto? = null,
    val snow: ForecastSnowDto? = null,
    val sys: ForecastSystemDto? = null,
    @SerialName("dt_txt")
    val dateTimeText: String
)

@Serializable
data class ForecastMainDto(
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
data class ForecastRainDto(
    @SerialName("3h")
    val threeHours: Double? = null
)

@Serializable
data class ForecastSnowDto(
    @SerialName("3h")
    val threeHours: Double? = null
)

@Serializable
data class ForecastSystemDto(
    val pod: String? = null
)

@Serializable
data class ForecastCityDto(
    val id: Long,
    val name: String,
    val coord: CoordinatesDto,
    val country: String,
    val timezone: Int,
    val sunrise: Long? = null,
    val sunset: Long? = null
)