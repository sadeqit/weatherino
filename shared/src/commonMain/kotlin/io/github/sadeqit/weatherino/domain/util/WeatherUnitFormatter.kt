package io.github.sadeqit.weatherino.domain.util

import io.github.sadeqit.weatherino.domain.model.DistanceUnit
import io.github.sadeqit.weatherino.domain.model.PrecipitationUnit
import io.github.sadeqit.weatherino.domain.model.PressureUnit
import io.github.sadeqit.weatherino.domain.model.TemperatureUnit
import io.github.sadeqit.weatherino.domain.model.WindSpeedUnit
import kotlin.math.roundToInt

object WeatherUnitFormatter {

    fun temperature(
        celsius: Double,
        unit: TemperatureUnit,
    ): String {
        val value = WeatherUnitConverter.temperature(
            celsius = celsius,
            unit = unit,
        )

        val symbol = when (unit) {
            TemperatureUnit.Celsius -> "°c"
            TemperatureUnit.Fahrenheit -> "°f"
        }

        return "${value.roundToInt()}$symbol"
    }


    fun windSpeed(
        metersPerSecond: Double,
        unit: WindSpeedUnit,
    ): String {
        val value = WeatherUnitConverter.windSpeed(
            metersPerSecond = metersPerSecond,
            unit = unit,
        )

        return "${formatDecimal(value)} ${unit.symbol}"
    }

    fun precipitation(
        millimeters: Double,
        unit: PrecipitationUnit,
    ): String {
        val value = WeatherUnitConverter.precipitation(
            millimeters = millimeters,
            unit = unit,
        )

        return "${formatDecimal(value)} ${unit.symbol}"
    }

    fun pressure(
        hectopascal: Int,
        unit: PressureUnit,
    ): String {
        val value = WeatherUnitConverter.pressure(
            hectopascal = hectopascal,
            unit = unit,
        )

        return "${formatDecimal(value)} ${unit.symbol}"
    }

    fun distance(
        meters: Int,
        unit: DistanceUnit,
    ): String {
        val value = WeatherUnitConverter.distance(
            meters = meters,
            unit = unit,
        )

        return "${formatDecimal(value)} ${unit.symbol}"
    }

    private fun formatDecimal(value: Double): String {
        val rounded = (value * 10).roundToInt() / 10.0

        return if (rounded % 1.0 == 0.0) {
            rounded.toInt().toString()
        } else {
            rounded.toString()
        }
    }

    fun temperatureValue(
        celsius: Double,
        unit: TemperatureUnit,
    ): String {
        val value = WeatherUnitConverter.temperature(
            celsius = celsius,
            unit = unit,
        )

        return "${value.roundToInt()}°"
    }
}